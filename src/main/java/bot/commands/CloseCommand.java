package bot.commands;

import bot.StateManager;
import bot.categories.TodoCategory;
import bot.dao.operations.MergeOperation;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CloseCommand extends Command {
    public CloseCommand(StateManager stateManager) {
        super(stateManager);
    }

    public CloseCommand(StateManager stateManager, String textMessage) {
        super(stateManager, textMessage);
    }

    @Override
    public SendMessage execute() {
        if (stateManager.getDialogState() == StateManager.DialogState.waitNothing) {
            stateManager.setDialogState(StateManager.DialogState.waitParameter);
            stateManager.setBufferedCommandName("close");

            stateManager.setBdOperation(new MergeOperation());

            return getInfo();
        }

        var messageBuilder = SendMessage.builder().chatId(stateManager.getChatID());
        if (!validateInput(textMessage)) {
            messageBuilder.text("input correct index");
        } else {
            var parsedIndex = Integer.parseInt(textMessage) - 1;
            var currentCategory = (TodoCategory)
                    stateManager.getTakenCategoryManager().getCategories().get(parsedIndex);
            if (stateManager.getTakenCategoryManager().getCategories().size() == 0) {
                messageBuilder.replyMarkup(
                                Keyboard.createKeyboardMarkUp(
                                        stateManager.getTakenCategoryManager().getAvailableButtonNames()))
                        .text("you should set goal firstly!");
                stateManager.setDialogState(StateManager.DialogState.waitNothing);

            } else if (currentCategory.getTotal() != 0) {
                messageBuilder.replyMarkup(Keyboard.createKeyboardMarkUp(
                                stateManager.getTakenCategoryManager().getAvailableButtonNames()))
                        .text("you should complete your goal before closing.");
                stateManager.setDialogState(StateManager.DialogState.waitNothing);
            } else {
                messageBuilder.replyMarkup(
                                Keyboard.createKeyboardMarkUp(stateManager.getTakenCategoryManager().getAvailableButtonNames()))
                        .text("congrats! you close your goal! Now it's moved to Outcome");
                stateManager.getTakenAccount()
                        .getCategoryManagers().get(1)
                        .addCategory(currentCategory.getName(), currentCategory.getGoalAmount());
                stateManager.getTakenCategoryManager().removeCategoryWithIndex(parsedIndex);
                stateManager.setDialogState(StateManager.DialogState.waitNothing);
            }
        }
        stateManager.setBdOperation(new MergeOperation());
        return messageBuilder.build();
    }

    @Override
    public SendMessage getInfo() {
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text("input goal index you want to close.")
                .build();
    }

    private boolean validateInput(String a) {
        try {
            var b = Integer.parseInt(a) - 1;
            return b >= 0 && b <= stateManager.getTakenAccount().getCategoryManagers().size();
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
