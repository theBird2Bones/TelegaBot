package bot.commands;

import bot.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class Command {
    protected StateManager stateManager;
    protected String textMessage;

    public Command(StateManager stateManager) {
        this(stateManager, null);
    }

    public Command(StateManager stateManager, String textMessage) {
        this.stateManager = stateManager;
        this.textMessage = textMessage;
    }

    abstract public SendMessage execute();
    abstract public SendMessage getInfo();
}










