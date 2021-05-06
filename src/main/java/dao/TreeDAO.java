package dao;

import DatabaseObjects.Tree;
import DatabaseObjects.TreeStock;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

@Dependent
public class TreeDAO implements Serializable {

	public List<Tree> getAllTrees() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		return (entityManager.createQuery("SELECT t FROM Tree t", Tree.class).getResultList());
	}

	public List<Tree> searchTrees(String attribute, String attributeValue) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Tree> results = null;
		switch (attribute) {
		case "type":
		case "material":
			results = (entityManager
					.createQuery("SELECT t FROM Tree t WHERE t." + attribute + ".name = :value", Tree.class)
					.setParameter("value", attributeValue).getResultList());
			break;
		case "Height":
		case "Available":
			if(Boolean.valueOf(attributeValue).equals(Boolean.TRUE)) {
				results = (entityManager.createQuery("SELECT t FROM Tree t WHERE t." + attribute + " = :value", Tree.class)
						.setParameter("value", TreeStock.INSTOCK).getResultList());
			}
			if(Boolean.valueOf(attributeValue).equals(Boolean.FALSE)) {
				results = (entityManager.createQuery("SELECT t FROM Tree t WHERE t." + attribute + " = :value", Tree.class)
						.setParameter("value", TreeStock.OUTOFSTOCK).getResultList());
			}
			break;
		default:
			break;
		}
		return results;

	}

}
