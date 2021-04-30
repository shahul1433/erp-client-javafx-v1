package erp.client.javafx.entity;

import java.util.HashSet;
import java.util.Set;

public class EntityIDList {

	private Set<Long> idList;
	
	public EntityIDList() {
		idList = new HashSet<Long>();
	}

	public Set<Long> getIdList() {
		return idList;
	}

	public void setIdList(Set<Long> idList) {
		this.idList = idList;
	}
	
	public void addId(Long id) {
		idList.add(id);
	}
}
