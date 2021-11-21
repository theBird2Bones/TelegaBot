package bot.categories;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("withdrawal_category_managers")
public class WithdrawalCategoryManager extends CategoryManager {
    public WithdrawalCategoryManager(String name) {
        super(name);
    }

    public WithdrawalCategoryManager() { super();}

    @Override
    public void addCategory(String name) {
        categories.add(new WithdrawalCategory(name));
    }
}
