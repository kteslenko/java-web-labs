package kteslenko.lab2.hibernate;

import kteslenko.lab2.entity.Client;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {
    private static SessionFactory sessionFactory;

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            build();
        }
        return sessionFactory;
    }

    private static void build() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().build();

        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Client.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException("Couldn't create SessionFactory");
        }
    }
}
