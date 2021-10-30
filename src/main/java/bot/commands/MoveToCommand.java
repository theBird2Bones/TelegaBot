package bot.commands;

import bot.CurrentState;
import bot.Formatter;
import bot.StateManager;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MoveToCommand extends Command {
    public MoveToCommand(StateManager stateManager, String command) {
        super(stateManager, command);
    }

    @Override
    public SendMessage execute() {
        if (stateManager.getDialogState() == StateManager.DialogState.waitNothing) {
            stateManager.setDialogState(StateManager.DialogState.waitParameter);
            stateManager.setBufferedCommand(params -> new MoveToCommand(stateManager, params).execute());
            return getInfo();
        }
        var messageBuilder = SendMessage.builder().chatId(stateManager.getChatID());
        if (!validateInput(textMessage)) {
            messageBuilder.text("input correct index");
        } else {
            var parsedIndex = Integer.parseInt(textMessage) - 1;
            var currentCategoryManager =
                    stateManager.getTakenAccount().getCategoryManagers().get(parsedIndex);
            stateManager.setTakenCategoryManager(currentCategoryManager);
            stateManager.setCurrentState(CurrentState.tookCategoryManager);
            stateManager.setDialogState(StateManager.DialogState.waitNothing);
            var preparedAnswer = String.format(
                    "You are moved to %s\n\n%s",
                    currentCategoryManager,
                    Formatter.formatCategoryManagerContent(stateManager.getTakenCategoryManager()));
            messageBuilder
                    .text(preparedAnswer)
                    .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()));
        }
        return messageBuilder.build();
    }

    @Override
    public SendMessage getInfo() {
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text("input desired category index")
                .build();
    }

    private boolean validateInput(String a) {
        try {
            var b = Integer.parseInt(a);
            return b >= 0 && b <= stateManager.getTakenAccount().getCategoryManagers().size();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
