package erp.client.javafx.http;

public class SortMap{
	private String column;
	private String sort;
	
	public SortMap() {
		// TODO Auto-generated constructor stub
	}

	public SortMap(String column, String sort) {
		super();
		this.column = column;
		this.sort = sort;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "SortMap [column=" + column + ", sort=" + sort + "]";
	}
}