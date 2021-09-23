package Bot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        var message = new SendMessage();
        if(command.equals("/about")){
            System.out.println("Konstantin & Mariia");
            message.setText("Creators: Konstantin & Mariia");
        }

        if(command.equals("/help")){
            System.out.println("/about\n/help");
            message.setText("What can I do:\n/about\n/help");
        }
        message.setChatId("460586723");
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }



    }
}
