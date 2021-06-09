package erp.client.javafx.dealer;

import erp.client.javafx.component.date.DateSearchable;
import erp.client.javafx.http.DatabaseTableFilter;
import erp.client.javafx.http.SortMap;

public class DealerFilter extends DatabaseTableFilter{

	private DealerDTO dealer;
	private DateSearchable addedDate, modifiedDate;
	
	public DealerFilter(DealerDTO dealer, DateSearchable addedDate, DateSearchable modifiedDate, int page, int size, SortMap sortMap) {
		super(page, size, sortMap);
		this.dealer = dealer;
		this.addedDate = addedDate;
		this.modifiedDate = modifiedDate;
	}

	public DealerDTO getDealer() {
		return dealer;
	}

	public void setDealer(DealerDTO dealer) {
		this.dealer = dealer;
	}

	public DateSearchable getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(DateSearchable addedDate) {
		this.addedDate = addedDate;
	}

	public DateSearchable getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(DateSearchable modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
