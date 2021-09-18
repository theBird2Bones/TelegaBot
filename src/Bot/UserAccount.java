package Bot;

import java.util.ArrayList;

public class UserAccount {
    private String name;
    private ArrayList<Category> categories;

    public UserAccount(String name){
        this.name = name;
        categories = new ArrayList<>();
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
