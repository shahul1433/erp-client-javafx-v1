package erp.client.javafx.component.enums;

public enum ProductScale {

	ALL ("All", "ALL"),
	KG	("Kilogram", "KG"),
	LITTER	("Litter", "LTR"),
	COUNT	("Count", "U"),
	METER	("Meter", "M"),
	CENTI_METER	("Centi Meter", "CM");
	
	private String name;
	private String representation;
	
	private ProductScale(String name, String representation) {
		this.name = name;
		this.representation = representation;
	}

	public String getName() {
		return name;
	}

	public String getRepresentation() {
		return representation;
	}
	
}
