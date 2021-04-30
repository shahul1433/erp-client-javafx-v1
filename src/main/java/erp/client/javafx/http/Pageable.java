package erp.client.javafx.http;
public class Pageable{
	
	private Sort sort;
	private long offset;
	private long pageNumber;
	private long pageSize;
	private boolean unpaged;
	private boolean paged;
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	public long getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(long pageNumber) {
		this.pageNumber = pageNumber;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public boolean isUnpaged() {
		return unpaged;
	}
	public void setUnpaged(boolean unpaged) {
		this.unpaged = unpaged;
	}
	public boolean isPaged() {
		return paged;
	}
	public void setPaged(boolean paged) {
		this.paged = paged;
	}
	@Override
	public String toString() {
		return "Pageable [sort=" + sort + ", offset=" + offset + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", unpaged=" + unpaged + ", paged=" + paged + "]";
	}
	
}
