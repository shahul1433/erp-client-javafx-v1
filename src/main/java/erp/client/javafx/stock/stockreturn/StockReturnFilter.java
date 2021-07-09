package erp.client.javafx.stock.stockreturn;

import erp.client.javafx.component.date.DateSearchable;
import erp.client.javafx.http.DatabaseTableFilter;
import erp.client.javafx.http.SortMap;

public class StockReturnFilter extends DatabaseTableFilter {

	private StockReturnDTO stockReturn;
	private DateSearchable addedDate;
	
	public StockReturnFilter(StockReturnDTO stockReturn, DateSearchable addedDate, int page, int size, SortMap sortMap) {
		super(page, size, sortMap);
		this.stockReturn = stockReturn;
		this.addedDate = addedDate;
	}

	public StockReturnDTO getStockReturn() {
		return stockReturn;
	}

	public void setStockReturn(StockReturnDTO stockReturn) {
		this.stockReturn = stockReturn;
	}

	public DateSearchable getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(DateSearchable addedDate) {
		this.addedDate = addedDate;
	}

}
