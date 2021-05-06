package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import DatabaseObjects.Tree;
import dao.TreeDAO;

@Named
@ViewScoped
public class SearchBean implements Serializable {

	@Inject
	TreeDAO treeDAO;

	private List<Tree> trees;

	private static final long serialVersionUID = 1L;
	private String searchAttribute;
	private String searchValue;

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
		trees = treeDAO.searchTrees(searchAttribute, searchValue);
	}
}
