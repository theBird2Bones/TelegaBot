package general;

import bot.StateManager;
import bot.commands.*;
import org.junit.jupiter.api.Assertions;
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
        treeBuilder.setCommand(aboutCommand, msg -> AboutCommand::new);
        var tree = treeBuilder.build();
        Assertions.assertEquals(
                tree.getCommand(aboutCommand).apply(aboutCommand).apply(someStateManager).execute(),
                new AboutCommand(someStateManager).execute());
    }

    @Test
    public void ContainTwoSimpleCommandTest() {
        treeBuilder.setCommand(aboutCommand, msg -> AboutCommand::new);
        treeBuilder.setCommand(helpCommand, msg -> HelpCommand::new);
        var tree = treeBuilder.build();
        Assertions.assertEquals(
                tree.getCommand(aboutCommand).apply(aboutCommand).apply(someStateManager).execute(),
                new AboutCommand(someStateManager).execute());
        Assertions.assertEquals(
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
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void ContainCommandsWithEqualTokens() {
        treeBuilder.setCommand(getTreeCommand, msg -> GetTreeCommand::new);
        treeBuilder.setCommand(getTotalCommand, msg -> GetTotalCommand::new);
        var tree = treeBuilder.build();
        Assertions.assertEquals(
                tree.getCommand(getTotalCommand).apply(getTotalCommand).apply(someStateManager).execute(),
                new GetTotalCommand(someStateManager).execute()
        );
        Assertions.assertEquals(
                tree.getCommand(getTreeCommand).apply(getTreeCommand).apply(someStateManager).execute(),
                new GetTreeCommand(someStateManager).execute()
        );
    }
}
