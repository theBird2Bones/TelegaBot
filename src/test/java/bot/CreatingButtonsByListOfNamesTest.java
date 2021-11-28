package bot;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;
import static bot.keyboard.Keyboard.createButton;
import static bot.keyboard.Keyboard.createKeyboardMarkUp;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatingButtonsByListOfNamesTest {

    @Test
    public void OneButtonOneRow(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        var keyboardRow = new ArrayList<InlineKeyboardButton>();
        var inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboard);

        keyboardRow.add(createButton("b1"));
        keyboard.add(keyboardRow);

        var list = new ArrayList<String>();
        list.add("b1");
        assertEquals(inlineKeyboardMarkup, createKeyboardMarkUp(list, 1));
    }

    @Test
    public void TwoButtonsOneRow(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        var keyboardRow = new ArrayList<InlineKeyboardButton>();
        var inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboard);

        keyboardRow.add(createButton("b1"));
        keyboardRow.add(createButton("b2"));
        keyboard.add(keyboardRow);

        var list = new ArrayList<String>();
        list.add("b1");
        list.add("b2");
        assertEquals(inlineKeyboardMarkup, createKeyboardMarkUp(list, 2));
    }

    @Test
    public void TwoButtonsTwoRows(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        var firstKeyboardRow = new ArrayList<InlineKeyboardButton>();
        var secondKeyboardRow = new ArrayList<InlineKeyboardButton>();
        var inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboard);

        firstKeyboardRow.add(createButton("b1"));
        secondKeyboardRow.add(createButton("b2"));
        keyboard.add(firstKeyboardRow);
        keyboard.add(secondKeyboardRow);

        var list = new ArrayList<String>();
        list.add("b1");
        list.add("b2");
        assertEquals(inlineKeyboardMarkup, createKeyboardMarkUp(list, 1));
    }

    @Test
    public void ThreeButtonsTwoRows(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        var firstKeyboardRow = new ArrayList<InlineKeyboardButton>();
        var secondKeyboardRow = new ArrayList<InlineKeyboardButton>();
        var inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboard);

        firstKeyboardRow.add(createButton("b1"));
        firstKeyboardRow.add(createButton("b2"));
        secondKeyboardRow.add(createButton("b3"));

        keyboard.add(firstKeyboardRow);
        keyboard.add(secondKeyboardRow);

        var list = new ArrayList<String>();
        list.add("b1");
        list.add("b2");
        list.add("b3");
        assertEquals(inlineKeyboardMarkup, createKeyboardMarkUp(list, 2));
    }
}
