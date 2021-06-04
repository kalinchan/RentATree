package dao;

import DatabaseObjects.Customer;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

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
    public List<Customer> getAllCustomers{
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	List<Customer> results = entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
	entityManager.close();
	return results;
    }

    public void setHitAndMiss(int id, int hit, int miss) {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	entityManager.getTransaction().begin();
	Query query = entityManager.createQuery("UPDATE Customer c SET c.SuccessCount=:hit , c.FailCount=:miss WHERE c.CustomerID=:id");
	query.setParameter("id", id);
	query.setParameter("hit", hit);
	query.setParameter("miss", miss);
	query.executeUpdate();
	entityManager.getTransaction().commit();
	entityManager.close();
    }
}
