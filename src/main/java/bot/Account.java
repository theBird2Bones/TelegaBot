package bot;

import bot.categories.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = CategoryManager.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private List<CategoryManager> categoryManagers;

    public Account() { }

    public Account(String name){
        this.name = name;
        categoryManagers = new ArrayList<>();
        categoryManagers.add(new DepositCategoryManager("Income"));
        categoryManagers.add(new WithdrawalCategoryManager("Outcome"));
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public List<CategoryManager> getCategoryManagers(){
        return categoryManagers;
    }

    public long getTotal() {
        var total = categoryManagers.stream().map(x -> x.getTotal()).reduce((a, b) -> a + b).get();
        return total;
    }

    @Override
    public String toString() {
        return name;
    }
}
