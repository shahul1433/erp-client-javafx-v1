package erp.client.javafx.dealer;

import erp.client.javafx.gst.GstStateCodeDTO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Dealer {

	private final SimpleStringProperty name;
	private final SimpleStringProperty shop;
	private final SimpleStringProperty email;
	private final SimpleStringProperty phone;
	private final SimpleStringProperty gstin;
	private final ObjectProperty<GstStateCodeDTO> gstStateCode;
	private final SimpleDoubleProperty balance;
	private final ObjectProperty<LocalDateTime> addedDate;
	private final ObjectProperty<LocalDateTime> modifiedDate;
	
	private DealerDTO dealer;
	
	public Dealer(DealerDTO dealer) {
		this.dealer = dealer;
		this.name = new SimpleStringProperty(dealer.getName());
		this.shop = new SimpleStringProperty(dealer.getShop());
		this.email = new SimpleStringProperty(dealer.getEmail());
		this.phone = new SimpleStringProperty(dealer.getPhone());
		this.gstin = new SimpleStringProperty(dealer.getGstin());
		this.gstStateCode = new SimpleObjectProperty<>(dealer.getGstStateCode());
		this.balance = new SimpleDoubleProperty(dealer.getBalance());
		this.addedDate = new SimpleObjectProperty<>(dealer.getAddedDate());
		this.modifiedDate = new SimpleObjectProperty<>(dealer.getModifiedDate());
	}

	public DealerDTO getDealer() {
		return dealer;
	}

	public void setDealer(DealerDTO dealer) {
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

	public GstStateCodeDTO getGstStateCode() {
		return gstStateCode.get();
	}
	
	public void setGstStateCode(GstStateCodeDTO gstStateCode) {
		this.gstStateCode.set(gstStateCode);
	}

	public double getBalance() {
		return balance.get();
	}
	
	public void setBalance(Double balance) {
		this.balance.set(balance);
	}

	public LocalDateTime getAddedDate() {
		return addedDate.get();
	}
	
	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate.set(addedDate);
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate.get();
	}
	
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate.set(modifiedDate);
	}

	@Override
	public String toString() {
		return "Dealer [name=" + name + "]";
	}

}
