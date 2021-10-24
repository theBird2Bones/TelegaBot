package bot.commands;

import bot.StateManager;

public class RenameCommand extends Command{
    public RenameCommand(StateManager stateManager, String command){
        super(stateManager, command);
    }

    @Override
    public String execute() {
        var splitterCommand = textMessage.split(" ");
        return switch (stateManager.getCurrentState()){
            case tookAccount -> "Sorry, but you can rename only categories in 'Income' and 'Outcome'";
            case tookCategoryManager -> {
                var deletedName = stateManager.getTakenCategoryManager().getCategories()
                        .get(Integer.parseInt(splitterCommand[1])-1).getName();
                stateManager.getTakenCategoryManager().getCategories()
                        .get(Integer.parseInt(splitterCommand[1])-1).setName(splitterCommand[2]);
                var newName = stateManager.getTakenCategoryManager().getCategories()
                        .get(Integer.parseInt(splitterCommand[1])-1).getName();
                yield String.format("you've renamed %s into %s",deletedName, newName);
            }
        };
    }
}
