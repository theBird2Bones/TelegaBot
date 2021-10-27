package bot.service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import  org.telegram.telegrambots.meta.api.objects.*;
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
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var inMessage = update.getMessage();
        try {
            execute(getResponseToInputtedMessage(inMessage));
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, StateManager> usersStateManager = new HashMap<>();
    private CommandTree commands = createCommands();

    private StateManager initStateManagerWithId(String chatId){
        var stateManager = new StateManager("Common", chatId);
        return stateManager;
    }

    private CommandTree createCommands(){
        var tree = new CommandTree.Builder();
        tree.setCommand("/about",
                textCommand -> stateManager -> new AboutCommand(stateManager));
        tree.setCommand("/help",
                textCommand -> stateManager -> new HelpCommand(stateManager));
        tree.setCommand("show content",
                textCommand -> stateManager -> new ShowContentCommand(stateManager));
        tree.setCommand("create",
                textCommand -> stateManager -> new CreateCommand(stateManager, textCommand));
        tree.setCommand("delete",
                textCommand -> stateManager -> new DeleteCommand(stateManager, textCommand));
        tree.setCommand("get total",
                textCommand -> stateManager -> new GetTotalCommand(stateManager));
        tree.setCommand("get tree",
                textCommand -> stateManager -> new GetTreeCommand(stateManager));
        tree.setCommand("put",
                textCommand -> stateManager -> new PutCommand(stateManager, textCommand));
        tree.setCommand("move to",
                textCommand -> stateManager -> new MoveToCommand(stateManager, textCommand));
        tree.setCommand("move up",
                textCommand -> stateManager -> new MoveUpCommand(stateManager));
        tree.setCommand("rename",
                textCommand -> stateManager -> new RenameCommand(stateManager, textCommand));

        return tree.build();
    }

    private SendMessage getResponseToInputtedMessage(Message message) {
        if (!usersStateManager.containsKey(message.getChatId().toString())) {
            usersStateManager.put(message.getChatId().toString(),
                    initStateManagerWithId(message.getChatId().toString()));
        }
        var userStateManager = usersStateManager.get(message.getChatId().toString());
        var messageText = message.getText().toLowerCase();
        var outMessage = commands.getCommand(messageText).apply(messageText).apply(userStateManager).execute();

        return MessageBuilder.createMessage(outMessage, userStateManager.getChatID());
    }

    private static class MessageBuilder{
        public static SendMessage createMessage(String messageText, String chatId){
            return new SendMessage(chatId,messageText);
        }
    }
}