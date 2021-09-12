package Bot;

import java.util.Deque;
import java.util.LinkedList;

public class Account {
    private String name;
    private Deque<CategoryManager> categoryManagers;

    public Account(String name){
        this.name = name;
        categoryManagers = new LinkedList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public void addCategoryManager(CategoryManager categoryManager){
        categoryManagers.addLast(categoryManager);
    }

    public long getTotal(){
        var iter = categoryManagers.iterator();
        var total = 0l;
        while(iter.hasNext()){
            total += iter.next().getCategoryTotal();
        }
        return total;
    }

}
