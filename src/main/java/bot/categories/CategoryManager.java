package bot.categories;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "category_managers")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "category_manager")
public abstract class CategoryManager {

    @Id
    @Column(name = "category_manager_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    protected String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "category_manager_id")
    protected List<Category> categories;

    public CategoryManager() { }

    public CategoryManager(String name) {
        this.name = name;
        categories = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    abstract public void addCategory(String name);

    public void removeCategoryWithIndex(int index) {
        categories.remove(index);
    }

    public long getTotal() {
        var iter = categories.iterator();
        var total = 0l;
        while (iter.hasNext()) {
            total += iter.next().getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return name;
    }
}
