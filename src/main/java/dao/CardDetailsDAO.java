package dao;

import DatabaseObjects.CardDetails;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
public class CardDetailsDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Exception saveCardDetails(CardDetails cardDetails){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(cardDetails);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
            logger.log(Level.SEVERE, e.toString());
            return e;
        }
        return null;
    }
}
