package gs.utility;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class hibernateSessionFactory {

    @Autowired
    private static EntityManagerFactory entityManagerFactory;

    public static Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }
}