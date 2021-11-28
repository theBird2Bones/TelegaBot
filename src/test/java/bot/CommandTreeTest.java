package bot;

import bot.commands.*;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandTreeTest {
    CommandTree.Builder treeBuilder;
    StateManager someStateManager;
    String aboutCommand = "about";
    String helpCommand = "help";
    String moveToCommand = "move to";
    String getTotalCommand = "get total";
    String getTreeCommand = "get tree";

    @BeforeEach
    public void beforeEach() {
        treeBuilder = new CommandTree.Builder();
        someStateManager = new StateManager("smt", "224434");
    }

    @Test
    public void ContainOneSimpleCommandTest() {
        treeBuilder.setCommand(aboutCommand, msg -> stateManager -> new AboutCommand(stateManager));
        var tree = treeBuilder.build();
        Assert.assertEquals(
                tree.getCommand(aboutCommand).apply(aboutCommand).apply(someStateManager).execute(),
                new AboutCommand(someStateManager).execute());
    }

    @Test
    public void ContainTwoSimpleCommandTest() {
        treeBuilder.setCommand(aboutCommand, msg -> stateManager -> new AboutCommand(stateManager));
        treeBuilder.setCommand(helpCommand, msg -> stateManager -> new HelpCommand(stateManager));
        var tree = treeBuilder.build();
        Assert.assertEquals(
                tree.getCommand(aboutCommand).apply(aboutCommand).apply(someStateManager).execute(),
                new AboutCommand(someStateManager).execute());
        Assert.assertEquals(
                tree.getCommand(helpCommand).apply(helpCommand).apply(someStateManager).execute(),
                new HelpCommand(someStateManager).execute());
    }

    @Test
    public void ContainOneDifficultCommandTest() {
        treeBuilder.setCommand(moveToCommand, msg -> stateManager -> new MoveToCommand(stateManager, msg));
        var tree = treeBuilder.build();
        var expected = new MoveToCommand(someStateManager, moveToCommand + "1").execute();
        someStateManager.setDialogState(StateManager.DialogState.waitNothing);
        var actual = tree.getCommand(moveToCommand).apply(moveToCommand + "1").apply(someStateManager).execute();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void ContainCommandsWithEqualTokens() {
        treeBuilder.setCommand(getTreeCommand, msg -> stateManager -> new GetTreeCommand(stateManager));
        treeBuilder.setCommand(getTotalCommand, msg -> stateManager -> new GetTotalCommand(stateManager));
        var tree = treeBuilder.build();
        Assert.assertEquals(
                tree.getCommand(getTotalCommand).apply(getTotalCommand).apply(someStateManager).execute(),
                new GetTotalCommand(someStateManager).execute()
        );
        Assert.assertEquals(
                tree.getCommand(getTreeCommand).apply(getTreeCommand).apply(someStateManager).execute(),
                new GetTreeCommand(someStateManager).execute()
        );
    }
}
