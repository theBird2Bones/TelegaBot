package bot;
import bot.StateManager;
import bot.commands.GetTreeCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetTreeCommandTest {
    @Test
    public void EmptyTreeTest(){
        var stateManager = new StateManager("Current Account", "2523434");
        var account = stateManager.getTakenAccount();
        assertEquals(String.format("Current account: %s\n\n" +
                                "   %s:\n\n" +
                                "       There are no categories\n\n" +
                                "   Total: %d\n\n" +
                                "   %s:\n\n" +
                                "       There are no categories\n\n" +
                                "   Total: %d\n\n" +
                                "Account total: %d",
                        account.getName(),
                        account.getCategoryManagers().get(0).getName(),
                        account.getCategoryManagers().get(0).getTotal(),
                        account.getCategoryManagers().get(1).getName(),
                        account.getCategoryManagers().get(1).getTotal(),
                        account.getTotal()),
                new GetTreeCommand(stateManager).execute().getText());
    }

    @Test
    public void OneIncomeTreeTest(){
        var stateManager = new StateManager("Current Account", "23434");
        var account = stateManager.getTakenAccount();

        account.getCategoryManagers().get(0).addCategory("пиво");
        account.getCategoryManagers().get(0).getCategories().get(0).put(45);

        assertEquals(String.format("Current account: %s\n\n" +
                                "   %s:\n\n" +
                                "       1) %s : %d\n\n" +
                                "   Total: %d\n\n" +
                                "   %s:\n\n" +
                                "       There are no categories\n\n" +
                                "   Total: %d\n\n" +
                                "Account total: %d",
                        account.getName(),
                        account.getCategoryManagers().get(0).getName(),
                        account.getCategoryManagers().get(0).getCategories().get(0).getName(),
                        account.getCategoryManagers().get(0).getCategories().get(0).getTotal(),
                        account.getCategoryManagers().get(0).getTotal(),
                        account.getCategoryManagers().get(1).getName(),
                        account.getCategoryManagers().get(1).getTotal(),
                        account.getTotal()),
                new GetTreeCommand(stateManager).execute().getText());
    }

    @Test
    public void OneOutcomeTreeTest(){
        var stateManager = new StateManager("Current Account", "23434");
        var account = stateManager.getTakenAccount();

        account.getCategoryManagers().get(1).addCategory("комп");
        account.getCategoryManagers().get(1).getCategories().get(0).put(12000);

        assertEquals(String.format("Current account: %s\n\n" +
                                "   %s:\n\n" +
                                "       There are no categories\n\n" +
                                "   Total: %d\n\n" +
                                "   %s:\n\n" +
                                "       1) %s : %d\n\n"+
                                "   Total: %d\n\n" +
                                "Account total: %d",
                        account.getName(),
                        account.getCategoryManagers().get(0).getName(),
                        account.getCategoryManagers().get(0).getTotal(),
                        account.getCategoryManagers().get(1).getName(),
                        account.getCategoryManagers().get(1).getCategories().get(0).getName(),
                        account.getCategoryManagers().get(1).getCategories().get(0).getTotal(),
                        account.getCategoryManagers().get(1).getTotal(),
                        account.getTotal()),
                new GetTreeCommand(stateManager).execute().getText());
    }

    @Test
    public void OneIncomeOneOutcomeTreeTest(){
        var stateManager = new StateManager("Current Account", "23434");
        var account = stateManager.getTakenAccount();

        account.getCategoryManagers().get(0).addCategory("пиво");
        account.getCategoryManagers().get(0).getCategories().get(0).put(45);
        account.getCategoryManagers().get(1).addCategory("комп");
        account.getCategoryManagers().get(1).getCategories().get(0).put(12000);

        assertEquals(String.format("Current account: %s\n\n" +
                                "   %s:\n\n" +
                                "       1) %s : %d\n\n" +
                                "   Total: %d\n\n" +
                                "   %s:\n\n" +
                                "       1) %s : %d\n\n"+
                                "   Total: %d\n\n" +
                                "Account total: %d",
                        account.getName(),
                        account.getCategoryManagers().get(0).getName(),
                        account.getCategoryManagers().get(0).getCategories().get(0).getName(),
                        account.getCategoryManagers().get(0).getCategories().get(0).getTotal(),
                        account.getCategoryManagers().get(0).getTotal(),
                        account.getCategoryManagers().get(1).getName(),
                        account.getCategoryManagers().get(1).getCategories().get(0).getName(),
                        account.getCategoryManagers().get(1).getCategories().get(0).getTotal(),
                        account.getCategoryManagers().get(1).getTotal(),
                        account.getTotal()),
                new GetTreeCommand(stateManager).execute().getText());
    }

    @Test
    public void MoreIncomeMoreOutcomeTreeTest(){
        var stateManager = new StateManager("Current Account", "23434");
        var account = stateManager.getTakenAccount();

        account.getCategoryManagers().get(0).addCategory("пиво");
        account.getCategoryManagers().get(0).getCategories().get(0).put(45);
        account.getCategoryManagers().get(0).addCategory("уник");
        account.getCategoryManagers().get(0).getCategories().get(1).put(1000);


        account.getCategoryManagers().get(1).addCategory("комп");
        account.getCategoryManagers().get(1).getCategories().get(0).put(12000);
        account.getCategoryManagers().get(1).addCategory("продукты на ужин");
        account.getCategoryManagers().get(1).getCategories().get(1).put(150);

        assertEquals(String.format("Current account: %s\n\n" +
                                "   %s:\n\n" +
                                "       1) %s : %d\n" +
                                "       2) %s : %d\n\n" +
                                "   Total: %d\n\n" +
                                "   %s:\n\n" +
                                "       1) %s : %d\n"+
                                "       2) %s : %d\n\n"+
                                "   Total: %d\n\n" +
                                "Account total: %d",
                        account.getName(),
                        account.getCategoryManagers().get(0).getName(),
                        account.getCategoryManagers().get(0).getCategories().get(0).getName(),
                        account.getCategoryManagers().get(0).getCategories().get(0).getTotal(),
                        account.getCategoryManagers().get(0).getCategories().get(1).getName(),
                        account.getCategoryManagers().get(0).getCategories().get(1).getTotal(),
                        account.getCategoryManagers().get(0).getTotal(),
                        account.getCategoryManagers().get(1).getName(),
                        account.getCategoryManagers().get(1).getCategories().get(0).getName(),
                        account.getCategoryManagers().get(1).getCategories().get(0).getTotal(),
                        account.getCategoryManagers().get(1).getCategories().get(1).getName(),
                        account.getCategoryManagers().get(1).getCategories().get(1).getTotal(),
                        account.getCategoryManagers().get(1).getTotal(),
                        account.getTotal()),
                new GetTreeCommand(stateManager).execute().getText());//
    }
}