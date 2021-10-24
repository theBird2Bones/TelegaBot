package bot.commands;

import bot.StateManager;

public class DeleteCommand extends Command{
    public DeleteCommand(StateManager stateManager, String command){
        super(stateManager, command);
    }

    @Override
    public String execute() {
        var splitterCommand = textMessage.split(" ");
        return switch (stateManager.getCurrentState()){
            case tookAccount -> "Sorry, but you can delete only categories in 'Income' and 'Outcome'";
            case tookCategoryManager -> {
                var deletedName = stateManager.getTakenCategoryManager().getCategories()
                        .get(Integer.parseInt(splitterCommand[1])-1).getName();
                stateManager.getTakenCategoryManager().removeCategoryWithIndex(Integer.parseInt(splitterCommand[1])-1);
                yield String.format("you've deleted %s",deletedName);
            }
        };
    }
}
