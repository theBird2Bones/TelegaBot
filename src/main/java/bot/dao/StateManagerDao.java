package bot.dao;

import bot.StateManager;
import bot.categories.Category;
import bot.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.TransactionException;
import org.hibernate.query.Query;

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
        } catch (TransactionException exception) {
            transaction.rollback();
        } catch (Exception exception) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public static void persist(StateManager stateManager) {
        performOperation(stateManager, ((session, manager) -> session.persist(manager)));
    }

    public static void merge(StateManager stateManager) {
        performOperation(stateManager, ((session, manager) -> session.merge(manager)));
    }

    public static void update(StateManager stateManager){
        performOperation(stateManager, ((session, manager) -> session.update(manager)));
    }

    public static void delete(StateManager stateManager) {
        performOperation(stateManager, ((session, manager) -> session.delete(manager)));
    }
}
