package bot.service;

import bot.aod.StateManagerAOD;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import bot.*;
import bot.commands.*;

public class FinancialBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "Financil_bot";
    }

    @Override
    public String getBotToken() {
        String token = "";
        try {
            var sc = new Scanner(new File("./.telega_bot_token.txt"));
            token = sc.next();
        } catch (FileNotFoundException e) {
            System.out.println("troubles /w bot token");
        }
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var inMessage = update.getMessage();
        if (update.hasCallbackQuery()) {
            inMessage = update.getCallbackQuery().getMessage();
            inMessage.setText(update.getCallbackQuery().getData());
        }
        try {
            execute(getResponseToInputtedMessage(inMessage));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private CommandTree commands = createCommands();

    private CommandTree createCommands() {
        var tree = new CommandTree.Builder();
        tree
                .setCommand("/start",
                        textCommand -> stateManager -> new StartCommand(stateManager))
                .setCommand("about",
                        textCommand -> stateManager -> new AboutCommand(stateManager))
                .setCommand("help",
                        textCommand -> stateManager -> new HelpCommand(stateManager))
                .setCommand("show content",
                        textCommand -> stateManager -> new ShowContentCommand(stateManager))
                .setCommand("create",
                        textCommand -> stateManager -> new CreateCommand(stateManager, textCommand))
                .setCommand("delete",
                        textCommand -> stateManager -> new DeleteCommand(stateManager, textCommand))
                .setCommand("get total",
                        textCommand -> stateManager -> new GetTotalCommand(stateManager))
                .setCommand("get tree",
                        textCommand -> stateManager -> new GetTreeCommand(stateManager))
                .setCommand("put",
                        textCommand -> stateManager -> new PutCommand(stateManager, textCommand))
                .setCommand("move to",
                        textCommand -> stateManager -> new MoveToCommand(stateManager, textCommand))
                .setCommand("move up",
                        textCommand -> stateManager -> new MoveUpCommand(stateManager))
                .setCommand("rename",
                        textCommand -> stateManager -> new RenameCommand(stateManager, textCommand));

        return tree.build();
    }

    private SendMessage getResponseToInputtedMessage(Message message) {
        StateManager userStateManager = StateManagerAOD.findById(message.getChatId());
        if (userStateManager == null) {
            userStateManager = new StateManager("Common", message.getChatId().toString());
            StateManagerAOD.save(userStateManager);
        }

        var messageText = message.getText().toLowerCase();
        var commandName = switch (userStateManager.getDialogState()) {
            case waitNothing -> messageText;
            case waitParameter -> userStateManager.getBufferedCommandName();
        };

        var preparedMessage =
                commands.getCommand(commandName).apply(messageText).apply(userStateManager).execute();
        StateManagerAOD.update(userStateManager);
        return preparedMessage;
    }
}
