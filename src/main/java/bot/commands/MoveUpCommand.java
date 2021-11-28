package bot.commands;

import bot.CurrentState;
import bot.Formatter;
import bot.StateManager;
import bot.dao.operations.NoOperation;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MoveUpCommand extends Command{
    public MoveUpCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public SendMessage execute() {
        return getInfo();
    }

    @Override
    public SendMessage getInfo() {
        stateManager.releaseCategoryManager();
        stateManager.setCurrentState(CurrentState.tookAccount);
        stateManager.setBdOperation(new NoOperation());
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text(String.format("You moved up to %s\n\n%s",
                        stateManager.getTakenAccount(),
                        Formatter.formatAccountInnerCategoryManager(stateManager.getTakenAccount())))
                .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                .build();
    }
}

