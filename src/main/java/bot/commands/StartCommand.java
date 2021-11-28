package bot.commands;

import bot.StateManager;
import bot.dao.operations.NoOperation;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartCommand extends Command {
    public StartCommand(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public SendMessage execute() {
        return getInfo();
    }

    @Override
    public SendMessage getInfo() {
        stateManager.setBdOperation(new NoOperation());
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text("Let's start!\nUse buttons to navigate within 2 categories: Outcome and Income.")
                .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                .build();
    }
}
