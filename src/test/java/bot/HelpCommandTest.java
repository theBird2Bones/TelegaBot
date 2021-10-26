package bot;

import bot.commands.AboutCommand;
import bot.commands.HelpCommand;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    @Test
    public void HelpTest(){
        var stateManager = new StateManager("Current Account");
        assertEquals("That I can do:" +
                "\n/about - Show my creators" +
                "\n/help - Show the list of possible commands" +
                "\nshow content - Show content" +
                "\nget total - Get total" +
                "\nmove to - Step in" +
                "\nmove up - Step out" +
                "\nput - Put something" +
                "\ncreate - Create something new" +
                "\ndelete - Delete something" +
                "\nrename - Rename something" +
                "\nget tree - Get tree",
                new HelpCommand(stateManager).execute());
    }

}
