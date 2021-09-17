package Bot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CategoryManager {
    private String name;
    private ArrayList<Category> categories;
    private Account masterAccount;

    public CategoryManager(String name, Account masterAccount){
        this.name = name;
        categories = new ArrayList<>();
        this.masterAccount = masterAccount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCategory(String name){
        var newCategory = new Category(name,this);
        addCategory(newCategory);
    }

    public void removeCategory(Category cat){
        categories.remove(cat);
    }

    public long getTotal(){
        var iter = categories.iterator();
        var total = 0l;
        while(iter.hasNext()){
            total += iter.next().getAmount();
        }
        return total;
    }

    @Override
    public String toString() {
        return name;
    }

    private void addCategory(Category cat){
        categories.add(cat);
    }
}
