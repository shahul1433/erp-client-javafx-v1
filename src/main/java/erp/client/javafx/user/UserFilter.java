package erp.client.javafx.user;

import erp.client.javafx.component.date.DateSearchable;
import erp.client.javafx.entity.TUser;
import erp.client.javafx.http.DatabaseTableFilter;
import erp.client.javafx.http.SortMap;

public class UserFilter extends DatabaseTableFilter{

	private TUser user;
	
	private DateSearchable addedDate, modifiedDate;
	
	public UserFilter(TUser user, DateSearchable addedDate, DateSearchable modifiedDate, int page, int size, SortMap sortMap) {
		super(page, size, sortMap);
		this.user = user;
		this.addedDate = addedDate;
		this.modifiedDate = modifiedDate;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
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
