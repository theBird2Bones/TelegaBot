package Bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;



public class Command {
    public enum Name{
        about,
        help,
        getAccountTotal,
        getInnerCategoryManagers,
        getInnerCategories,
        getCategoryManagerTotal,
        getCategoryTotal,
        addCategory,

    }
    private final Name name;

    private SendMessage message;

    public Command(Name name, String sendMessageText) {
        this.name = name;

        var message = new SendMessage();
        message.setText(sendMessageText);
        this.message = message;
    }

    public Name getName() {
        return name;
    }

    public SendMessage getMessage() {
        return message;
    }
}
