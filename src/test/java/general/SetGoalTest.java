package general;

import bot.StateManager;
import bot.categories.TodoCategory;
import bot.commands.CommandTree;
import bot.commands.SetGoalCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CategoryComparator;

public class SetGoalTest {
    CommandTree.Builder treeBuilder;
    StateManager someStateManager;
    @BeforeEach
    public void beforeEach() {
        treeBuilder = new CommandTree.Builder();
        someStateManager = new StateManager("smt", "5");
    }

    @Test
    public void SetGoalTest() {
        treeBuilder.setCommand("set goal", command -> stateManager -> new SetGoalCommand(stateManager,command));
        var tree = treeBuilder.build();
        tree.getCommand("set goal").apply("set goal").apply(someStateManager).execute();
        someStateManager.setTakenCategoryManager(someStateManager.getTakenAccount().getCategoryManagers().get(2));
        tree.getCommand(someStateManager.getBufferedCommandName()).apply("g 135").apply(someStateManager).execute();
        Assertions.assertTrue(
                CategoryComparator.isEqual(
                        someStateManager.getTakenAccount().getCategoryManagers().get(2).getCategories().get(0),
                        new TodoCategory("g", 135)));
    }
}
