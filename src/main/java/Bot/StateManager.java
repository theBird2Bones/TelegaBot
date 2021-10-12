package Bot;

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

    //TODO: выпилить после подключения бд
    public void setChatID(String id){
        chatID = id;
    }

    public String getChatID(){
        return chatID;
    }

    public void releaseCategoryManager(){
        chosenLevel = ChosenLevel.account;
        takenCategoryManager = null;
    }

//    public SendMessage

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
