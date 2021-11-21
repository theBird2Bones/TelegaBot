package bot.commands;

import bot.Formatter;
import bot.StateManager;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class DeleteCommand extends Command {
    public DeleteCommand(StateManager stateManager, String command) {
        super(stateManager, command);
    }

    @Override
    public SendMessage execute() {
        if (stateManager.getDialogState() == StateManager.DialogState.waitNothing) {
            if (stateManager.getTakenCategoryManager().getCategories().size() == 0) {
                return SendMessage.builder()
                        .text("before deleting you have to create")
                        .chatId(stateManager.getChatID())
                        .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                        .build();
            }
            stateManager.setDialogState(StateManager.DialogState.waitParameter);
            stateManager.setBufferedCommandName("delete");
            return getInfo();
        }

        var messageBuilder = SendMessage.builder().chatId(stateManager.getChatID());
        if (!validateInput(textMessage)) {
            messageBuilder.text("input correct index");
        } else {
            var parsedParameter = Integer.parseInt(textMessage) - 1;
            var deletedName = stateManager
                    .getTakenCategoryManager()
                    .getCategories()
                    .get(parsedParameter)
                    .getName();
            stateManager.getTakenCategoryManager().removeCategoryWithIndex(parsedParameter);
            stateManager.setDialogState(StateManager.DialogState.waitNothing);
            var preparedAnswer = String.format(
                    "you've deleted %s\n\n%s",
                    deletedName,
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
                .text("input category index")
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
