package Bot;
public class Category {
    private String name;
    private long amount;
    private UserAccount masterUserAccount;

    public Category(String name, int amount, CategoryManager categoryManager){
        this.name = name;
    }

    public Category(String name, long amount,UserAccount masterUserAccount ){
        this.name = name;
        this.amount = amount;
        this.masterUserAccount = masterUserAccount;
    }

    public void subtract(long value){
        add(-value);
    }

    public void add(long value){
        amount += value;
    }

    public long getAmount(){
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
