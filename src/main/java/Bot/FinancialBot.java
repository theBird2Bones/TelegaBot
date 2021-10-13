package Bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import  org.telegram.telegrambots.meta.api.objects.*;
import java.util.*;
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
    private HashMap<String, Command> commands = createCommands();

    private StateManager initStateManagerWithId(String chatId){
        var stateManager = new StateManager("Common", chatId);
        stateManager.getTakenAccount().addCategoryManager("Income");
        stateManager.getTakenAccount().addCategoryManager("Outcome");
        return stateManager;
    }

    private HashMap<String, Command> createCommands(){
        var commands = new HashMap<String, Command>();
        commands.put("/about", new TextProcessor( "Creators: Konstantin & Mariia"));
        commands.put("/help", new TextProcessor("That I can do:\n/about -" +
                " Show my creators\n/help - Show the list of possible commands"));
//        commands.put("get total",
//                new AccountProcessor(stateManager -> Formatter.formatAccountInnerCategoryManagerTotal(stateManager)));
//        commands.put("show content",
//                new AccountProcessor(stateManager -> Formatter.formatAccountInnerCategoryManager(stateManager)));
//        commands.put("move to", new TransitionProcessorFromAccountToCategoryManager("to take a category manager you should input its index"));
        //проблема: для прописывания таких команд надо иметь состояния диалога

        return commands;
    }

    //TODO: there is ForceReply for chain conversation. it worth to check it
    private SendMessage getResponseToInputtedMessage(Message message){
        if(!usersStateManager.containsKey(message.getChatId().toString())){
           usersStateManager.put(message.getChatId().toString(),
                   initStateManagerWithId(message.getChatId().toString()));
        }
        var userStateManager = usersStateManager.get(message.getChatId().toString());

        var messageText = message.getText();

        if(messageText.contains("/about")){
            return MessageBuilder.createMessage("Creators: Konstantin & Mariia", message.getChatId().toString());
        }
        else if(messageText.contains("/help")) {
            return MessageBuilder.createMessage("That I can do:\n/about -" +
                    " Show my creators\n/help - Show the list of possible commands", message.getChatId().toString());
        }
        else if(messageText.contains("show inner")){
            if (userStateManager.getCurrentState() == CurrentState.tookAccount)
                return MessageBuilder.createMessage(
                        Formatter.formatAccountInnerCategoryManager(userStateManager.getTakenAccount()),
                        message.getChatId().toString());
            else if (userStateManager.getCurrentState() == CurrentState.tookCategoryManager)
                return MessageBuilder.createMessage(
                        Formatter.formatCategoryManagerContent(userStateManager.getTakenCategoryManager()),
                        message.getChatId().toString());
        }
        else if(messageText.contains("get total")){
            if (userStateManager.getCurrentState() == CurrentState.tookAccount)
                return MessageBuilder.createMessage(
                        Formatter.formatAccountInnerCategoryManagerTotal(userStateManager.getTakenAccount()),
                        message.getChatId().toString());
            else if (userStateManager.getCurrentState() == CurrentState.tookCategoryManager)
                return MessageBuilder.createMessage(
                        Formatter.formatCategoryManagerTotal(userStateManager.getTakenCategoryManager()),
                        message.getChatId().toString());
        }

        else if(messageText.contains("move to")){
            if (userStateManager.getCurrentState() == CurrentState.tookAccount) {
                userStateManager.setCurrentState(CurrentState.tookCategoryManager);
                userStateManager.setTakenCategoryManager(
                        userStateManager
                                .getTakenAccount()
                                .getCategoryManagers()
                                .get(Integer.parseInt(messageText.split(" ")[2]))
                );
                return MessageBuilder.createMessage(
                        String.format("you moved to %s\n%s",
                                userStateManager.getTakenCategoryManager().getName(),
                                Formatter.formatCategoryManagerContent(
                                        userStateManager.getTakenCategoryManager())),
                        message.getChatId().toString());
            }
        }
        else if(messageText.contains("add at")){
            if(userStateManager.getCurrentState() == CurrentState.tookCategoryManager){
                var splittedMessageText = messageText.split(" ");
                if(Pattern.matches("^-?\\d+?$",splittedMessageText[2])){
                    userStateManager
                            .getTakenCategoryManager()
                            .getCategories()
                            .get(Integer.parseInt(splittedMessageText[2]))
                            .add(Long.parseLong(splittedMessageText[3]));
                }
            }

            return MessageBuilder.createMessage(
                    Formatter.formatCategoryManagerContent(userStateManager.getTakenCategoryManager()),
                    userStateManager.getChatID()
            );
        }
        else if(messageText.contains("add")) {
            if (userStateManager.getCurrentState() == CurrentState.tookCategoryManager) {
                var splittedMessageText = messageText.split(" ");
                userStateManager
                        .getTakenCategoryManager()
                        .addCategory(splittedMessageText[1]);
            }
            return MessageBuilder.createMessage(
                    Formatter.formatCategoryManagerContent(userStateManager.getTakenCategoryManager()),
                    userStateManager.getChatID()
            );
        }
        else if(messageText.contains("move up")){

            if (userStateManager.getCurrentState() == CurrentState.tookCategoryManager) {
                userStateManager.releaseCategoryManager();
            }
            return MessageBuilder.createMessage(
                    Formatter.formatAccountInnerCategoryManager(userStateManager.getTakenAccount()),
                    userStateManager.getChatID()
            );
        }

        var sM = new SendMessage(message.getChatId().toString(), "hehe");
        return sM;
    }

    private void prepareMessage(Message message, CurrentState state){

    }
    private static class MessageBuilder{
        public static SendMessage createMessage(String messageText, String chatId){
            return new SendMessage(chatId,messageText);
        }
    }
}