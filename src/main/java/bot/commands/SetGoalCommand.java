package bot.commands;

import bot.Formatter;
import bot.StateManager;
import bot.categories.TodoCategory;
import bot.dao.operations.MergeOperation;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class SetGoalCommand extends Command {
    public SetGoalCommand(StateManager stateManager) {
        super(stateManager);
    }

    public SetGoalCommand(StateManager stateManager, String textMessage) {
        super(stateManager, textMessage);
    }

    @Override
    public SendMessage execute() {
        if (stateManager.getDialogState() == StateManager.DialogState.waitNothing) {
            stateManager.setDialogState(StateManager.DialogState.waitParameter);
            stateManager.setBufferedCommandName("set goal");
            stateManager.setBdOperation(new MergeOperation());
            return getInfo();
        }

        var splitMessageText = textMessage.split("\\s");

        var messageBuilder = SendMessage.builder().chatId(stateManager.getChatID());

        var splittedMessage = textMessage.split("\\s");
        if (!validateInput(splittedMessage[1])) {
            messageBuilder.text("input correct goal value");
        } else {
            var name = splitMessageText[0];
            var goal = Long.parseLong(splitMessageText[1]);
            stateManager.setDialogState(StateManager.DialogState.waitNothing);
            stateManager.getTakenCategoryManager().addCategory(name, goal);

            var preparedAnswer = Formatter.formatCategoryManagerTotal(stateManager.getTakenCategoryManager());
            messageBuilder
                    .text(String.format("Congrats! you create the goal!\n%s", preparedAnswer))
                    .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()));
        }
        stateManager.setBdOperation(new MergeOperation());
        return messageBuilder.build();
    }

    @Override
    public SendMessage getInfo() {
        return SendMessage.builder()
                .chatId(stateManager.getChatID())
                .text("input desired goal name and money amount")
                .build();
    }

    private boolean validateInput(String a){
        try{
            var b = Long.parseLong(a);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
