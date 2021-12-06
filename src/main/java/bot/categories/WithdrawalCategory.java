package bot.categories;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="withdrawal_categories")
public class WithdrawalCategory extends Category {

    public WithdrawalCategory(String name){
        super(name);
    }

    public WithdrawalCategory() { super();}

    @Override
    public void put(long value) {
        total -= Math.abs(value);
    }
}
