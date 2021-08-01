package erp.client.javafx.stock.transaction;

import erp.client.javafx.component.date.DateSearchable;
import erp.client.javafx.http.DatabaseTableFilter;
import erp.client.javafx.http.SortMap;

public class StockTransactionFilter extends DatabaseTableFilter{

	private StockTransactionDTO stockTransaction;
	private DateSearchable addedDate;

	public StockTransactionFilter(StockTransactionDTO stockTransaction, DateSearchable addedDate, int page, int size, SortMap sortMap) {
		super(page, size, sortMap);
		this.stockTransaction = stockTransaction;
		this.addedDate = addedDate;
	}

	public StockTransactionDTO getStockTransaction() {
		return stockTransaction;
	}

	public void setStockTransaction(StockTransactionDTO stockTransaction) {
		this.stockTransaction = stockTransaction;
	}

	public DateSearchable getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(DateSearchable addedDate) {
		this.addedDate = addedDate;
	}

}
