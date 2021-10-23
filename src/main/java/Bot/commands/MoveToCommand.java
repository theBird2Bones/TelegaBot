package bot.commands;

import bot.CurrentState;
import bot.StateManager;

public class MoveToCommand extends Command{
    private String command;
    public MoveToCommand(StateManager stateManager, String command){
        super(stateManager);
        this.command = command;
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount -> {
                stateManager.setCurrentState(CurrentState.tookCategoryManager);
                stateManager.setTakenCategoryManager(
                        stateManager .getTakenAccount() .getCategoryManagers()
                                .get(Integer.parseInt(command.split(" ")[2])-1));
                yield  String.format("You are moved to %s",
                        stateManager .getTakenAccount() .getCategoryManagers()
                                .get(Integer.parseInt(command.split(" ")[2])-1));
            }
            case tookCategoryManager -> "Please, move up to choose 'Income' or 'Outcome' category";
        };
    }
}
