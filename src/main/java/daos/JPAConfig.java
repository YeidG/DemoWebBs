package daos;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConfig {

    private static final String PERSISTENCE_UNIT_NAME = "myPersistenceUnit";
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static EntityManagerFactory getEntityManagerFactory(){
        return factory;
    }

    public static void shutdown(){
        if (factory != null){
            factory.close();
        }
    }
}

