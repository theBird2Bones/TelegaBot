package bot.aod;

import bot.StateManager;
import bot.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StateManagerAOD {
    public static StateManager findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(StateManager.class, id);
    }

    public static StateManager findById(String id) {
        return findById(Long.parseLong(id));
    }

    public static void save(StateManager stateManager) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(stateManager);
        tx1.commit();
        session.close();
    }

    public static void update(StateManager stateManager) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(stateManager);
        tx1.commit();
        session.close();
    }

    public static void delete(StateManager stateManager) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(stateManager);
        tx1.commit();
        session.close();
    }
}
