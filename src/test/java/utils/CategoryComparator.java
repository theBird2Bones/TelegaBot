package utils;

import bot.categories.Category;

public class CategoryComparator {
    public static boolean isEqual(Category c1, Category c2) {
        return c1.getTotal() == c2.getTotal() &&
                c1.getName().equals(c2.getName());
    }
}
