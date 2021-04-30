package erp.client.javafx.component.enums;

public enum UserRole {

	USER_MANAGEMENT	("USER MANAGEMENT"),
	STOCK_IN ("STOCK IN"),
	STOCK_RETURN ("STOCK RETURN"),
	STOCK_TRANSACTION ("STOCK TRANSACTION"),
	DEALER	("DEALER");
	
	private String name;
	
	private UserRole(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
