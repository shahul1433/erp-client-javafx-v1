package erp.client.javafx.entity;

import java.time.LocalDateTime;

public class TStockReturn {

	private Long id;
	private TStockIn stockIn;
	private String reason;
	private Double returnQuantity;
	private Double refundAmount;
	private LocalDateTime addedDate;
	private TUser addedBy;
	private Boolean archive;
	
	public TStockReturn() {
		// TODO Auto-generated constructor stub
	}

	public TStockReturn(TStockIn stockIn, String reason, Double returnQuantity, Double refundAmount,
			LocalDateTime addedDate, TUser addedBy, Boolean archive) {
		super();
		this.stockIn = stockIn;
		this.reason = reason;
		this.returnQuantity = returnQuantity;
		this.refundAmount = refundAmount;
		this.addedDate = addedDate;
		this.addedBy = addedBy;
		this.archive = archive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TStockIn getStockIn() {
		return stockIn;
	}

	public void setStockIn(TStockIn stockIn) {
		this.stockIn = stockIn;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Double getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(Double returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
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

	public TUser getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(TUser addedBy) {
		this.addedBy = addedBy;
	}

	@Override
	public String toString() {
		return "TStockReturn [id=" + id + ", stockIn=" + stockIn + ", reason=" + reason + ", returnQuantity="
				+ returnQuantity + ", refundAmount=" + refundAmount + ", addedDate=" + addedDate + ", addedBy="
				+ addedBy + ", archive=" + archive + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addedBy == null) ? 0 : addedBy.hashCode());
		result = prime * result + ((addedDate == null) ? 0 : addedDate.hashCode());
		result = prime * result + ((archive == null) ? 0 : archive.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((refundAmount == null) ? 0 : refundAmount.hashCode());
		result = prime * result + ((returnQuantity == null) ? 0 : returnQuantity.hashCode());
		result = prime * result + ((stockIn == null) ? 0 : stockIn.hashCode());
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
		TStockReturn other = (TStockReturn) obj;
		if (addedBy == null) {
			if (other.addedBy != null)
				return false;
		} else if (!addedBy.equals(other.addedBy))
			return false;
		if (addedDate == null) {
			if (other.addedDate != null)
				return false;
		} else if (!addedDate.equals(other.addedDate))
			return false;
		if (archive == null) {
			if (other.archive != null)
				return false;
		} else if (!archive.equals(other.archive))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (refundAmount == null) {
			if (other.refundAmount != null)
				return false;
		} else if (!refundAmount.equals(other.refundAmount))
			return false;
		if (returnQuantity == null) {
			if (other.returnQuantity != null)
				return false;
		} else if (!returnQuantity.equals(other.returnQuantity))
			return false;
		if (stockIn == null) {
			if (other.stockIn != null)
				return false;
		} else if (!stockIn.equals(other.stockIn))
			return false;
		return true;
	}
	
}
