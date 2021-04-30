package beans;

import DatabaseObjects.Tree;
import dao.TreeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TreeBean implements Serializable {

    @Inject
    TreeDAO treeDAO;

    private List<Tree> trees;
    private Tree selectedTree;

    @PostConstruct
    public void init(){
        trees = treeDAO.getAllTrees();
    }

    public List<Tree> getTrees(){
        return trees;
    }

    public Tree getSelectedTree(){
        return selectedTree;
    }

    public void setSelectedTree(Tree selectedTree){
        this.selectedTree = selectedTree;
    }
}
