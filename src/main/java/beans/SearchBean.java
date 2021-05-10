package beans;

import DatabaseObjects.Tree;
import dao.TreeDAO;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SearchBean implements Serializable {

	@Inject
	TreeDAO treeDAO;

	private List<Tree> trees;

	private static final long serialVersionUID = 1L;
	private String searchAttribute;
	private String searchValue;
	
	private String searchAttribute2;
	private String searchValue2;

	public String getSearchAttribute() {
		return searchAttribute;
	}

	public void setSearchAttribute(String searchAttribute) {
		this.searchAttribute = searchAttribute;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public List<Tree> getTrees() {
		return trees;
	}

	public void setTrees(List<Tree> trees) {
		this.trees = trees;
	}

	public void search() {
		if((searchAttribute != null && searchValue != null) && (searchAttribute2 != null && searchValue2!=null)) {
			trees= treeDAO.searchTrees(searchAttribute, searchValue, searchAttribute2, searchValue2);
			System.out.println("Search 2 attributes");
			return;
		}
		if(searchAttribute!=null && searchValue!=null) {
			trees = treeDAO.searchTrees(searchAttribute, searchValue);
			return;
		}
		if(searchAttribute2!=null && searchValue2!=null) {
			trees = treeDAO.searchTrees(searchAttribute2, searchValue2);
			return;
		}
	}
	
	public String getSearchAttribute2() {
		return searchAttribute2;
	}
	
	public void setSearchAttribute2(String searchAttribute2) {
		this.searchAttribute2 = searchAttribute2;
	}
	
	public String getSearchValue2() {
		return searchValue2;
	}
	public void setSearchValue2(String searchValue2) {
		this.searchValue2 = searchValue2;
	}
}
