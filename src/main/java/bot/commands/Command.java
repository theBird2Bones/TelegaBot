package bot.commands;

import bot.*;

public abstract class Command {
    protected StateManager stateManager;
    protected String textMessage = null;

    public Command(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public Command(StateManager stateManager, String textMessage) {
        this.stateManager = stateManager;
        this.textMessage = textMessage;
    }

    abstract public String execute();
}










