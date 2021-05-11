package erp.client.javafx.dealer;

import java.util.ArrayList;
import java.util.List;

import erp.client.javafx.entity.TDealer;

public class DealerList {

	private List<TDealer> items;
	
	public DealerList() {
		items = new ArrayList<TDealer>();
	}

	public List<TDealer> getItems() {
		return items;
	}

	public void setItems(List<TDealer> items) {
		this.items = items;
	}
	
	public void addDealer(TDealer dealer) {
		items.add(dealer);
	}
}
