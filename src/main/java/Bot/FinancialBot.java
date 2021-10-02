package Bot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import  org.telegram.telegrambots.meta.api.objects.*;

public class FinancialBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "Financil_bot";
    }

    @Override
    public String getBotToken() {
        return "1966706011:AAHJpIam9Wj0MaJg67ZI3PDFfeeKf-F6nes";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var inMessage = update.getMessage();
        try {
            execute(getResponseToInputtedMessage(inMessage));
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage getResponseToInputtedMessage(Message message){
        var outMessage = new SendMessage();
        if(message.getText().equals("/about")){
            outMessage.setText("Creators: Konstantin & Mariia");
        }

        if(message.getText().equals("/help")){
            outMessage.setText("That I can do:\n/about - Show my creators\n/help - Show the list of possible commands");
        }
        outMessage.setChatId(message.getChatId().toString());
        return outMessage;
    }
}
