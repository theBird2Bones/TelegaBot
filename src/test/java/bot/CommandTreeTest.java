package bot;

import bot.commands.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandTreeTest {
    CommandTree.Builder treeBuilder;
    StateManager someStateManager;
    String aboutMessage = "/about";
    String helpMessage = "/help";
    String moveToMessage = "move to 1";
    String getTotalMessage = "get total";
    String getTreeMessage = "get tree";

    @Before
    public void beforeEach(){
        treeBuilder = new CommandTree.Builder();
        someStateManager = new StateManager("smt");
    }

    @Test
    public void ContainOneSimpleCommandTest() {
        treeBuilder.setCommand(aboutMessage, msg -> stateManager -> new AboutCommand(stateManager));
        var tree = treeBuilder.build();
        Assert.assertEquals(
                tree.getCommand(aboutMessage).apply(aboutMessage).apply(someStateManager).execute(),
                new AboutCommand(someStateManager).execute());
    }

    @Test
    public void ContainTwoSimpleCommandTest(){
        treeBuilder.setCommand(aboutMessage, msg -> stateManager -> new AboutCommand(stateManager));
        treeBuilder.setCommand(helpMessage, msg -> stateManager -> new HelpCommand(stateManager));
        var tree = treeBuilder.build();
        Assert.assertEquals(
                tree.getCommand(aboutMessage).apply(aboutMessage).apply(someStateManager).execute(),
                new AboutCommand(someStateManager).execute());
        Assert.assertEquals(
                tree.getCommand(helpMessage).apply(helpMessage).apply(someStateManager).execute(),
                new HelpCommand(someStateManager).execute());
    }

    @Test
    public void ContainOneDifficultCommandTest(){
        treeBuilder.setCommand(moveToMessage, msg -> stateManager -> new MoveToCommand(stateManager,msg));
        var tree = treeBuilder.build();
        var expected = new MoveToCommand(someStateManager, moveToMessage).execute();
        someStateManager.releaseCategoryManager();
        var actual = tree.getCommand(moveToMessage).apply(moveToMessage).apply(someStateManager).execute();
        Assert.assertEquals( expected, actual );
    }

    @Test
    public void ContainCommandsWithEqualTokens(){
        treeBuilder.setCommand(getTreeMessage, msg -> stateManager -> new GetTreeCommand(stateManager));
        treeBuilder.setCommand(getTotalMessage, msg -> stateManager -> new GetTotalCommand(stateManager));
        var tree = treeBuilder.build();
        Assert.assertEquals(
                tree.getCommand(getTotalMessage).apply(getTotalMessage).apply(someStateManager).execute(),
                new GetTotalCommand(someStateManager).execute()
        );
        Assert.assertEquals(
                tree.getCommand(getTreeMessage).apply(getTreeMessage).apply(someStateManager).execute(),
                new GetTreeCommand(someStateManager).execute()
        );
    }
}
