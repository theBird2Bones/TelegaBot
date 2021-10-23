package bot.categories;

import java.util.*;

public abstract class CategoryManager {
    protected String name;
    protected ArrayList<Category> categories;

    public CategoryManager(String name){
        this.name = name;
        categories = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {return name;}

    public ArrayList<Category> getCategories(){
        return categories;
    }

    abstract public void addCategory(String name);

    public void removeCategoryWithIndex(int index){
        categories.remove(index);
    }

    public long getTotal(){
        var iter = categories.iterator();
        var total = 0l;
        while(iter.hasNext()){
            total += iter.next().getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return name;
    }
}
