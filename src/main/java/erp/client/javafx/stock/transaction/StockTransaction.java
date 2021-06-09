/*
package erp.client.javafx.stock.transaction;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import erp.client.javafx.component.enums.StockTransactionType;
import javafx.beans.property.SimpleStringProperty;

public class StockTransaction {

	private final SimpleStringProperty stockTransactionType;
	private final SimpleStringProperty product;
	private final SimpleStringProperty netAmount;
	private final SimpleStringProperty addedDate;
	
	private TStockTransaction stockTransaction;
	
	private NumberFormat rupeesFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
	
	public StockTransaction(TStockTransaction transaction) {
		this.stockTransaction = transaction;
		this.stockTransactionType = new SimpleStringProperty(transaction.getStockTransactionType() != null ? transaction.getStockTransactionType().getType() : "");
		String product = "";
		String netAmountStr = "";
		if(transaction.getStockTransactionType() == StockTransactionType.STOCK_IN && transaction.getStockIn() != null) {
			product = transaction.getStockIn().getFullName();
			netAmountStr = rupeesFormat.format(transaction.getStockIn().getNetAmount());
		}else {
			if(transaction.getStockReturn() != null && transaction.getStockReturn().getStockIn() != null) {
				TStockIn stockIn = transaction.getStockReturn().getStockIn();
				product = stockIn.getFullName();
				netAmountStr = rupeesFormat.format(transaction.getStockReturn().getRefundAmount());
			}
		}
		this.product = new SimpleStringProperty(product);
		this.netAmount = new SimpleStringProperty(netAmountStr);
		this.addedDate = new SimpleStringProperty(transaction.getAddedDate() != null ? transaction.getAddedDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) : "");
	}

	public TStockTransaction getStockTransaction() {
		return stockTransaction;
	}

	public void setStockTransaction(TStockTransaction stockTransaction) {
		this.stockTransaction = stockTransaction;
	}

	public String getStockTransactionType() {
		return stockTransactionType.get();
	}
	
	public void setStockTransactionType(StockTransactionType stockTransactionType) {
		this.stockTransactionType.set(stockTransactionType != null ? stockTransactionType.getType() : "");
	}

	public String getProduct() {
		return product.get();
	}
	
	public void setProduct(TStockIn product) { 
		this.product.set(product != null ? product.getFullName() : "");
	}

	public String getNetAmount() {
		return netAmount.get();
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount.set(rupeesFormat.format(netAmount != null ? netAmount : 0));
	}
	
	public String getAddedDate() {
		return addedDate.get();
	}
	
	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate.set(addedDate != null ? addedDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) : "");
	}
	
}
*/
