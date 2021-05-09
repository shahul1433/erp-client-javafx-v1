package erp.client.javafx.component.textfield.phone;

public enum Country {

	AUSTRALIA ("Australia","+61","/country/australia.jpg", 9),
	ISRAEL ("Israel","+972","/country/israel.png", 9),
	SAUDI_ARABIA ("Saudi Arabia","+966","/country/ksa.png", 9),
	UNITED_ARAB_EMIRATES ("United Arab Emirates","+971","/country/uae.png", 9),
	UNITED_STATES_OF_AMERICA ("United States Of America","+1","/country/usa.png", 10),
	INDIA ("India","+91","/country/india.png", 10);
	
	private String countryName;
	private String extension;
	private String icon;
	private int length;
	
	private Country(String countryName, String extension, String icon, int length) {
		this.countryName = countryName;
		this.extension = extension;
		this.icon = icon;
		this.length = length;
	}

	public String getExtension() {
		return extension;
	}

	public String getIcon() {
		return icon;
	}

	public int getLength() {
		return length;
	}

	public String getCountryName() {
		return countryName;
	}

}
