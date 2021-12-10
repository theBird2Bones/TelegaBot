package bot.categories;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="deposit_categories")
public class DepositCategory extends Category {
    public DepositCategory() {
        super();
    }

    public DepositCategory(String name){
        super(name);
    }

    public DepositCategory(String name, long iniValue){
        super(name, iniValue);
    }

    @Override
    public void put(long value) {
        total += Math.abs(value);
    }
}
