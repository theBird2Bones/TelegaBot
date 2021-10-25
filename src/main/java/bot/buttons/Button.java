package bot.buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Button {
    private InlineKeyboardButton createButton(String name){
        var inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(name); // почему я не могу написать так new InlineKeyboardButton().setText("Fi4a").setCallbackData("CallFi4a"); ?
        inlineKeyboardButton.setCallbackData("Button \""+ name +"\" has been pressed");
        return inlineKeyboardButton;
    }

    public SendMessage sendInLineKeyBoardMessage(Long chatId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        var firstKeyboardRow = new ArrayList(Arrays.asList
                (createButton("About"), createButton("Create"), createButton("Delete")));
        var secondKeyboardRow = new ArrayList(Arrays.asList
                (createButton("Get total"), createButton("Get tree"), createButton("Help")));
        var thirdKeyboardRow = new ArrayList(Arrays.asList
                (createButton("Move to"), createButton("Move up"), createButton("Put")));
        var fourthKeyboardRow = new ArrayList(Arrays.asList
                (createButton("Rename"), createButton("Show content")));
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList(Arrays.asList
                (firstKeyboardRow, secondKeyboardRow, thirdKeyboardRow, fourthKeyboardRow));

        inlineKeyboardMarkup.setKeyboard(keyboardRows);

        var sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Menu:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup); // почему мы не можем написать так new SendMessage().setChatId(String.valueOf(chatId)).setText("Пример").setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
