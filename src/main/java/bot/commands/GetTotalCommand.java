package bot.commands;

import bot.Formatter;
import bot.StateManager;
import bot.keyboard.Keyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class GetTotalCommand extends Command{
    public GetTotalCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public SendMessage execute() {
        return getInfo();
    }

    @Override
    public SendMessage getInfo() {
        var preparedAnswer =  switch (stateManager.getCurrentState()){
            case tookAccount ->
                    Formatter.formatAccountInnerCategoryManagerTotal(stateManager.getTakenAccount());
            case tookCategoryManager ->
                    Formatter.formatCategoryManagerTotal(stateManager.getTakenCategoryManager());
        };
        return SendMessage
                .builder()
                .chatId(stateManager.getChatID())
                .text(preparedAnswer)
                .replyMarkup(Keyboard.createKeyboardMarkUp(stateManager.getAvailableButtonNames()))
                .build();
    }
}
