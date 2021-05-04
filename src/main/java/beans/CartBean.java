package beans;

import DatabaseObjects.Tree;
import dao.TreeDAO;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;


@Named
@SessionScoped
public class CartBean implements Serializable {

    private List<Tree> contents = new ArrayList<>();
    private int total;

    @Inject
    TreeDAO treeDAO;

    @PostConstruct
    public void init(){
        new CartBean();
    }

    public CartBean(){
    }

    public CartBean(List<Tree> contents, int total) {
        this.contents = contents;
        this.total = total;
    }

    public List<Tree> getContents() {
        return contents;
    }

    public void setContents(List<Tree> contents) {
        this.contents = contents;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCartSize(){
        return contents.size();
    }

    public String addTreeToCart(Tree selectedTree){
        Tree tree = treeDAO.getTreeByID(selectedTree.getTreeID());
        contents.add(selectedTree);
        return "Trees.jsf";
    }

}
