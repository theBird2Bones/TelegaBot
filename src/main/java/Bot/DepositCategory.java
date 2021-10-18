package Bot;

public class DepositCategory extends Category {
    public DepositCategory(String name, long initValue){
        super(name, initValue);
    }

    public DepositCategory(String name){
        super(name);
    }

    @Override
    public void put(long value) {
        total += Math.abs(value);
    }
}
