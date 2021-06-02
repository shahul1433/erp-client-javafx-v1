package erp.client.javafx.dealer;

import erp.client.javafx.entity.TDealer;
import erp.client.javafx.entity.TGstStateCode;
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
	private final ObjectProperty<TGstStateCode> gstStateCode;
	private final SimpleDoubleProperty balance;
	private final ObjectProperty<LocalDateTime> addedDate;
	private final ObjectProperty<LocalDateTime> modifiedDate;
	
	private TDealer dealer;
	
	private static NumberFormat rupeesFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
	
	public Dealer(TDealer dealer) {
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

	public TGstStateCode getGstStateCode() {
		return gstStateCode.get();
	}
	
	public void setGstStateCode(TGstStateCode gstStateCode) {
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

	static class DateCellFactory implements  Callback<TableColumn<Dealer, LocalDateTime>, TableCell<Dealer, LocalDateTime>> {

		@Override
		public TableCell<Dealer, LocalDateTime> call(TableColumn<Dealer, LocalDateTime> dealerLocalDateTimeTableColumn) {
			return new TableCell<>(){
				@Override
				protected void updateItem(LocalDateTime localDateTime, boolean b) {
					super.updateItem(localDateTime, b);
					setStyle("-fx-alignment: center");
					if(localDateTime != null) {
						setText(localDateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
					} else
						setText(null);
				}
			};
		}
	}

	static class BalanceCellFactory implements  Callback<TableColumn<Dealer, Double>, TableCell<Dealer, Double>> {

		@Override
		public TableCell<Dealer, Double> call(TableColumn<Dealer, Double> dealerDoubleTableColumn) {
			return new TableCell<>() {

				@Override
				protected void updateItem(Double value, boolean b) {
					getStylesheets().add(Dealer.class.getResource("style.css").toExternalForm());
					super.updateItem(value, b);
					setStyle("-fx-alignment: center-right");
					if(value != null) {
						setText(rupeesFormat.format(value));
						if(value < 0) {
							getStyleClass().add("debit-balance");
							getStylesheets().remove("credit-balance");
							getStylesheets().remove("zero-balance");
						}else if(value > 0) {
							getStyleClass().add("credit-balance");
							getStylesheets().remove("debit-balance");
							getStylesheets().remove("zero-balance");
						}else {
							getStyleClass().add("zero-balance");
							getStylesheets().remove("debit-balance");
							getStylesheets().remove("credit-balance");
						}
					} else
						setText(null);
				}
			};
		}
	}

	static class GstStateCodeCellFactory implements Callback<TableColumn<Dealer, TGstStateCode>, TableCell<Dealer, TGstStateCode>> {

		@Override
		public TableCell<Dealer, TGstStateCode> call(TableColumn<Dealer, TGstStateCode> dealerTGstStateCodeTableColumn) {
			return new TableCell<>() {
				@Override
				protected void updateItem(TGstStateCode tGstStateCode, boolean b) {
					super.updateItem(tGstStateCode, b);
					if(tGstStateCode != null)
						setText(tGstStateCode.getCode() + " - " + tGstStateCode.getState());
					else
						setText(null);
				}
			};
		}
	}
}
