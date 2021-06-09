package erp.client.javafx.dealer;

import java.util.ArrayList;
import java.util.List;

public class DealerList {

	private List<DealerDTO> items;
	
	public DealerList() {
		items = new ArrayList<DealerDTO>();
	}

	public List<DealerDTO> getItems() {
		return items;
	}

	public void setItems(List<DealerDTO> items) {
		this.items = items;
	}
	
	public void addDealer(DealerDTO dealer) {
		items.add(dealer);
	}
}
