package hibernateTest;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetupDatabaseTest {

    //This test causes hibernate to active, creating all entity tables
    @Test
    public void hibernateCreateTables(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        assertTrue(entityManager.isOpen());
    }
}
