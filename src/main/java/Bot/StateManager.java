package Bot;

import org.antlr.stringtemplate.language.Cat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StateManager {
    private ChosenLevel chosenLevel;
    private Account takenAccount;
    private CategoryManager takenCategoryManager;
    private StateManager(Builder builder){
        takenAccount = builder.account;
        chosenLevel = ChosenLevel.account;
        takenCategoryManager = null;
    }

    public void releaseCategoryManager(){
        chosenLevel = ChosenLevel.account;
        takenCategoryManager = null;
    }

    public SendMessage

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

    public static class Builder{
        private ChosenLevel chosenLevel;
        private Account account;

        public StateManager build(){
            return new StateManager(this);
        }
        public Builder setAccount(Account account){
            this.account = account;
            return this;
        }

        public Builder setAccountWithName(String name){
            this.account = new Account(name);
            return this;
        }
    }
}
