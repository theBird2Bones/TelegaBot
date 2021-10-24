package bot.commands;

import bot.Formatter;
import bot.StateManager;
import java.util.regex.Pattern;

public class PutCommand extends Command {
    public PutCommand(StateManager stateManager, String command) {
        super(stateManager, command);
    }

    @Override
    public String execute() {
        return switch (stateManager.getCurrentState()) {
            case tookAccount -> "Please choose 'Income' or 'Outcome' category to change balance";
            case tookCategoryManager -> {
                var splittedMessageText = textMessage.split(" ");
                if (Pattern.matches("^-?\\d+?$", splittedMessageText[2])) {
                    stateManager.getTakenCategoryManager().getCategories()
                            .get(Integer.parseInt(splittedMessageText[1]) - 1)
                            .put(Long.parseLong(splittedMessageText[2]));
                    yield Formatter.formatCategoryManagerTotal(stateManager.getTakenCategoryManager());
                }
                yield "Sorry but you have to write digits only";
            }
        };
    }
}