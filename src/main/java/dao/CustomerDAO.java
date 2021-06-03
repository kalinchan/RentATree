package dao;

import DatabaseObjects.Customer;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
public class CustomerDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public Exception saveCustomer(Customer customer){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
            logger.log(Level.SEVERE, e.toString());
            return e;
        }
        return null;
    }

    public Exception removeCustomerByEmail(String email){
        try{
            entityManager.createQuery("DELETE FROM Customer customer WHERE customer.Email =:email")
                    .setParameter(email, email)
                    .executeUpdate();
        } catch (Exception e){
            Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
            logger.log(Level.SEVERE, e.toString());
            return e;
        }
        return null;
    }
}
