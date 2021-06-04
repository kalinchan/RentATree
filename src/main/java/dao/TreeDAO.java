package dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import DatabaseObjects.Tree;
import DatabaseObjects.TreeStock;

@Named
@Dependent
public class TreeDAO implements Serializable {

	public List<Tree> getAllTrees() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Tree> results = entityManager.createQuery("SELECT t FROM Tree t", Tree.class).getResultList();
		entityManager.close();
		return results;
	}

	public List<Tree> searchTrees(String attribute, String attributeValue) {
		List<Tree> results = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		switch (attribute) {
		case "type":
		case "material":
			results = (entityManager
					.createQuery("SELECT t FROM Tree t WHERE t." + attribute + ".name = :value", Tree.class)
					.setParameter("value", attributeValue).getResultList());
			break;
		case "Height":
			results = (entityManager.createQuery("SELECT t FROM Tree t WHERE t." + attribute + " = :value", Tree.class)
					.setParameter("value", Integer.parseInt(attributeValue)).getResultList());
			break;
		case "Available":
			results = (entityManager.createQuery("SELECT t FROM Tree t WHERE t." + attribute + " = :value", Tree.class)
					.setParameter("value", getTreeStockEquivalent(attributeValue)).getResultList());
			break;
		default:
			break;
		}
		entityManager.close();
		return results;

	}

	public List<Tree> searchTrees(String attribute, String attributeValue, String attribute2, String attributeValue2) {
		List<Tree> results = null;
		// if both attributes are the same
		if (attribute.equals(attribute2)) {
			return searchTrees(attribute, attributeValue);
		}
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String option = "";
		String option2 = "";
		if (attribute.equals("type") || attribute.equals("material")) {
			option = ".name";
		}
		if (attribute2.equals("material") || attribute2.equals("type")) {
			option2 = ".name";
		}
		TypedQuery<Tree> query = entityManager.createQuery("SELECT t FROM Tree t WHERE t." + attribute + option
				+ " = :value AND t." + attribute2 + option2 + " = :value2", Tree.class);

		// if attribute is type or material then option param = .name else option param
		// = ""
		// if available then value = gettreestockequivalent
		if (attribute.equals("type") || attribute.equals("material")) {
			query.setParameter("value", attributeValue);
		}
		if (attribute2.equals("material") || attribute2.equals("type")) {
			query.setParameter("value2", attributeValue2);
		}
		if (attribute.equals("Available")) {
			query.setParameter("value", getTreeStockEquivalent(attributeValue));
		}

		if (attribute2.equals("Available")) {
			query.setParameter("value2", getTreeStockEquivalent(attributeValue2));
		}

		if (attribute.equals("Height")) {
			query.setParameter("value", Integer.parseInt(attributeValue));

		}
		if (attribute2.equals("Height")) {
			query.setParameter("value2", Integer.parseInt(attributeValue2));

		}
		results = query.getResultList();
		entityManager.close();
		return results;
	}

	public TreeStock getTreeStockEquivalent(String value) {
		if (value.equalsIgnoreCase("true"))
			return TreeStock.INSTOCK;
		return TreeStock.OUTOFSTOCK;
	}

	public Tree getTreeByID(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("SELECT t FROM Tree t WHERE t.TreeID=:id", Tree.class);
		query.setParameter("id", id);
		Tree result = (Tree) query.getSingleResult();
		entityManager.close();
		return result;
	}

	public void setTreeStock(int id, int amount) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("UPDATE Tree t SET t.Stock=:amount WHERE t.TreeID=:id");
		query.setParameter("amount", amount);
		query.setParameter("id", id);
		query.executeUpdate();
		Query query1 = entityManager.createQuery("UPDATE Tree t SET t.Available=:bool WHERE t.TreeID=:id");
		TreeStock available = amount <= 0 ? TreeStock.OUTOFSTOCK : TreeStock.INSTOCK;
		query1.setParameter("bool", available);
		query1.setParameter("id", id);
		query1.executeUpdate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
