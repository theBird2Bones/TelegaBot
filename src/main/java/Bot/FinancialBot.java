package Bot;


import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

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
    public void onUpdateReceived(org.telegram.telegrambots.meta.api.objects.Update update) {
        var command = update.getMessage().getText();
        long chatId = update.message().chat().id();
        if(command.equals("/about")){
            System.out.println("JOPA");
            var message = new SendMessage("460586723","HOHOHO");

        }



    }
}
