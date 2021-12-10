package bot.utils;

import bot.Account;
import bot.StateManager;
import bot.categories.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration =
                        new Configuration().configure();
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(WithdrawalCategory.class);
                configuration.addAnnotatedClass(DepositCategory.class);
                configuration.addAnnotatedClass(TodoCategory.class);

                configuration.addAnnotatedClass(CategoryManager.class);
                configuration.addAnnotatedClass(WithdrawalCategoryManager.class);
                configuration.addAnnotatedClass(DepositCategoryManager.class);
                configuration.addAnnotatedClass(TodoCategoryManager.class);

                configuration.addAnnotatedClass(StateManager.class);
                configuration.addAnnotatedClass(Account.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
