package erp.client.javafx.entity;

import erp.client.javafx.component.enums.StockTransactionType;

import java.time.LocalDateTime;

public class TStockTransaction {

	private Long id;
	private StockTransactionType stockTransactionType;
	private TStockIn stockIn;
	private TStockReturn stockReturn;
	private LocalDateTime addedDate;
	
	public TStockTransaction() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StockTransactionType getStockTransactionType() {
		return stockTransactionType;
	}

	public void setStockTransactionType(StockTransactionType stockTransactionType) {
		this.stockTransactionType = stockTransactionType;
	}

	public TStockIn getStockIn() {
		return stockIn;
	}

	public void setStockIn(TStockIn stockIn) {
		this.stockIn = stockIn;
	}

	public TStockReturn getStockReturn() {
		return stockReturn;
	}

	public void setStockReturn(TStockReturn stockReturn) {
		this.stockReturn = stockReturn;
	}

	public LocalDateTime getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate = addedDate;
	}

	@Override
	public String toString() {
		return "TStockTransaction [id=" + id + ", stockTransactionType=" + stockTransactionType + ", stockIn=" + stockIn
				+ ", stockReturn=" + stockReturn + ", addedDate=" + addedDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addedDate == null) ? 0 : addedDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stockIn == null) ? 0 : stockIn.hashCode());
		result = prime * result + ((stockReturn == null) ? 0 : stockReturn.hashCode());
		result = prime * result + ((stockTransactionType == null) ? 0 : stockTransactionType.hashCode());
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
		TStockTransaction other = (TStockTransaction) obj;
		if (addedDate == null) {
			if (other.addedDate != null)
				return false;
		} else if (!addedDate.equals(other.addedDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stockIn == null) {
			if (other.stockIn != null)
				return false;
		} else if (!stockIn.equals(other.stockIn))
			return false;
		if (stockReturn == null) {
			if (other.stockReturn != null)
				return false;
		} else if (!stockReturn.equals(other.stockReturn))
			return false;
		if (stockTransactionType != other.stockTransactionType)
			return false;
		return true;
	}
	
}
