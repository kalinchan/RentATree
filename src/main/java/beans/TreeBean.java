package beans;

import DatabaseObjects.Tree;
import dao.TreeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class TreeBean implements Serializable {

	private static final long serialVersionUID = 1L;

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
