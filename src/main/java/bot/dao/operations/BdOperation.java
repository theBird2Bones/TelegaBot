package bot.dao.operations;

import bot.StateManager;

public interface BdOperation {
    public void performOperation(StateManager stateManager);
}
