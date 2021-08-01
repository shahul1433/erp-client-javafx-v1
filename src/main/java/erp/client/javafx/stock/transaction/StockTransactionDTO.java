package erp.client.javafx.stock.transaction;

import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.stock.stockreturn.StockReturnDTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class StockTransactionDTO {

    private Long stockTransactionId;
    private StockTransactionType stockTransactionType;
    private StockInDTO stockIn;
    private StockReturnDTO stockReturn;
    private LocalDateTime addedDate;

    public StockTransactionDTO() {

    }

    public StockTransactionDTO(Long stockTransactionId, StockTransactionType stockTransactionType, StockInDTO stockIn, StockReturnDTO stockReturn, LocalDateTime addedDate) {
        this.stockTransactionId = stockTransactionId;
        this.stockTransactionType = stockTransactionType;
        this.stockIn = stockIn;
        this.stockReturn = stockReturn;
        this.addedDate = addedDate;
    }

    public Long getStockTransactionId() {
        return stockTransactionId;
    }

    public void setStockTransactionId(Long stockTransactionId) {
        this.stockTransactionId = stockTransactionId;
    }

    public StockTransactionType getStockTransactionType() {
        return stockTransactionType;
    }

    public void setStockTransactionType(StockTransactionType stockTransactionType) {
        this.stockTransactionType = stockTransactionType;
    }

    public StockInDTO getStockIn() {
        return stockIn;
    }

    public void setStockIn(StockInDTO stockIn) {
        this.stockIn = stockIn;
    }

    public StockReturnDTO getStockReturn() {
        return stockReturn;
    }

    public void setStockReturn(StockReturnDTO stockReturn) {
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
        return "StockTransactionDTO{" +
                "stockTransactionId=" + stockTransactionId +
                ", stockTransactionType=" + stockTransactionType +
                ", stockIn=" + stockIn +
                ", stockReturn=" + stockReturn +
                ", addedDate=" + addedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockTransactionDTO)) return false;
        StockTransactionDTO that = (StockTransactionDTO) o;
        return getStockTransactionId().equals(that.getStockTransactionId()) && getStockTransactionType() == that.getStockTransactionType() && Objects.equals(getStockIn(), that.getStockIn()) && Objects.equals(getStockReturn(), that.getStockReturn()) && getAddedDate().equals(that.getAddedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockTransactionId(), getStockTransactionType(), getStockIn(), getStockReturn(), getAddedDate());
    }
}
