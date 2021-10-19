package Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import  org.telegram.telegrambots.meta.api.objects.*;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class FinancialBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "Financil_bot";
    }

    @Override
    public String getBotToken() {
        return "1966706011:AAHJpIam9Wj0MaJg67ZI3PDFfeeKf-F6nes";
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
    private HashMap<String, Function<StateManager,Command>> commands = createCommands();

    //как создать команды заранее, если они требуют доп параметры???? я не понимаю
    private StateManager initStateManagerWithId(String chatId){
        var stateManager = new StateManager("Common", chatId);
        return stateManager;
    }

    private HashMap<String, Function<StateManager,Command>> createCommands(){
        var commands = new HashMap<String, Function<StateManager,Command>>();

        return commands;
    }

    //TODO: there is ForceReply for chain conversation. it worth to check it
    private SendMessage getResponseToInputtedMessage(Message message) {
        if (!usersStateManager.containsKey(message.getChatId().toString())) {
            usersStateManager.put(message.getChatId().toString(),
                    initStateManagerWithId(message.getChatId().toString()));
        }
        var userStateManager = usersStateManager.get(message.getChatId().toString());

        var messageText = message.getText().toLowerCase();
        var outMessage = "undefined command";

        if (messageText.contains("/about")) {
            outMessage = new AboutCommand(userStateManager).execute();
        } else if (messageText.contains("/help")) {
            outMessage = new HelpCommand(userStateManager).execute();
        } else if (messageText.contains("show content")) {
            outMessage = new ShowContentCommand(userStateManager).execute();
        } else if (messageText.contains("get total")) {
            outMessage = new GetTotalCommand(userStateManager).execute();
        } else if (messageText.contains("move to")) {
            outMessage = new MoveToCommand(userStateManager, messageText).execute();
        } else if (messageText.contains("increase")) {
            outMessage = new IncreaseCommand(userStateManager, messageText).execute();
        } else if (messageText.contains("/create")) {
            outMessage = new CreateCommand(userStateManager, messageText).execute();
        } else if (messageText.contains("move up")) {
            outMessage = new MoveUpCommand(userStateManager).execute();
        } else if (messageText.contains("/delete")) {
            outMessage = new DeleteCommand(userStateManager, messageText).execute();
        } else if (messageText.contains("rename")) {
            outMessage = new RenameCommand(userStateManager, messageText).execute();
        } else if (messageText.contains("get_tree")){
            outMessage = new GetTreeCommand(userStateManager).execute();
        }
        return MessageBuilder.createMessage(outMessage, userStateManager.getChatID());
    }

    private static class MessageBuilder{
        public static SendMessage createMessage(String messageText, String chatId){
            return new SendMessage(chatId,messageText);
        }
    }
}