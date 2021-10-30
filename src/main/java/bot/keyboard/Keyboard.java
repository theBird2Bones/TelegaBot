package bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

public class Keyboard {
    public static InlineKeyboardButton createButton(String name){
        var inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(name);
        inlineKeyboardButton.setCallbackData(name);
        return inlineKeyboardButton;
    }

    public static InlineKeyboardMarkup createKeyboardMarkUp(List<String> buttonNames){
        return createKeyboardMarkUp(buttonNames,3);
    }
    public static InlineKeyboardMarkup createKeyboardMarkUp(List<String> buttonNames, int rowCount){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        var keyboardRow = new ArrayList<InlineKeyboardButton>();
        var cnt = 0;
        for (var buttonName: buttonNames) {
            if(cnt >= rowCount){
                keyboard.add(new ArrayList<>(keyboardRow));
                keyboardRow.clear();
                cnt = 0;
            }
            keyboardRow.add((createButton(buttonName)));
            cnt++;
        }
        if(!keyboardRow.isEmpty()){
            keyboard.add(keyboardRow);
        }
        return new InlineKeyboardMarkup(keyboard);
    }
}
