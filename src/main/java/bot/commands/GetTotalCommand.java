package bot.commands;

import bot.Formatter;
import bot.StateManager;

public class GetTotalCommand extends Command{
    public GetTotalCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount ->
                    Formatter.formatAccountInnerCategoryManagerTotal(stateManager.getTakenAccount());
            case tookCategoryManager ->
                    Formatter.formatCategoryManagerTotal(stateManager.getTakenCategoryManager());
        };
    }
}
