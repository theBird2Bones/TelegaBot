package Bot;

import java.util.HashMap;
import java.util.HashSet;

public class CategoryManager {
    private String name;
    private HashSet<Category> categories;

    public CategoryManager(String name){
        this.name = name;
        categories = new HashSet<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCategory(Category cat){
        categories.add(cat);
    }

    public void removeCategory(Category cat){
        categories.remove(cat);
    }

    public long getCategoryTotal(){
        var iter = categories.iterator();
        var total = 0l;
        while(iter.hasNext()){
            total += iter.next().getAmount();
        }
        return total;
    }
}
