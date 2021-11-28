package db;

import bot.StateManager;
import bot.dao.StateManagerDao;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.StateManagerComparator;

public class DataBaseTests {
    static StateManager initStateManager;
    @BeforeAll
    public static void initBD(){
        initStateManager = new StateManager("testStateManager","1");
        StateManagerDao.persist(initStateManager);
    }

    @AfterEach
    public void makeDefault(){
        initStateManager = new StateManager("testStateManager","1");
        StateManagerDao.update(initStateManager);
    }

    @Test
    public void dbContainsStateManagerSimpleTest(){
        var st = StateManagerDao.findById(initStateManager.getChatID());
        Assert.assertEquals(st.getChatID(), initStateManager.getChatID());
    }

    @Test
    public void dbUpdateAfterAddedNewCategory(){
        initStateManager.getTakenAccount().getCategoryManagers().get(0).addCategory("testCategory");
        StateManagerDao.update(initStateManager);
        var st = StateManagerDao.findById(initStateManager.getChatID());
        Assert.assertEquals(StateManagerComparator.isEqual(initStateManager, st), true);
    }

    @Test
    public void dbUpdateAfterPut(){
        initStateManager.getTakenAccount().getCategoryManagers().get(0).addCategory("testCategory");
        initStateManager.getTakenAccount().getCategoryManagers().get(0).getCategories().get(0).put(1600);
        StateManagerDao.update(initStateManager);
        var st = StateManagerDao.findById(initStateManager.getChatID());
        Assert.assertEquals(st.getTakenAccount().getCategoryManagers().get(0).getCategories().get(0).getTotal(),
                initStateManager.getTakenAccount().getCategoryManagers().get(0).getCategories().get(0).getTotal());
    }

    @Test
    public void dbUpdateAfterDelete(){
        initStateManager.getTakenAccount().getCategoryManagers().get(0).addCategory("testCategory1");
        StateManagerDao.update(initStateManager);
        initStateManager.getTakenAccount().getCategoryManagers().get(0).removeCategoryWithIndex(0);
        StateManagerDao.update(initStateManager);
        var st = StateManagerDao.findById(initStateManager.getChatID());
        Assert.assertEquals(StateManagerComparator.isEqual(initStateManager,st), true);
    }

    @Test
    public void dbUpdateAfterRename(){
        initStateManager.getTakenAccount().getCategoryManagers().get(0).addCategory("testCategory1");
        StateManagerDao.update(initStateManager);
        initStateManager.getTakenAccount().getCategoryManagers().get(0).getCategories().get(0).setName("testAnother1");
        StateManagerDao.update(initStateManager);
        
        var st = StateManagerDao.findById(initStateManager.getChatID());
        Assert.assertEquals(StateManagerComparator.isEqual(initStateManager,st), true);
    }
}
