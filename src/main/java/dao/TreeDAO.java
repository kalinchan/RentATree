package dao;

import DatabaseObjects.Tree;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Named
@Dependent
public class TreeDAO implements Serializable {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List<Tree> getAllTrees(){
        return (entityManager.createQuery("SELECT t FROM Tree t", Tree.class).getResultList());
    }

    public Tree getTreeByID(int id){
        Query query = entityManager.createQuery("SELECT t FROM Tree t WHERE t.TreeID=:id", Tree.class);
        query.setParameter("id", id);
        return (Tree) query.getSingleResult();
    }

}
