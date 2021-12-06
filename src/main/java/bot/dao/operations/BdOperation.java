package bot.dao.operations;

import bot.StateManager;

public interface BdOperation {
    void performOperation(StateManager stateManager);
}
