package bot.commands;
import bot.StateManager;

public class GetTreeCommand extends Command{
    public GetTreeCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        var account = stateManager.getTakenAccount();
        var tree = "Current account: " + account.getName() + "\n" + "\n";

        for (var categoryManager : account.getCategoryManagers()){
            tree += "   " + categoryManager.getName() + ":\n" + "\n";

            if (categoryManager.getCategories().size() != 0) {
                var i = 1;
                for (var category : categoryManager.getCategories()) {
                    tree += "       " + i + ") " + category.getName() + " : " + category.getTotal() + "\n";
                    i++;
                }
            } else{
                tree += "       There are not any categories" + "\n";
            }

            tree += "\n" + "   " +"Total: "+ categoryManager.getTotal() + "\n" + "\n";

        }
        tree += "Account total: " + account.getTotal();
        return tree;
    }
}