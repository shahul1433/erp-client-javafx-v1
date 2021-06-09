package erp.client.javafx.stock.stockin;

import erp.client.javafx.component.date.DateSearchable;
import erp.client.javafx.http.DatabaseTableFilter;
import erp.client.javafx.http.SortMap;

public class StockInFilter extends DatabaseTableFilter{

	private StockInDTO stockIn;
	private DateSearchable addedDate;
	
	public StockInFilter(StockInDTO stockIn, DateSearchable addedDate, int page, int size, SortMap sortMap) {
		super(page, size, sortMap);
		this.stockIn = stockIn;
		this.addedDate = addedDate;
	}

	public StockInDTO getStockIn() {
		return stockIn;
	}

	public void setStockIn(StockInDTO stockIn) {
		this.stockIn = stockIn;
	}

	public DateSearchable getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(DateSearchable addedDate) {
		this.addedDate = addedDate;
	}

}
