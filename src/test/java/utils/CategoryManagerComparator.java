package utils;

import bot.categories.CategoryManager;

public class CategoryManagerComparator {
    public static boolean isEqual(CategoryManager cm1, CategoryManager cm2){
        if(cm1.getCategories().size() != cm2.getCategories().size())
            return false;
        var categories1 = cm1.getCategories();
        var categories2 = cm2.getCategories();
        for(int i = 0; i < categories1.size(); i++){
            if(!CategoryComparator.isEqual(categories1.get(i), categories2.get(i)))
                return false;
        }
        return true;
    }
}
