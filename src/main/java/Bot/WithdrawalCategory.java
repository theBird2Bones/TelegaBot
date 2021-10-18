package Bot;

public class WithdrawalCategory extends Category {
    public WithdrawalCategory(String name, long initValue){
        super(name, initValue);
    }

    public WithdrawalCategory(String name){
        super(name);
    }

    @Override
    public void put(long value) {
        total -= Math.abs(value);
    }
}
