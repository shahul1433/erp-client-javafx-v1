package erp.client.javafx.gst;

import erp.client.javafx.gst.GstStateCodeDTO;

import java.util.HashSet;
import java.util.Set;

public class GstStateCodeList {

	private Set<GstStateCodeDTO> gstStateList;
	
	public GstStateCodeList() {
		gstStateList = new HashSet<GstStateCodeDTO>();
	}

	public Set<GstStateCodeDTO> getGstStateList() {
		return gstStateList;
	}

	public void setGstStateList(Set<GstStateCodeDTO> gstStateList) {
		this.gstStateList = gstStateList;
	}
	
	public void addGstStateCode(GstStateCodeDTO code) {
		this.gstStateList.add(code);
	}
}
