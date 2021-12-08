package general;

import bot.StateManager;
import bot.keyboard.Keyboard;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static bot.CurrentState.tookAccount;
import static bot.CurrentState.tookCategoryManager;

public class StateDependentKeyboardTest {
    @Test
    public void tookAccountTest() {
        var stateManager = new StateManager("current","2323");
        stateManager.setCurrentState(tookAccount);

        Assert.assertEquals(
                Keyboard.createKeyboardMarkUp(
                        new ArrayList<>(Arrays.asList(
                                "Get total", "Get tree", "Show content", "Move to", "Help", "About"))),
                Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()));
    }

    @Test
    public void tookCategoryManagerTest() {
        var stateManager = new StateManager("current","2343");
        stateManager.setCurrentState(tookCategoryManager);

        Assert.assertEquals(
                Keyboard.createKeyboardMarkUp(
                        new ArrayList<>(Arrays.asList(
                                "Get total", "Move up", "Rename", "Create", "Put", "Delete", "Help", "About"))),
                Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()));
    }
}
