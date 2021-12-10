package bot.categories;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="todo_categories")
public class TodoCategory extends Category {
    @Column(name = "goal_amount")
    private long goalAmount;

    public TodoCategory() {
        super();
    }

    public TodoCategory(String name){
        super(name);
    }

    public TodoCategory(String name, long initValue){
        super(name);
        goalAmount = initValue;
        total = goalAmount;
    }

    @Override
    public void put(long value) {
        total = Math.max(0, total - Math.abs(value));
    }

    public long getGoalAmount(){
        return goalAmount;
    }
}
