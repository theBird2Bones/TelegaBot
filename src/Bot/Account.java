package Bot;

import java.util.ArrayList;

public class Account {
    private String name;
    private ArrayList<CategoryManager> categoryManagers;

    public Account(String name){
        this.name = name;
        categoryManagers = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void addCategoryManager(CategoryManager categoryManager){
        categoryManagers.add(categoryManager);
    }

    public ArrayList<CategoryManager> getCategoryManagers(){
        return categoryManagers;
    }

    public long getTotal(){
        var iter = categoryManagers.iterator();
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
