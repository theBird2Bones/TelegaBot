package Bot;

public class StateManager {
    private ChosenLevel chosenLevel;
    private Account account;
    private Object pointer;
    private StateManager(Builder builder){
        this.chosenLevel = builder.chosenLevel;
        this.account = builder.account;
        this.pointer = builder.pointer;
    }

    public void levelUp(){
        if(pointer instanceof Category){
            pointer = ((Category) pointer).getMasterCategoryManager();
            chosenLevel = ChosenLevel.categoryManager;
        }

        if(pointer instanceof CategoryManager){
            pointer = ((CategoryManager) pointer).getMasterAccount();
            chosenLevel = ChosenLevel.account;
        }
    }

    //TODO: сделать метод levelDown с параметром, котоый бы позвалял посмотреть что внутри
    public void levelDown(Category category){
       //?????????????
    }
    public void setLevel(ChosenLevel chosenLevel){
        this.chosenLevel = chosenLevel;
    }

    public ChosenLevel getCurrentLevel(){
        return chosenLevel;
    }
    //TODO: добавить возможность вытаскивать поинтер для более быстрого доступа. нужна какая то абстракция

    public Account getAccount(){
        return account;
    }
    public static class Builder{
        private ChosenLevel chosenLevel;
        private Account account;
        private Object pointer = account;

        public StateManager build(){
            return new StateManager(this);
        }
        public Builder setAccount(Account account){
            this.account = account;
            return this;
        }

        public Builder setAccount(String name){
            this.account = new Account(name);
            return this;
        }
    }
}
