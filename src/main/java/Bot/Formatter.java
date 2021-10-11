package Bot;

import org.apache.commons.lang.NotImplementedException;

public class Formatter {
    public String formatCategory(Category category){
        return "Category: " + category.getName() + "\n" + "Outcome: " + category.getTotal();
    }

    public String formatCategoryManagerContent(CategoryManager categoryManager){
        var format = "Category manager: " + categoryManager.getName() + "\n" + "Inner categories:";
        for (var category : categoryManager.getCategories())
            format += "\n" + category.getTotal();

        return format;
    }

    public String formatCategoryManagerTotal(CategoryManager categoryManager){
        var format = "Category manager: " + categoryManager.getName() +"\n" + "Total: " + categoryManager.getTotal()
                + "with categories:";
        for (var category : categoryManager.getCategories())
            format += "\n" + category.getName() + ": " + category.getTotal();
        return format;
    }

    public String formatAccountInnerCategoryManager(Account account){
        var format = "Account: " + account.getName() + "\n" + "Inner category managers:";
        for(var categoryManager : account.getCategoryManagers())
            format += "\n" + categoryManager.getName();
        return  format;
    }
    public String formatAccountInnerCategoryManagerTotal(Account account) {
        var format = "Account: " + account.getName() + "\n" + "Total:" + "\n" + account.getTotal()
                + "with inner managers:";
        for(var categoryManager : account.getCategoryManagers())
            format += "\n" + categoryManager.getName();
        return  format;
    }
}
