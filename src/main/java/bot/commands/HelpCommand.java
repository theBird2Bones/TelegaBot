package bot.commands;

import bot.StateManager;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class HelpCommand extends Command{
    public HelpCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public SendMessage execute() {
        return getInfo();
    }

    @Override
    public SendMessage getInfo() {
        var preparedAnswer =  "That I can do:" +
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
                "\nget tree - Get tree";
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text(preparedAnswer)
                .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                .build();
    }
}

