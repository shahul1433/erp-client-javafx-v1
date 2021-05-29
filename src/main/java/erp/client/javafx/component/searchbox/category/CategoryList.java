package erp.client.javafx.component.searchbox.category;

import java.util.HashSet;
import java.util.Set;

public class CategoryList {

	private Set<String> categoryList;
	
	public CategoryList() {
		categoryList = new HashSet<String>();
	}

	public Set<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<String> categoryList) {
		this.categoryList = categoryList;
	}
	
}
