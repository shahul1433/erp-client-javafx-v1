package erp.client.javafx.entity;

import java.util.HashSet;
import java.util.Set;

public class GstStateCodeList {

	private Set<TGstStateCode> gstStateList;
	
	public GstStateCodeList() {
		gstStateList = new HashSet<TGstStateCode>();
	}

	public Set<TGstStateCode> getGstStateList() {
		return gstStateList;
	}

	public void setGstStateList(Set<TGstStateCode> gstStateList) {
		this.gstStateList = gstStateList;
	}
	
	public void addGstStateCode(TGstStateCode code) {
		this.gstStateList.add(code);
	}
}
