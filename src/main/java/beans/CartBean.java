package beans;

import DatabaseObjects.Tree;
import dao.TreeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@SessionScoped
public class CartBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Tree> contents = new ArrayList<>();
	private double total = 0;
	private double discount = 0;
	private double totalAfterDiscount = 0;
	private int quantityOrdered;

	@Inject
	TreeDAO treeDAO;

	@PostConstruct
	public void init() {
		new CartBean();
	}

	public CartBean() {
	}

	public CartBean(List<Tree> contents, int total) {
		this.contents = contents;
	}

	public List<Tree> getContents() {
		System.out.println(contents);
		return contents;
	}

	public void setContents(List<Tree> contents) {
		this.contents = contents;
	}

	public int getCartSize() {
		return contents.size();
	}

	public String addTreeToCart(Tree selectedTree) {
		if (!contents.stream().anyMatch(tree -> tree.getTreeID() == selectedTree.getTreeID())) {
			contents.add(selectedTree);
		}
		return "Trees.jsf";
	}

	public void removeTreeFromCart(Tree selectedTree) {
		Logger log = Logger.getLogger(String.valueOf(this.getClass()));
		contents.removeIf(tree -> tree.getTreeID() == selectedTree.getTreeID());
	}

	public double getTotal() {
		total = 0;
		for (Tree tree : contents) {
			total = total + (tree.getTotalPrice(tree));
		}
		return total;
	}

	public double getDiscount() {
		discount = 0;
		int counter = 1;
		for (Tree tree : contents) {
			for (int i = 1; i <= tree.getQuantityOrdered(); i++) {
				if (counter % 2 == 0) {
					discount = discount + ((tree.getTotalDailyPrice(tree) + tree.getDeposit()) / 2);
				}
				counter++;
			}
		}
		return discount;
	}

	public double getTotalAfterDiscount() {
		return total - discount;
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

	public int getQuantityOrdered() {
		return quantityOrdered;
	}
}
