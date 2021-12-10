package bot.categories;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.List;

@Entity
@DiscriminatorValue("todo_category_managers")
public class TodoCategoryManager extends CategoryManager {
    public TodoCategoryManager(String name) {
        super(name);
    }

    public TodoCategoryManager() {
        super();
    }

    @Override
    public void addCategory(String name) {
        categories.add(new TodoCategory(name));
    }

    @Override
    public void addCategory(String name, long initValue) {
        categories.add(new TodoCategory(name, initValue));
    }

    @Override
    public List<String> getAvailableButtonNames() {
        return Arrays.asList("Get total", "Move up", "Rename", "Set goal", "Close", "Put", "Delete", "Help", "About");
    }

    @Override
    public long getTotal(){
        return 0;
    }
}
