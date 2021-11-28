package bot.commands;

import bot.Formatter;
import bot.StateManager;
import bot.dao.operations.MergeOperation;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class PutCommand extends Command {
    public PutCommand(StateManager stateManager, String command) {
        super(stateManager, command);
    }

    @Override
    public SendMessage execute() {
        if (stateManager.getDialogState() == StateManager.DialogState.waitNothing) {
            if(stateManager.getTakenCategoryManager().getCategories().size() == 0){
                return SendMessage.builder()
                        .text("before putting you have to create")
                        .chatId(stateManager.getChatID())
                        .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                        .build();
            }
            stateManager.setDialogState(StateManager.DialogState.waitParameter);
            stateManager.setBufferedCommandName("put");
            stateManager.setBdOperation(new MergeOperation());
            return getInfo();
        }

        var splittedMessageText = textMessage.split(" ");
        var messageBuilder = SendMessage.builder()
                .chatId(stateManager.getChatID());
        if (splittedMessageText.length == 2 && validateInput(splittedMessageText[0], splittedMessageText[1])) {
            stateManager.setDialogState(StateManager.DialogState.waitNothing);
            stateManager.getTakenCategoryManager().getCategories()
                    .get(Integer.parseInt(splittedMessageText[0]) - 1)
                    .put(Long.parseLong(splittedMessageText[1]));
            var preparedAnswer = Formatter.formatCategoryManagerTotal(stateManager.getTakenCategoryManager());
            messageBuilder
                    .text(preparedAnswer)
                    .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()));
        } else {
            messageBuilder.text("write correct input");
        }
        stateManager.setBdOperation(new MergeOperation());
        return messageBuilder.build();
    }

    @Override
    public SendMessage getInfo() {
        return SendMessage.builder()
                .chatId(stateManager.getChatID())
                .text("input two numbers: desired category index and money amount")
                .build();
    }

    private boolean validateInput(String a, String b) {
        try{
            var c = Long.parseLong(a);
            var d = Long.parseLong(b);
            return c >= 0 && c <= stateManager.getTakenCategoryManager().getCategories().size();
        } catch (NumberFormatException e){
            return false;
        }
    }
}