package bot.commands;

import bot.StateManager;

public class HelpCommand extends Command{
    public HelpCommand(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public String execute() {
        return "That I can do:" +
                "\n/about - Show my creators" +
                "\n/help - Show the list of possible commands" +
                "\n/show content - Show content" +
                "\n/get total - Get total" +
                "\n/move to - Step in" +
                "\n/move up - Step out" +
                "\n/put - Put something" +
                "\n/create - Create something new" +
                "\n/delete - Delete something" +
                "\n/rename - Rename something" +
                "\n/get tree - Get tree";
    }
}

