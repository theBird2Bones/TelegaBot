package general;

import bot.StateManager;
import bot.categories.WithdrawalCategory;
import bot.commands.CloseCommand;
import bot.commands.CommandTree;
import bot.commands.PutCommand;
import bot.commands.SetGoalCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CategoryComparator;

public class CloseCommandTest {
    CommandTree.Builder treeBuilder;
    StateManager someStateManager;
    @BeforeEach
    public void beforeEach() {
        treeBuilder = new CommandTree.Builder();
        someStateManager = new StateManager("smt", "5");
    }

    @Test
    public void CloseWithoutGoal() {
        treeBuilder
                .setCommand("close", command -> stateManager -> new CloseCommand(stateManager, command))
                .setCommand("set goal", command -> stateManager -> new SetGoalCommand(stateManager, command));

        var tree = treeBuilder.build();
        someStateManager.setTakenCategoryManager(someStateManager.getTakenAccount().getCategoryManagers().get(2));
        tree.getCommand("close").apply("close").apply(someStateManager).execute();

        Assertions.assertEquals(
                "you should set goal firstly!",
                tree.getCommand(someStateManager.getBufferedCommandName())
                        .apply("1").apply(someStateManager).execute().getText());
    }

    @Test
    public void CloseWhenGoalIsNotZero() {
        treeBuilder
                .setCommand("close", command -> stateManager -> new CloseCommand(stateManager, command))
                .setCommand("set goal", command -> stateManager -> new SetGoalCommand(stateManager, command));

        var tree = treeBuilder.build();
        someStateManager.setTakenCategoryManager(someStateManager.getTakenAccount().getCategoryManagers().get(2));

        tree.getCommand("set goal").apply("set goal").apply(someStateManager).execute();
        tree.getCommand(someStateManager.getBufferedCommandName()).apply("g 135").apply(someStateManager).execute();

        tree.getCommand("close").apply("close").apply(someStateManager).execute();

        Assertions.assertEquals(
                "you should complete your goal before closing.",
                tree.getCommand(someStateManager.getBufferedCommandName())
                        .apply("1").apply(someStateManager).execute().getText());
    }

    @Test
    public void CloseWhenGoalIsZero() {
        treeBuilder
                .setCommand("close", command -> stateManager -> new CloseCommand(stateManager, command))
                .setCommand("put", command -> stateManager -> new PutCommand(stateManager, command))
                .setCommand("set goal", command -> stateManager -> new SetGoalCommand(stateManager, command));

        var tree = treeBuilder.build();
        someStateManager.setTakenCategoryManager(someStateManager.getTakenAccount().getCategoryManagers().get(2));

        tree.getCommand("set goal").apply("set goal").apply(someStateManager).execute();
        tree.getCommand(someStateManager.getBufferedCommandName()).apply("g 135").apply(someStateManager).execute();

        tree.getCommand("put").apply("put").apply(someStateManager).execute();
        tree.getCommand(someStateManager.getBufferedCommandName()).apply("1 135").apply(someStateManager).execute();

        tree.getCommand("close").apply("close").apply(someStateManager).execute();
        tree.getCommand(someStateManager.getBufferedCommandName()).apply("1").apply(someStateManager).execute();

        Assertions.assertTrue(
                someStateManager.getTakenAccount().getCategoryManagers().get(2).getCategories().size() == 0
        );
        Assertions.assertTrue(
                CategoryComparator.isEqual(
                        someStateManager.getTakenAccount().getCategoryManagers().get(1).getCategories().get(0),
                        new WithdrawalCategory("g", 135)
                )
        );

    }
}
