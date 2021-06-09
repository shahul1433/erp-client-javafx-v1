package erp.client.javafx.stock.stockin;

import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.user.UserDTO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class StockIn {

	private final SimpleStringProperty name;
	private final SimpleStringProperty model;
	private final SimpleStringProperty category;
	private final SimpleStringProperty company;
	private final SimpleStringProperty warranty;
	private final SimpleStringProperty guarantee;
	private final SimpleDoubleProperty stockQuantity;
	private final SimpleDoubleProperty currentQuantity;
	private final SimpleDoubleProperty reorderLimit;
	private final SimpleDoubleProperty stockPrice;
	private final SimpleDoubleProperty customerPrice;
	private final SimpleDoubleProperty gst;
	private final SimpleDoubleProperty gstAmount;
	private final SimpleDoubleProperty netAmount;
	private final SimpleObjectProperty<ProductScale> scale;
	private final SimpleStringProperty specifications;
	private final SimpleObjectProperty<DealerDTO> dealer;
	private final SimpleObjectProperty<UserDTO> addedBy;
	private final SimpleObjectProperty<LocalDateTime> addedDate;
	
	private StockInDTO stockIn;
	
	private NumberFormat rupeesFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
	private NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("en", "IN"));
	private NumberFormat percentageFormat = NumberFormat.getPercentInstance(new Locale("en", "IN"));

	public StockIn(StockInDTO stockIn) {
		this.stockIn = stockIn;
		this.name = new SimpleStringProperty(stockIn.getName());
		this.model = new SimpleStringProperty(stockIn.getModel());
		this.category = new SimpleStringProperty(stockIn.getCategory());
		this.company = new SimpleStringProperty(stockIn.getCompany());
		this.warranty = new SimpleStringProperty(stockIn.getWarranty());
		this.guarantee = new SimpleStringProperty(stockIn.getGuarantee());
		this.stockQuantity = new SimpleDoubleProperty(stockIn.getStockQuantity());
		this.currentQuantity = new SimpleDoubleProperty(stockIn.getCurrentQuantity());
		this.reorderLimit = new SimpleDoubleProperty(stockIn.getReorderLimit());
		this.stockPrice = new SimpleDoubleProperty(stockIn.getStockPrice());
		this.customerPrice = new SimpleDoubleProperty(stockIn.getCustomerPrice());
		this.gst = new SimpleDoubleProperty(stockIn.getGst());
		this.gstAmount = new SimpleDoubleProperty(stockIn.getGstAmount());
		this.netAmount = new SimpleDoubleProperty(stockIn.getNetAmount());
		this.scale = new SimpleObjectProperty<ProductScale>(stockIn.getScale());
		this.specifications = new SimpleStringProperty(stockIn.getSpecifications());
		this.dealer = new SimpleObjectProperty<DealerDTO>(stockIn.getDealer());
		this.addedBy = new SimpleObjectProperty<UserDTO>(stockIn.getAddedBy());
		this.addedDate = new SimpleObjectProperty<LocalDateTime>(stockIn.getAddedDate());
	}

	public StockInDTO getStockIn() {
		return stockIn;
	}

	public void setStockIn(StockInDTO stockIn) {
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

	public double getStockQuantity() {
		return stockQuantity.get();
	}
	
	public void setStockQuantity(Double stockQuantity) {
		this.stockQuantity.set(stockQuantity);
	}

	public double getCurrentQuantity() {
		return currentQuantity.get();
	}
	
	public void setCurrentQuantity(Double currentQuantity) {
		this.currentQuantity.set(currentQuantity);
	}

	public double getReorderLimit() {
		return reorderLimit.get();
	}
	
	public void setReorderLimit(Double reorderLimit) {
		this.reorderLimit.set(reorderLimit);
	}

	public double getStockPrice() {
		return stockPrice.get();
	}
	
	public void setStockPrice(Double stockPrice) {
		this.stockPrice.set(stockPrice);
	}

	public double getCustomerPrice() {
		return customerPrice.get();
	}

	public void setCustomerPrice(Double customerPrice) {
		this.customerPrice.set(customerPrice);
	}
	
	public double getGst() {
		return gst.get();
	}
	
	public void setGst(Double gst) {
		this.gst.set(gst);
	}

	public double getGstAmount() {
		return gstAmount.get();
	}
	
	public void setGstAmount(Double gstAmount) {
		this.gstAmount.set(gstAmount);
	}

	public double getNetAmount() {
		return netAmount.get();
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount.set(netAmount);
	}
	
	public ProductScale getScale() {
		return scale.get();
	}
	
	public void setScale(ProductScale productScale) {
		this.scale.set(productScale);
	}

	public String getSpecifications() {
		return specifications.get();
	}

	public void setSpecifications(String specifications) {
		this.specifications.set(specifications);
	}
	
	public DealerDTO getDealer() {
		return dealer.get();
	}
	
	public void setDealer(DealerDTO dealer) {
		this.dealer.set(dealer);
	}

	public UserDTO getAddedBy() {
		return addedBy.get();
	}
	
	public void setAddedBy(UserDTO addedBy) {
		this.addedBy.set(addedBy);
	}

	public LocalDateTime getAddedDate() {
		return addedDate.get();
	}
	
	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate.set(addedDate);
	}
	
}
