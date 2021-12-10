package bot;

import bot.categories.CategoryManager;
import bot.dao.operations.BdOperation;
import bot.dao.operations.NoOperation;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "stateManagers")
public class StateManager {
    public StateManager() {super(); }

    public enum DialogState {
        waitParameter,
        waitNothing,
    }

    @Id
    @Column(name = "id")
    private long chatID;

    @Enumerated(EnumType.STRING)
    private CurrentState currentState;

    @Enumerated(EnumType.STRING)
    private DialogState dialogState;

    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    private Account takenAccount;

    @OneToOne(targetEntity = CategoryManager.class, cascade = CascadeType.ALL)
    private CategoryManager takenCategoryManager;

    @Column(name = "bufferdCommandName")
    private String bufferedCommandName;

    @Transient
    BdOperation bdOperation;

    public StateManager(String accountName) {
        this(accountName, null);
    }

    public StateManager(String accountName, String chatID) {
        takenAccount = new Account(accountName);
        currentState = CurrentState.tookAccount;
        takenCategoryManager = null;
        this.chatID = Long.parseLong(chatID);
        this.dialogState = DialogState.waitNothing;
        this.bdOperation = new NoOperation();
    }

    public List<String> getAvailableButtonNames() {
        return switch (currentState) {
            case tookAccount -> takenAccount.getAvailableButtonNames();
            case tookCategoryManager -> takenCategoryManager.getAvailableButtonNames();
        };
    }

    public void setBufferedCommandName(String name) {
        bufferedCommandName = name;
    }

    public String getBufferedCommandName() {
        return bufferedCommandName;
    }

    public String getChatID() {
        return String.valueOf(chatID);
    }

    public void releaseCategoryManager() {
        currentState = CurrentState.tookAccount;
        takenCategoryManager = null;
    }

    public BdOperation getBdOperation(){
        return bdOperation;
    }

    public void setBdOperation(BdOperation bdOperation){
        this.bdOperation = bdOperation;
    }

    public void setDialogState(DialogState dialogState) {
        this.dialogState = dialogState;
    }

    public DialogState getDialogState() {
        return dialogState;
    }

    public CurrentState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(CurrentState state) {
        currentState = state;
    }

    public Account getTakenAccount() {
        return takenAccount;
    }

    public void setTakenCategoryManager(CategoryManager categoryManager) {
        takenCategoryManager = categoryManager;
    }

    public CategoryManager getTakenCategoryManager() {
        currentState = CurrentState.tookCategoryManager;
        return takenCategoryManager;
    }
}
