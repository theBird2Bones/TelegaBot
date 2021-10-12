package Bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StateManager {
    private ChosenLevel chosenLevel;
    private Account takenAccount;
    private CategoryManager takenCategoryManager;
    private String chatID;

    public StateManager(String accountName){
        takenAccount = new Account(accountName);
        chosenLevel = ChosenLevel.account;
        takenCategoryManager = null;
    }

    public StateManager(String accountName, String chatID){
        takenAccount = new Account(accountName);
        chosenLevel = ChosenLevel.account;
        takenCategoryManager = null;
        this.chatID = chatID;
    }

    public SendMessage executeCommand(Command command){
        return switch (command.getName()){
            case about -> command.getMessage();
            case help -> command.getMessage();
            case getAccountTotal -> command.getMessage();
            case getInnerCategoryManagers -> command.getMessage();
            case getInnerCategories -> command.getMessage();
            case getCategoryManagerTotal -> command.getMessage();
            case getCategoryTotal -> command.getMessage();
            case addCategory -> command.getMessage();
        };
    }

    public String getChatID(){
        return chatID;
    }

    public void releaseCategoryManager(){
        chosenLevel = ChosenLevel.account;
        takenCategoryManager = null;
    }

    public ChosenLevel getChosenLevel(){
        return chosenLevel;
    }

    public Account getTakenAccount(){
        return takenAccount;
    }

    public void setTakenCategoryManager(CategoryManager categoryManager){
        takenCategoryManager = categoryManager;
    }

    public CategoryManager getTakenCategoryManager(){
        chosenLevel = ChosenLevel.categoryManager;
        return takenCategoryManager;
    }
}
