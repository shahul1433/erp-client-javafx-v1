package erp.client.javafx.http;

public class DatabaseTableFilter {

	private int page;
	private int size;
	private SortMap sortMap;
	
	public DatabaseTableFilter(int page, int size, SortMap sortMap) {
		super();
		this.page = page;
		this.size = size;
		this.sortMap = sortMap;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public SortMap getSortMap() {
		return sortMap;
	}

	public void setSortMap(SortMap sortMap) {
		this.sortMap = sortMap;
	}

	@Override
	public String toString() {
		return "DatabaseTableFilter [page=" + page + ", size=" + size + ", sortMap=" + sortMap + "]";
	}
	
}