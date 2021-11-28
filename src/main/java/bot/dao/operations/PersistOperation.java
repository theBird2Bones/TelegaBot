package bot.dao.operations;

import bot.StateManager;
import bot.dao.StateManagerDao;

public class PersistOperation implements BdOperation {

    @Override
    public void performOperation(StateManager stateManager) {
        StateManagerDao.persist(stateManager);
    }
}
