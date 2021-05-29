package erp.client.javafx.stock.stockin;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.entity.TDealer;
import erp.client.javafx.entity.TStockIn;
import erp.client.javafx.entity.TUser;
import javafx.beans.property.SimpleStringProperty;

public class StockIn {

	private final SimpleStringProperty name;
	private final SimpleStringProperty model;
	private final SimpleStringProperty category;
	private final SimpleStringProperty company;
	private final SimpleStringProperty warranty;
	private final SimpleStringProperty guarantee;
	private final SimpleStringProperty stockQuantity;
	private final SimpleStringProperty currentQuantity;
	private final SimpleStringProperty reorderLimit;
	private final SimpleStringProperty stockPrice;
	private final SimpleStringProperty customerPrice;
	private final SimpleStringProperty gst;
	private final SimpleStringProperty gstAmount;
	private final SimpleStringProperty netAmount;
	private final SimpleStringProperty scale;
	private final SimpleStringProperty specifications;
	private final SimpleStringProperty dealer;
	private final SimpleStringProperty addedBy;
	private final SimpleStringProperty addedDate;
	
	private TStockIn stockIn;
	
	private NumberFormat rupeesFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
	private NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("en", "IN"));
	private NumberFormat percentageFormat = NumberFormat.getPercentInstance(new Locale("en", "IN"));

	public StockIn(TStockIn stockIn) {
		this.stockIn = stockIn;
		this.name = new SimpleStringProperty(stockIn.getName());
		this.model = new SimpleStringProperty(stockIn.getModel());
		this.category = new SimpleStringProperty(stockIn.getCategory());
		this.company = new SimpleStringProperty(stockIn.getCompany());
		this.warranty = new SimpleStringProperty(stockIn.getWarranty());
		this.guarantee = new SimpleStringProperty(stockIn.getGuarantee());
		this.stockQuantity = new SimpleStringProperty(stockIn.getStockQuantity() != null ? numberFormat.format(stockIn.getStockQuantity()) : "");
		this.currentQuantity = new SimpleStringProperty(stockIn.getCurrentQuantity() != null ? numberFormat.format(stockIn.getCurrentQuantity()) : "");
		this.reorderLimit = new SimpleStringProperty(stockIn.getReorderLimit() != null ? numberFormat.format(stockIn.getReorderLimit()) : "");
		this.stockPrice = new SimpleStringProperty(stockIn.getStockPrice() != null ? rupeesFormat.format(stockIn.getStockPrice()) : "");
		this.customerPrice = new SimpleStringProperty(stockIn.getCustomerPrice() != null ? rupeesFormat.format(stockIn.getCustomerPrice()) : "");
		this.gst = new SimpleStringProperty(stockIn.getGst() != null ? percentageFormat.format(stockIn.getGst()) : "");
		this.gstAmount = new SimpleStringProperty(stockIn.getGstAmount() != null ? rupeesFormat.format(stockIn.getGstAmount()) : "");
		this.netAmount = new SimpleStringProperty(stockIn.getNetAmount() != null ? rupeesFormat.format(stockIn.getNetAmount()) : "");
		this.scale = new SimpleStringProperty(stockIn.getScale().getName());
		this.specifications = new SimpleStringProperty(stockIn.getSpecifications());
		this.dealer = new SimpleStringProperty(stockIn.getDealer().getName());
		this.addedBy = new SimpleStringProperty(stockIn.getAddedBy().getName());
		this.addedDate = new SimpleStringProperty(stockIn.getAddedDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
	}

	public TStockIn getStockIn() {
		return stockIn;
	}

	public void setStockIn(TStockIn stockIn) {
		this.stockIn = stockIn;
	}

	public String getName() {
		return name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);
	}

	public String getModel() {
		return model.get();
	}
	
	public void setModel(String model) {
		this.model.set(model);
	}

	public String getCategory() {
		return category.get();
	}

	public void setCategory(String category) {
		this.category.set(category);
	}
	
	public String getCompany() {
		return company.get();
	}
	
	public void setCompany(String company) {
		this.company.set(company);
	}

	public String getWarranty() {
		return warranty.get();
	}
	
	public void setWarranty(String warranty) {
		this.warranty.set(warranty);
	}

	public String getGuarantee() {
		return guarantee.get();
	}
	
	public void setGuarantee(String guarantee) {
		this.guarantee.set(guarantee);
	}

	public String getStockQuantity() {
		return stockQuantity.get();
	}
	
	public void setStockQuantity(Double stockQuantity) {
		this.stockQuantity.set(stockQuantity == null ? "" : numberFormat.format(stockQuantity));
	}

	public String getCurrentQuantity() {
		return currentQuantity.get();
	}
	
	public void setCurrentQuantity(Double currentQuantity) {
		this.currentQuantity.set(currentQuantity == null ? "" : numberFormat.format(currentQuantity));
	}

	public String getReorderLimit() {
		return reorderLimit.get();
	}
	
	public void setReorderLimit(Double reorderLimit) {
		this.reorderLimit.set(reorderLimit == null? "" : numberFormat.format(reorderLimit));
	}

	public String getStockPrice() {
		return stockPrice.get();
	}
	
	public void setStockPrice(Double stockPrice) {
		this.stockPrice.set(stockPrice == null ? "" : rupeesFormat.format(stockPrice));
	}

	public String getCustomerPrice() {
		return customerPrice.get();
	}

	public void setCustomerPrice(Double customerPrice) {
		this.customerPrice.set(customerPrice == null ? "" : rupeesFormat.format(customerPrice));
	}
	
	public String getGst() {
		return gst.get();
	}
	
	public void setGst(Double gst) {
		this.gst.set(gst == null ? "" : numberFormat.format(gst));
	}

	public String getGstAmount() {
		return gstAmount.get();
	}
	
	public void setGstAmount(Double gstAmount) {
		this.gstAmount.set(gstAmount == null? "" : rupeesFormat.format(gstAmount));
	}

	public String getNetAmount() {
		return netAmount.get();
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount.set(netAmount == null ? "" : rupeesFormat.format(netAmount));
	}
	
	public String getScale() {
		return scale.get();
	}
	
	public void setScale(ProductScale productScale) {
		this.scale.set(productScale.getName());
	}

	public String getSpecifications() {
		return specifications.get();
	}

	public void setSpecifications(String specifications) {
		this.specifications.set(specifications);
	}
	
	public String getDealer() {
		return dealer.get();
	}
	
	public void setDealer(TDealer dealer) {
		this.dealer.set(dealer == null ? "" : dealer.getName());
	}

	public String getAddedBy() {
		return addedBy.get();
	}
	
	public void setAddedBy(TUser addedBy) {
		this.addedBy.set(addedBy == null ? "" : addedBy.getName());
	}

	public String getAddedDate() {
		return addedDate.get();
	}
	
	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate.set(addedDate == null ? "" : addedDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
	}
	
}
