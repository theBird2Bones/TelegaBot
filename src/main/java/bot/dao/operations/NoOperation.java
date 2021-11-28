package bot.dao.operations;

import bot.StateManager;

public class NoOperation implements BdOperation{
    @Override
    public void performOperation(StateManager stateManager) {}
}
