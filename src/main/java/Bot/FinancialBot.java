package Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import  org.telegram.telegrambots.meta.api.objects.*;
import java.util.*;

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
    private HashMap<String, Command> commands = createCommands();

    private HashMap<String, Command> createCommands(){
        var commands = new HashMap<String, Command>();
        commands.put("/about", new Command(Command.Name.about, "Creators: Konstantin & Mariia"));
        commands.put("/help", new Command(Command.Name.help, "That I can do:\n/about -" +
                " Show my creators\n/help - Show the list of possible commands"));

        return commands;
    }

    private SendMessage getResponseToInputtedMessage(Message message){
        if(!usersStateManager.containsKey(message.getChatId().toString())){
           usersStateManager.put(message.getChatId().toString(), new StateManager("Common"));
        }
        var userStateManager = usersStateManager.get(message.getChatId().toString());

        return MessageBuilder.getSendMessageWithId(
                commands.get(userStateManager.getChatID()).getMessage(), message.getChatId().toString());
    }

    private static class MessageBuilder{
        public static SendMessage getSendMessageWithId(SendMessage message, String id){
            message.setChatId(id);
            return message;
        }
    }

    public void botAnswer(Category category) {

    }
    public void botAnswer(CategoryManager categoryManager){

    }

    public void botAnswer(Account account){

    }
}
