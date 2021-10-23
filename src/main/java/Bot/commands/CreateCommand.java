package bot.commands;

import bot.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CreateCommand extends Command{
    private String command;
    public CreateCommand(StateManager stateManager, String command){
        super(stateManager);
        this.command = command;
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount -> "Sorry, but you can create categories in 'Income' or 'Outcome' only";
            case tookCategoryManager ->{
                var categoryName = Arrays.stream(command.split(" "))
                        .skip(1).collect(Collectors.joining(" ")).toString();
                stateManager.getTakenCategoryManager().addCategory(categoryName);
                yield Formatter.formatCategoryManagerContent(stateManager.getTakenCategoryManager());
            }
        };
    }
}
