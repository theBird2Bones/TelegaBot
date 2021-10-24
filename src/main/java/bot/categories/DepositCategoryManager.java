package bot.categories;

public class DepositCategoryManager extends CategoryManager {
    public DepositCategoryManager(String name) {
        super(name);
    }

    @Override
    public void addCategory(String name) {
        categories.add(new DepositCategory(name));
    }
}
