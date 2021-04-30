package erp.client.javafx.component.enums;

public enum StockTransactionType {
	ALL ("ALL"),
	STOCK_IN ("STOCK IN"),
	STOCK_RETURN ("STOCK RETURN");
	
	private String type;
	
	private StockTransactionType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
