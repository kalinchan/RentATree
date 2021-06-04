package dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import DatabaseObjects.Customer;

@Named
@Dependent
public class CustomerDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public List<Customer> getAllCustomers(){
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
