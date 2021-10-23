package bot.commands;

import bot.Formatter;
import bot.StateManager;

public class ShowContentCommand extends Command{
    public ShowContentCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount ->
                    Formatter.formatAccountInnerCategoryManager(stateManager.getTakenAccount());
            case tookCategoryManager ->
                    Formatter.formatCategoryManagerContent(stateManager.getTakenCategoryManager());
        };
    }
}
