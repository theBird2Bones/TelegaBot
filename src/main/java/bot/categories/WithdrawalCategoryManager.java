package bot.categories;

public class WithdrawalCategoryManager extends CategoryManager {
    public WithdrawalCategoryManager(String name) {
        super(name);
    }

    @Override
    public void addCategory(String name) {
        categories.add(new WithdrawalCategory(name));
    }
}
