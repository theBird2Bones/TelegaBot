package Bot;

abstract class Category {
    protected String name;
    protected long total;

    public Category(String name, long initValue){
        this.name = name;
        total = initValue;
    }

    public Category(String name){
        total = 0;
        this.name = name;
    }

    abstract public void put(long value);

    public long getTotal(){
        return total;
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
