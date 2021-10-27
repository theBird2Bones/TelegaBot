package bot.keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Keyboard {
    public InlineKeyboardButton createButton(String name){
        var inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(name); // почему я не могу написать так new InlineKeyboardButton().setText("Fi4a").setCallbackData("CallFi4a"); ?
        inlineKeyboardButton.setCallbackData("Button \""+ name +"\" has been pressed");
        return inlineKeyboardButton;
    }

    public List<List<InlineKeyboardButton>> createKeyboard(List<String> buttonNames, int rowCount){
        var cnt = 0;
        List<List<InlineKeyboardButton>> keyboard = new ArrayList();
        var keyboardRow = new ArrayList();
        for (var  buttonName :  buttonNames) {
            if(cnt > rowCount){
                keyboard.add(keyboardRow);
                keyboardRow.clear();
                cnt = 0;
            }
            else{
                keyboardRow.add((createButton(buttonName)));
                cnt++;
            }
        }
        return keyboard;
    }

    private InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    private List<String> buttonNames = new ArrayList(Arrays.asList("About", "Create", "Delete",
            "Get total", "Get tree", "Help", "Move to", "Move up", "Put", "Rename", "Show content"));

    public SendMessage sendInLineKeyBoardMessage(Long chatId){

        inlineKeyboardMarkup.setKeyboard(createKeyboard(buttonNames, 3));

        var sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Menu:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup); // почему мы не можем написать так new SendMessage().setChatId(String.valueOf(chatId)).setText("Пример").setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
