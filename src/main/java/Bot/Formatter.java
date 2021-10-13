package Bot;


public class Formatter {
    public static String formatCategory(Category category){
        return "Category: " + category.getName() + "\n" + "Outcome: " + category.getTotal();
    }

    public static String formatCategoryManagerContent(CategoryManager categoryManager){
        var format = "Category manager: " + categoryManager.getName() + "\n" + "Inner categories:";
        for (var category : categoryManager.getCategories())
            format += "\n" + category.getName();

        return format;
    }

    public static String formatCategoryManagerTotal(CategoryManager categoryManager){
        var format = "Category manager: " + categoryManager.getName() +"\n" + "Total: " + categoryManager.getTotal()
                + "\n" + "with categories:";
        for (var category : categoryManager.getCategories())
            format += "\n" + category.getName() + ": " + category.getTotal();
        return format;
    }

    public static String formatAccountInnerCategoryManager(Account account){
        var format = "Account: " + account.getName() + "\n" + "Inner category managers:";
        for(var categoryManager : account.getCategoryManagers())
            format += "\n" + categoryManager.getName();
        return  format;
    }
    public static String formatAccountInnerCategoryManagerTotal(Account account) {
        var format = "Account: " + account.getName() + "\n" + "Total: " + account.getTotal() + "\n"
                + "with inner category managers:";
        for(var categoryManager : account.getCategoryManagers())
            format += "\n" + categoryManager.getName();
        return  format;
    }
}
