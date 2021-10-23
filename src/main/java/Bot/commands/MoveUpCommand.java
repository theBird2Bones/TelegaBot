package bot.commands;

import bot.CurrentState;
import bot.StateManager;

public class MoveUpCommand extends Command{
    public MoveUpCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        stateManager.releaseCategoryManager();
        stateManager.setCurrentState(CurrentState.tookAccount);
        return String.format("You moved up to %s", stateManager.getTakenAccount());
    }
}

