package bot.commands;

import bot.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CreateCommand extends Command{
    public CreateCommand(StateManager stateManager, String command){
        super(stateManager, command);
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()){
            case tookAccount -> "Sorry, but you can create categories in 'Income' or 'Outcome' only";
            case tookCategoryManager ->{
                var categoryName = Arrays.stream(textMessage.split(" "))
                        .skip(1).collect(Collectors.joining(" ")).toString();
                stateManager.getTakenCategoryManager().addCategory(categoryName);
                yield Formatter.formatCategoryManagerContent(stateManager.getTakenCategoryManager());
            }
        };
    }
}
