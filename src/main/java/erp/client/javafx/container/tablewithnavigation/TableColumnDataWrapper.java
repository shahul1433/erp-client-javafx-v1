package erp.client.javafx.container.tablewithnavigation;

import javax.swing.SortOrder;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableColumnDataWrapper<S, T> extends TableColumn<S, T>{

	private String dbAttributeName;
	private String displayName;
	private SortOrder sortOrder;
	
	public TableColumnDataWrapper(String displayName, String dbAttributeName) {
		super(displayName);
		this.dbAttributeName = dbAttributeName;
		this.displayName = displayName;
		this.setCellValueFactory(new PropertyValueFactory<>(dbAttributeName));
		this.sortOrder = SortOrder.ASCENDING;
	}

	public String getDbAttributeName() {
		return dbAttributeName;
	}

	public void setDbAttributeName(String dbAttributeName) {
		this.dbAttributeName = dbAttributeName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public void changeSortOrder() {
		if(this.sortOrder == SortOrder.ASCENDING)
			this.sortOrder = SortOrder.DESCENDING;
		else
			this.sortOrder = SortOrder.ASCENDING;
	}
	
	public String getSortOrderString() {
		return this.sortOrder == SortOrder.ASCENDING ? "asc" : "desc";
	}
}
