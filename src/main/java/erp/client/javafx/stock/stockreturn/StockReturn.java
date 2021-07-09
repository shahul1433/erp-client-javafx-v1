package erp.client.javafx.stock.stockreturn;

import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.user.UserDTO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

public class StockReturn {

    private final SimpleObjectProperty<StockInDTO> stockIn;
    private final SimpleStringProperty reason;
    private final SimpleDoubleProperty returnQuantity;
    private final SimpleDoubleProperty refundAmount;
    private final SimpleObjectProperty<LocalDateTime> addedDate;
    private final SimpleObjectProperty<UserDTO> addedBy;

    private final StockReturnDTO stockReturnDTO;

    public StockReturn(StockReturnDTO stockReturnDTO) {
        this.stockReturnDTO = stockReturnDTO;
        this.stockIn = new SimpleObjectProperty<StockInDTO>(stockReturnDTO.getStockIn());
        this.reason = new SimpleStringProperty(stockReturnDTO.getReason());
        this.returnQuantity = new SimpleDoubleProperty(stockReturnDTO.getReturnQuantity());
        this.refundAmount = new SimpleDoubleProperty(stockReturnDTO.getRefundAmount());
        this.addedDate = new SimpleObjectProperty<>(stockReturnDTO.getAddedDate());
        this.addedBy = new SimpleObjectProperty<>(stockReturnDTO.getAddedBy());
    }

    public StockInDTO getStockIn() {
        return stockIn.get();
    }

    public SimpleObjectProperty<StockInDTO> stockInProperty() {
        return stockIn;
    }

    public void setStockIn(StockInDTO stockIn) {
        this.stockIn.set(stockIn);
    }

    public String getReason() {
        return reason.get();
    }

    public SimpleStringProperty reasonProperty() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason.set(reason);
    }

    public double getReturnQuantity() {
        return returnQuantity.get();
    }

    public SimpleDoubleProperty returnQuantityProperty() {
        return returnQuantity;
    }

    public void setReturnQuantity(double returnQuantity) {
        this.returnQuantity.set(returnQuantity);
    }

    public double getRefundAmount() {
        return refundAmount.get();
    }

    public SimpleDoubleProperty refundAmountProperty() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount.set(refundAmount);
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

    public UserDTO getAddedBy() {
        return addedBy.get();
    }

    public SimpleObjectProperty<UserDTO> addedByProperty() {
        return addedBy;
    }

    public void setAddedBy(UserDTO addedBy) {
        this.addedBy.set(addedBy);
    }

    public StockReturnDTO getStockReturnDTO() {
        return stockReturnDTO;
    }
}
