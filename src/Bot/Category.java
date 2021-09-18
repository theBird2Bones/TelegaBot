package Bot;
public class Category {
    private String name;
    private long income;
    private long expense;
    private CategoryManager masterCategoryManager;

    public Category(String name, CategoryManager masterCategoryManager ){
        this.name = name;
        this.masterCategoryManager = masterCategoryManager;
    }

    public void subtract(long value){
        if(value > 0)
            expense += value;
        else throw new IllegalArgumentException();
    }

    public void add(long value){
        if(value > 0)
            income += value;
        else throw new IllegalArgumentException();
    }

    public long getTotal(){
        return income - expense;
    }

    public CategoryManager getMasterCategoryManager(){
        return masterCategoryManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
