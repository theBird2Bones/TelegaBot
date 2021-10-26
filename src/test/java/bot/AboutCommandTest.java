package bot;
import bot.commands.AboutCommand;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AboutCommandTest {
    @Test
    public void AboutTest(){
        var stateManager = new StateManager("Current Account");
        assertEquals("Creators: Konstantin & Mariia",
                new AboutCommand(stateManager).execute());
    }
}
