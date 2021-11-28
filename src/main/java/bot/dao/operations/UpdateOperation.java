package bot.dao.operations;

import bot.StateManager;
import bot.dao.StateManagerDao;

public class UpdateOperation implements BdOperation {

    @Override
    public void performOperation(StateManager stateManager) {
        StateManagerDao.update(stateManager);
    }
}
