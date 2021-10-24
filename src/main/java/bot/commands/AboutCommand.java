package bot.commands;

import bot.StateManager;

public class AboutCommand extends Command {
    public AboutCommand(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public String execute() {
        return "Creators: Konstantin & Mariia";
    }
}

