package erp.client.javafx.dealer;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import erp.client.javafx.entity.TDealer;
import erp.client.javafx.entity.TGstStateCode;
import javafx.beans.property.SimpleStringProperty;

public class Dealer {

	private final SimpleStringProperty name;
	private final SimpleStringProperty shop;
	private final SimpleStringProperty email;
	private final SimpleStringProperty phone;
	private final SimpleStringProperty gstin;
	private final SimpleStringProperty gstStateCode;
	private final SimpleStringProperty balance;
	private final SimpleStringProperty addedDate;
	private final SimpleStringProperty modifiedDate;
	
	private TDealer dealer;
	
	private NumberFormat rupeesFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
	
	public Dealer(TDealer dealer) {
		this.dealer = dealer;
		this.name = new SimpleStringProperty(dealer.getName());
		this.shop = new SimpleStringProperty(dealer.getShop());
		this.email = new SimpleStringProperty(dealer.getEmail());
		this.phone = new SimpleStringProperty(dealer.getPhone());
		this.gstin = new SimpleStringProperty(dealer.getGstin());
		this.gstStateCode = new SimpleStringProperty(dealer.getGstStateCode() != null ? dealer.getGstStateCode().getCode() + " - " + dealer.getGstStateCode().getState() : "");
		this.balance = new SimpleStringProperty(rupeesFormat.format(dealer.getBalance()));
		this.addedDate = new SimpleStringProperty(dealer.getAddedDate() != null ? dealer.getAddedDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) : "");
		this.modifiedDate = new SimpleStringProperty(dealer.getModifiedDate() != null ? dealer.getModifiedDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) : "");
	}

	public TDealer getDealer() {
		return dealer;
	}

	public void setDealer(TDealer dealer) {
		this.dealer = dealer;
	}

	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}

	public String getShop() {
		return shop.get();
	}
	
	public void setShop(String shop) {
		this.shop.set(shop);
	}

	public String getEmail() {
		return email.get();
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}

	public String getPhone() {
		return phone.get();
	}
	
	public void setPhone(String phone) {
		this.phone.set(phone);
	}

	public String getGstin() {
		return gstin.get();
	}
	
	public void setGstin(String gstin) {
		this.gstin.set(gstin);
	}

	public String getGstStateCode() {
		return gstStateCode.get();
	}
	
	public void setGstStateCode(TGstStateCode gstStateCode) {
		this.gstStateCode.set(gstStateCode != null ? gstStateCode.getCode() + " - " + gstStateCode.getState() : "");
	}

	public String getBalance() {
		return balance.get();
	}
	
	public void setBalance(Double balance) {
		this.balance.set(rupeesFormat.format(balance));
	}

	public String getAddedDate() {
		return addedDate.get();
	}
	
	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate.set(addedDate != null ? addedDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) : "");
	}

	public String getModifiedDate() {
		return modifiedDate.get();
	}
	
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate.set(modifiedDate != null ? modifiedDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) : "");
	}

	@Override
	public String toString() {
		return "Dealer [name=" + name + "]";
	}
	
}
