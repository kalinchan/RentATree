package hibernateTest;

import DatabaseObjects.CardDetails;
import org.junit.jupiter.api.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Date;

import static enums.Country.UNITEDKINGDOM;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetupDatabaseTest {

    //This test causes hibernate to active, creating all entity tables
    @Test
    public void hibernateCreateTables(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        assertTrue(entityManager.isOpen());
    }

    @Test
    public void cardDetailsTable(){
        java.sql.Date date = new Date(03/2021);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CardDetails cd = new CardDetails("email", "firstName", "lastName", "addressLine1", "addressLine2",
                "postcode", "city", UNITEDKINGDOM, 1, 2, date);
        entityManager.getTransaction().begin();
        entityManager.persist(cd);
    }
}
