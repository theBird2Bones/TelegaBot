package Bot;

import java.util.Objects;

public class Category {
    private String name;
    private long amount;

    public Category(String name){
        this.name = name;
    }

    public Category(String name, long amount ){
        this.name = name;
        this.amount = amount;
    }

    public void subtract(long value){
        add(-value);
    }

    public void add(long value){
        amount += value;
    }

    public long getAmount(){
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
