package beans;

import DatabaseObjects.Tree;
import dao.TreeDAO;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@SessionScoped
public class CartBean implements Serializable {

    private List<Tree> contents = new ArrayList<>();
    private int total;
    private int discount;
    private int totalAfterDiscount;

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
    }

    public List<Tree> getContents() {
        return contents;
    }

    public void setContents(List<Tree> contents) {
        this.contents = contents;
    }

    public int getCartSize(){
        return contents.size();
    }

    public String addTreeToCart(Tree selectedTree){
        Tree tree = treeDAO.getTreeByID(selectedTree.getTreeID());
        contents.add(selectedTree);
        return "Trees.jsf";
    }

    public double calculateTotal(){
        double total = 0;
        for (Tree tree : contents){
            total =+ tree.getTotalPrice(tree);
        }
        return total;
    }

    private double calculateDiscount() {
        double discount = 0;
        for(int i=1; i>contents.size();i+=2){
            Tree tree = contents.get(i);
            discount =+ tree.getTotalPrice(tree)/2;
        }
        return discount;
    }

    private double calculateTotalAfterDiscount(){
        return calculateTotal()-calculateDiscount();
    }

    public int getTotal() {
        calculateTotal();
        return total;
    }

    public int getDiscount() {
        calculateDiscount();
        return discount;
    }

    public int getTotalAfterDiscount() {
        calculateTotalAfterDiscount();
        return totalAfterDiscount;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setTotalAfterDiscount(int totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }
}
