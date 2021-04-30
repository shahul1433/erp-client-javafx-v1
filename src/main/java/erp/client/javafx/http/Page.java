package erp.client.javafx.http;

import java.util.List;

public class Page<T> {

	private List<T> content;
	private Pageable pageable;
	private boolean last;
	private long totalElements;
	private long totalPages;
	private long size;
	private long number;
	private Sort sort;
	private long numberOfElements;
	private boolean first;
	private boolean empty;
	
	public Page() {
		// TODO Auto-generated constructor stub
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public long getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(long numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return "Page [content=" + content + ", pageable=" + pageable + ", last=" + last + ", totalElements="
				+ totalElements + ", totalPages=" + totalPages + ", size=" + size + ", number=" + number + ", sort="
				+ sort + ", numberOfElements=" + numberOfElements + ", first=" + first + ", empty=" + empty + "]";
	}
	
}
