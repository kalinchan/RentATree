package beans;

import DatabaseObjects.Tree;
import dao.TreeDAO;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.control.RequestContextController;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Named
@SessionScoped
public class CartBean implements Serializable {

    private List<Tree> contents = new ArrayList<>();
    private double total = 0;
    private double discount = 0;
    private double totalAfterDiscount = 0;

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
        if(!contents.stream().anyMatch(tree -> tree.getTreeID()==selectedTree.getTreeID())){
            contents.add(selectedTree);
        }
        return "Trees.jsf";
    }

    public void removeTreeFromCart(Tree selectedTree){
        Logger log = Logger.getLogger(String.valueOf(this.getClass()));
        contents.removeIf(tree -> tree.getTreeID() == selectedTree.getTreeID());
    }

    public double calculateTotal(){
        total = 0;
        for (Tree tree : contents){
            total = total + tree.getTotalPrice(tree);
        }
        return total;
    }

    private double calculateDiscount() {
        for(Tree tree : contents){

        }
        return discount;
    }

    private double calculateTotalAfterDiscount(){
        return calculateTotal()-calculateDiscount();
    }

    public double getTotal() {
        calculateTotal();
        return total;
    }

    public double getDiscount() {
        calculateDiscount();
        return discount;
    }

    public double getTotalAfterDiscount() {
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
