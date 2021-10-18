package Bot;

public class Formatter {
    public static String formatCategory(Category category){
        return "Category: " + category.getName() + "\n" + "Outcome: " + category.getTotal();
    }

    public static String formatCategoryManagerContent(CategoryManager categoryManager){
        var format = "Category manager: " + categoryManager.getName() + "\n" + "Inner categories:";
        var index = 0;
        for (var category : categoryManager.getCategories()){
            index++;
            format += "\n" + index + ") " + category.getName();
        }


        return format;
    }

    public static String formatCategoryManagerTotal(CategoryManager categoryManager){
        var format = "Category manager: " + categoryManager.getName() +"\n" + "Total: " + categoryManager.getTotal()
                + "\n" + "with categories:";
        var index = 0;
        for (var category : categoryManager.getCategories()) {
            index++;
            format += "\n" + index + ") " + category.getName() + ": " + category.getTotal();
        }
        return format;
    }

    public static String formatAccountInnerCategoryManager(Account account){
        var format = "Account: " + account.getName() + "\n" + "Inner category managers:";
        var index = 0;
        for(var categoryManager : account.getCategoryManagers()){
            index++;
            format += "\n" + index + ") " + categoryManager.getName();
        }
        return  format;
    }
    public static String formatAccountInnerCategoryManagerTotal(Account account) {
        var format = "Account: " + account.getName() + "\n" + "Total: " + account.getTotal() + "\n"
                + "with inner category managers:";
        var index = 0;
        for(var categoryManager : account.getCategoryManagers()) {
            index++;
            format += "\n" + index + ") " + categoryManager.getName();
        }
        return  format;
    }
}
