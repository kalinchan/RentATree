package dao;

import DatabaseObjects.Tree;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

@Dependent
public class TreeDAO implements Serializable {

    public List<Tree> getAllTrees(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RentATree");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return (entityManager.createQuery("SELECT t FROM Tree t", Tree.class).getResultList());
    }

}
