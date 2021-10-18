package Bot;

import java.util.ArrayList;

public class Account {
    private String name;
    private String currency;
    private ArrayList<CategoryManager> categoryManagers;

    public Account(String name){
        this.name = name;
        categoryManagers = new ArrayList<>();
        categoryManagers.add(new DepositCategoryManager("Income"));
        categoryManagers.add(new WithdrawalCategoryManager("Outcome"));
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCurrency() {return currency;}

    public void setCurrency(String cur){ this.currency = cur;}

    public String getName(){
        return name;
    }

    public ArrayList<CategoryManager> getCategoryManagers(){
        return categoryManagers;
    }

    public long getTotal(){
        var iter = categoryManagers.iterator();
        var total = 0l;
        while(iter.hasNext()){
            total += iter.next().getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return name;
    }
}
