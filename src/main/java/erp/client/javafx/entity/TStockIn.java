package erp.client.javafx.entity;

import erp.client.javafx.component.enums.ProductScale;

import java.time.LocalDateTime;


public class TStockIn {
	
	private Long id;
	private String barcode;
	private String name;
	private String model;
	private String category;
	private String company;
	private String warranty;
	private String guarantee;
	private Double stockQuantity;
	private Double currentQuantity;
	private Double reorderLimit;
	private Double stockPrice;
	private Double customerPrice;
	private Double gst;
	private Double gstAmount;
	private Double netAmount;
	private ProductScale scale;
	private String specifications;
	private TDealer dealer;
	private TUser addedBy;
	private LocalDateTime addedDate;
	private Boolean archive;

	public TStockIn() {
		// TODO Auto-generated constructor stub
	}

	public TStockIn(String barcode, String name, String model, String category, String company, String warranty,
			String guarantee, Double stockQuantity, Double currentQuantity, Double reorderLimit, Double stockPrice,
			Double customerPrice, Double gst, Double gstAmount, Double netAmount, ProductScale scale,
			String specifications, TDealer dealer, TUser addedBy, LocalDateTime addedDate, Boolean archive) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.model = model;
		this.category = category;
		this.company = company;
		this.warranty = warranty;
		this.guarantee = guarantee;
		this.stockQuantity = stockQuantity;
		this.currentQuantity = currentQuantity;
		this.reorderLimit = reorderLimit;
		this.stockPrice = stockPrice;
		this.customerPrice = customerPrice;
		this.gst = gst;
		this.gstAmount = gstAmount;
		this.netAmount = netAmount;
		this.scale = scale;
		this.specifications = specifications;
		this.dealer = dealer;
		this.addedBy = addedBy;
		this.addedDate = addedDate;
		this.archive = archive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public Double getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Double stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Double getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(Double currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public Double getReorderLimit() {
		return reorderLimit;
	}

	public void setReorderLimit(Double reorderLimit) {
		this.reorderLimit = reorderLimit;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Double getCustomerPrice() {
		return customerPrice;
	}

	public void setCustomerPrice(Double customerPrice) {
		this.customerPrice = customerPrice;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public Double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public ProductScale getScale() {
		return scale;
	}

	public void setScale(ProductScale scale) {
		this.scale = scale;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public TDealer getDealer() {
		return dealer;
	}

	public void setDealer(TDealer dealer) {
		this.dealer = dealer;
	}

	public TUser getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(TUser addedBy) {
		this.addedBy = addedBy;
	}

	public LocalDateTime getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate = addedDate;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "TStockIn [id=" + id + ", barcode=" + barcode + ", name=" + name + ", model=" + model + ", category="
				+ category + ", company=" + company + ", warranty=" + warranty + ", guarantee=" + guarantee
				+ ", stockQuantity=" + stockQuantity + ", currentQuantity=" + currentQuantity + ", reorderLimit="
				+ reorderLimit + ", stockPrice=" + stockPrice + ", customerPrice=" + customerPrice + ", gst=" + gst
				+ ", gstAmount=" + gstAmount + ", netAmount=" + netAmount + ", scale=" + scale + ", specifications="
				+ specifications + ", dealer=" + dealer + ", addedBy=" + addedBy + ", addedDate=" + addedDate
				+ ", archive=" + archive + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addedBy == null) ? 0 : addedBy.hashCode());
		result = prime * result + ((archive == null) ? 0 : archive.hashCode());
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((currentQuantity == null) ? 0 : currentQuantity.hashCode());
		result = prime * result + ((customerPrice == null) ? 0 : customerPrice.hashCode());
		result = prime * result + ((dealer == null) ? 0 : dealer.hashCode());
		result = prime * result + ((gst == null) ? 0 : gst.hashCode());
		result = prime * result + ((gstAmount == null) ? 0 : gstAmount.hashCode());
		result = prime * result + ((guarantee == null) ? 0 : guarantee.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((netAmount == null) ? 0 : netAmount.hashCode());
		result = prime * result + ((reorderLimit == null) ? 0 : reorderLimit.hashCode());
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
		result = prime * result + ((specifications == null) ? 0 : specifications.hashCode());
		result = prime * result + ((stockPrice == null) ? 0 : stockPrice.hashCode());
		result = prime * result + ((stockQuantity == null) ? 0 : stockQuantity.hashCode());
		result = prime * result + ((warranty == null) ? 0 : warranty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TStockIn other = (TStockIn) obj;
		if (addedBy == null) {
			if (other.addedBy != null)
				return false;
		} else if (!addedBy.equals(other.addedBy))
			return false;
		if (archive == null) {
			if (other.archive != null)
				return false;
		} else if (!archive.equals(other.archive))
			return false;
		if (barcode == null) {
			if (other.barcode != null)
				return false;
		} else if (!barcode.equals(other.barcode))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (currentQuantity == null) {
			if (other.currentQuantity != null)
				return false;
		} else if (!currentQuantity.equals(other.currentQuantity))
			return false;
		if (customerPrice == null) {
			if (other.customerPrice != null)
				return false;
		} else if (!customerPrice.equals(other.customerPrice))
			return false;
		if (dealer == null) {
			if (other.dealer != null)
				return false;
		} else if (!dealer.equals(other.dealer))
			return false;
		if (gst == null) {
			if (other.gst != null)
				return false;
		} else if (!gst.equals(other.gst))
			return false;
		if (gstAmount == null) {
			if (other.gstAmount != null)
				return false;
		} else if (!gstAmount.equals(other.gstAmount))
			return false;
		if (guarantee == null) {
			if (other.guarantee != null)
				return false;
		} else if (!guarantee.equals(other.guarantee))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (netAmount == null) {
			if (other.netAmount != null)
				return false;
		} else if (!netAmount.equals(other.netAmount))
			return false;
		if (reorderLimit == null) {
			if (other.reorderLimit != null)
				return false;
		} else if (!reorderLimit.equals(other.reorderLimit))
			return false;
		if (scale != other.scale)
			return false;
		if (specifications == null) {
			if (other.specifications != null)
				return false;
		} else if (!specifications.equals(other.specifications))
			return false;
		if (stockPrice == null) {
			if (other.stockPrice != null)
				return false;
		} else if (!stockPrice.equals(other.stockPrice))
			return false;
		if (stockQuantity == null) {
			if (other.stockQuantity != null)
				return false;
		} else if (!stockQuantity.equals(other.stockQuantity))
			return false;
		if (warranty == null) {
			if (other.warranty != null)
				return false;
		} else if (!warranty.equals(other.warranty))
			return false;
		return true;
	}
	
	public String getFullName() {
		return name + " - " + model + " - " + category + " - " + company;
	}
	
}
