package bot.dao;

import bot.StateManager;
import bot.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import java.util.function.BiConsumer;

public class StateManagerDao {
    public static StateManager findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(StateManager.class, id);
    }

    public static StateManager findById(String id) {
        return findById(Long.parseLong(id));
    }

    private static void performOperation(StateManager stateManager, BiConsumer<Session, StateManager> operation) {
        var session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        try {
            operation.accept(session, stateManager);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
    //TODO: попробовать объеднить эти методы в один, тк они практически идентичны
    public static void persist(StateManager stateManager) {
        performOperation(stateManager, (Session::persist));
    }

    public static void merge(StateManager stateManager) {
        performOperation(stateManager, (Session::merge));
    }

    public static void update(StateManager stateManager){
        performOperation(stateManager, (Session::update));
    }
}
