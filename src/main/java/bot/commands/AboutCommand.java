package bot.commands;

import bot.StateManager;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class AboutCommand extends Command {
    public AboutCommand(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public SendMessage execute() {
        return getInfo();
    }

    @Override
    public SendMessage getInfo() {
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text("Creators: Konstantin & Mariia")
                .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                .build();
    }
}

