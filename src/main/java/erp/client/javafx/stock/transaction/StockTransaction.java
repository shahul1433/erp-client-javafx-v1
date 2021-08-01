package erp.client.javafx.stock.transaction;

import erp.client.javafx.stock.stockin.StockInDTO;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class StockTransaction {

	private final SimpleObjectProperty<erp.client.javafx.stock.transaction.StockTransactionType> stockTransactionType;
	private final SimpleObjectProperty<StockInDTO> product;
	private final SimpleObjectProperty<Double> amount;
	private final SimpleObjectProperty<LocalDateTime> addedDate;
	
	private StockTransactionDTO stockTransaction;
	
	public StockTransaction(StockTransactionDTO transaction) {
		this.stockTransaction = transaction;
		this.stockTransactionType = new SimpleObjectProperty<>(transaction.getStockTransactionType());
		if (transaction.getStockTransactionType() == erp.client.javafx.stock.transaction.StockTransactionType.STOCK_IN) {
            this.product = new SimpleObjectProperty<>(transaction.getStockIn());
            this.amount = new SimpleObjectProperty<>(transaction.getStockIn().getNetAmount());
        }else {
            this.product = new SimpleObjectProperty<>(transaction.getStockReturn().getStockIn());
            this.amount = new SimpleObjectProperty<>(transaction.getStockReturn().getRefundAmount());
        }
		this.addedDate = new SimpleObjectProperty<>(transaction.getAddedDate());
	}

    public StockTransactionType getStockTransactionType() {
        return stockTransactionType.get();
    }

    public SimpleObjectProperty<StockTransactionType> stockTransactionTypeProperty() {
        return stockTransactionType;
    }

    public void setStockTransactionType(StockTransactionType stockTransactionType) {
        this.stockTransactionType.set(stockTransactionType);
    }

    public StockInDTO getProduct() {
        return product.get();
    }

    public SimpleObjectProperty<StockInDTO> productProperty() {
        return product;
    }

    public void setProduct(StockInDTO product) {
        this.product.set(product);
    }

    public Double getAmount() {
        return amount.get();
    }

    public SimpleObjectProperty<Double> amountProperty() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount.set(amount);
    }

    public LocalDateTime getAddedDate() {
        return addedDate.get();
    }

    public SimpleObjectProperty<LocalDateTime> addedDateProperty() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate.set(addedDate);
    }

    public StockTransactionDTO getStockTransaction() {
        return stockTransaction;
    }

    public void setStockTransaction(StockTransactionDTO stockTransaction) {
        this.stockTransaction = stockTransaction;
    }
}
