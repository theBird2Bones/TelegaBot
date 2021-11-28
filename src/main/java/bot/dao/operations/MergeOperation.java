package bot.dao.operations;

import bot.StateManager;
import bot.dao.StateManagerDao;

public class MergeOperation implements BdOperation{
    @Override
    public void performOperation(StateManager stateManager) {
        StateManagerDao.merge(stateManager);
    }
}
