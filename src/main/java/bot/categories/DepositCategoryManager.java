package bot.categories;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("deposit_category_managers")
public class DepositCategoryManager extends CategoryManager {
    public DepositCategoryManager(String name) {
        super(name);
    }
    public DepositCategoryManager() { super(); }

    @Override
    public void addCategory(String name) {
        categories.add(new DepositCategory(name));
    }
}
