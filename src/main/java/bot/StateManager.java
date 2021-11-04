package bot;

import bot.categories.CategoryManager;
import org.glassfish.jersey.internal.util.Producer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StateManager {
    public enum DialogState{
        waitParameter,
        waitNothing,
    }
    private CurrentState currentState;
    private Account takenAccount;
    private CategoryManager takenCategoryManager;
    private String chatID;
    private Function<String, SendMessage> bufferedCommand;
    private DialogState dialogState;

    public StateManager(String accountName){
        this(accountName, null);
    }

    public StateManager(String accountName, String chatID){
        takenAccount = new Account(accountName);
        currentState = CurrentState.tookAccount;
        takenCategoryManager = null;
        this.chatID = chatID;
        this.dialogState = DialogState.waitNothing;
    }

    public List<String> getAvailableButtonNames(){
        return switch (currentState) {
            case tookAccount ->
                    new ArrayList<>(Arrays.asList("Get total", "Get tree", "Show content", "Move to", "Help", "About"));
            case tookCategoryManager ->
                    new ArrayList<>(Arrays.asList("Get total", "Move up", "Rename","Create", "Put", "Delete", "Help", "About"));
        };
    }

    public void setBufferedCommand(Function<String, SendMessage> bufferedCommand) {
        this.bufferedCommand = bufferedCommand;
    }
    public Function<String, SendMessage> getBufferedCommand(){
        return bufferedCommand;
    }

    public void setDialogState(DialogState dialogState) {
        this.dialogState = dialogState;
    }

    public DialogState getDialogState(){
        return dialogState;
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
