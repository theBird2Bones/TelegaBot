package bot.commands;

import bot.*;

public abstract class Command {
    protected StateManager stateManager;

    public Command(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    abstract public String execute();
}










