package bot.service;

import bot.dao.StateManagerDao;
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

    private final CommandTree commands = createCommands();

    private CommandTree createCommands() {
        var tree = new CommandTree.Builder();
        tree
                .setCommand("/start",
                        textCommand -> StartCommand::new)
                .setCommand("about",
                        textCommand -> AboutCommand::new)
                .setCommand("help",
                        textCommand -> HelpCommand::new)
                .setCommand("show content",
                        textCommand -> ShowContentCommand::new)
                .setCommand("create",
                        textCommand -> stateManager -> new CreateCommand(stateManager, textCommand))
                .setCommand("delete",
                        textCommand -> stateManager -> new DeleteCommand(stateManager, textCommand))
                .setCommand("get total",
                        textCommand -> GetTotalCommand::new)
                .setCommand("get tree",
                        textCommand -> GetTreeCommand::new)
                .setCommand("put",
                        textCommand -> stateManager -> new PutCommand(stateManager, textCommand))
                .setCommand("close",
                        textCommand -> stateManager -> new CloseCommand(stateManager, textCommand))
                .setCommand("set goal",
                        textCommand -> stateManager -> new SetGoalCommand(stateManager, textCommand))
                .setCommand("move to",
                        textCommand -> stateManager -> new MoveToCommand(stateManager, textCommand))
                .setCommand("move up",
                        textCommand -> MoveUpCommand::new)
                .setCommand("rename",
                        textCommand -> stateManager -> new RenameCommand(stateManager, textCommand));

        return tree.build();
    }

    private SendMessage getResponseToInputtedMessage(Message message) {
        StateManager userStateManager = StateManagerDao.findById(message.getChatId());
        if (userStateManager == null) {
            userStateManager = new StateManager("Common", message.getChatId().toString());
            StateManagerDao.persist(userStateManager);
        }

        var messageText = message.getText().toLowerCase();
        var commandName = switch (userStateManager.getDialogState()) {
            case waitNothing -> messageText;
            case waitParameter -> userStateManager.getBufferedCommandName();
        };

        var preparedMessage =
                commands.getCommand(commandName).apply(messageText).apply(userStateManager).execute();
        userStateManager.getBdOperation().performOperation(userStateManager);
        return preparedMessage;
    }
}
