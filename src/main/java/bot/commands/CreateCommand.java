package bot.commands;

import bot.*;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CreateCommand extends Command {
    public CreateCommand(StateManager stateManager, String command) {
        super(stateManager, command);
    }

    @Override
    public SendMessage execute() {
        if (stateManager.getDialogState() == StateManager.DialogState.waitNothing) {
            stateManager.setDialogState(StateManager.DialogState.waitParameter);
            stateManager.setBufferedCommand(params -> new CreateCommand(stateManager, params).execute());
            return getInfo();
        }
        stateManager.setDialogState(StateManager.DialogState.waitNothing);
        var categoryName = String.join(" ", textMessage.split(" "));
        stateManager.getTakenCategoryManager().addCategory(categoryName);
        var preparedAnswer = Formatter.formatCategoryManagerContent(stateManager.getTakenCategoryManager());
        return SendMessage.builder()
                .chatId(stateManager.getChatID())
                .text(preparedAnswer)
                .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                .build();
    }

    @Override
    public SendMessage getInfo() {
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text("input category name")
                .build();
    }
}
