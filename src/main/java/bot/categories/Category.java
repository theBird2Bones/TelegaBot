package bot.categories;


import javax.persistence.*;

@Entity
@Table(name = "categories")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "category")
public abstract class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    protected String name;
    @Column(name = "total")
    protected long total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category(String name, long initValue) {
        this.name = name;
        total = initValue;
    }

    public Category(String name) {
        total = 0;
        this.name = name;
    }

    public Category() {
    }

    abstract public void put(long value);

    public long getTotal() {
        return total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
