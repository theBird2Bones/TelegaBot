package bot;

import bot.categories.CategoryManager;

public class StateManager {
    private CurrentState currentState;
    private Account takenAccount;
    private CategoryManager takenCategoryManager;
    private String chatID;

    public StateManager(String accountName){
        takenAccount = new Account(accountName);
        currentState = CurrentState.tookAccount;
        takenCategoryManager = null;
    }

    public StateManager(String accountName, String chatID){
        takenAccount = new Account(accountName);
        currentState = CurrentState.tookAccount;
        takenCategoryManager = null;
        this.chatID = chatID;
    }

    public String getChatID(){
        return chatID;
    }

    public void releaseCategoryManager(){
        currentState = CurrentState.tookAccount;
        takenCategoryManager = null;
    }

    public CurrentState getCurrentState(){
        return currentState;
    }

    public void setCurrentState(CurrentState state){
        currentState = state;
    }

    public Account getTakenAccount(){
        return takenAccount;
    }

    public void setTakenCategoryManager(CategoryManager categoryManager){
        takenCategoryManager = categoryManager;
    }

    public CategoryManager getTakenCategoryManager(){
        currentState = CurrentState.tookCategoryManager;
        return takenCategoryManager;
    }
}
