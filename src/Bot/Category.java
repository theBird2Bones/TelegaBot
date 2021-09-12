package Bot;

public class Category {
    private String name;
    private long amount;

    public Category(String name){
        this.name = name;
    }

    public Category(String name, long amount ){
        this.name = name;
        this.amount = amount;
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


}
