package bot.commands;

import bot.StateManager;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class RenameCommand extends Command {
    public RenameCommand(StateManager stateManager, String command) {
        super(stateManager, command);
    }

    @Override
    public SendMessage execute() {
        if (stateManager.getDialogState() == StateManager.DialogState.waitNothing) {
            if(stateManager.getTakenCategoryManager().getCategories().size() == 0){
                return SendMessage.builder()
                        .text("before renaming you have to create")
                        .chatId(stateManager.getChatID())
                        .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                        .build();
            }
            stateManager.setDialogState(StateManager.DialogState.waitParameter);
            stateManager.setBufferedCommandName("rename");
            return getInfo();
        }
        var splitterCommand = textMessage.split(" ");
        var messageBuilder = SendMessage.builder()
                .chatId(stateManager.getChatID());
        if(!validateInput(splitterCommand[0])){
            messageBuilder.text("input correct index and write name again");
        } else {
            var takenCategoryManagerCategories =
                    stateManager.getTakenCategoryManager().getCategories();
            var parsedIndex = Integer.parseInt(splitterCommand[0]) - 1;
            var deletedName =
                    takenCategoryManagerCategories.get(parsedIndex).getName();
            takenCategoryManagerCategories.get(parsedIndex).setName(splitterCommand[1]);
            stateManager.setDialogState(StateManager.DialogState.waitNothing);
            var newName = stateManager.getTakenCategoryManager().getCategories() .get(parsedIndex).getName();
            var preparedAnswer = String.format("you've renamed %s into %s", deletedName, newName);
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
                .text("input desired category index and its new name")
                .build();
    }

    private boolean validateInput(String a) {
        try{
            var c = Long.parseLong(a);
            return c >= 0 && c <= stateManager.getTakenCategoryManager().getCategories().size();
        } catch (NumberFormatException e){
            return false;
        }
    }
}
