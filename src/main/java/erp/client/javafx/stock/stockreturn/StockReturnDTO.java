package erp.client.javafx.stock.stockreturn;

import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.user.UserDTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class StockReturnDTO {

    private Long stockReturnId;
    private StockInDTO stockIn;
    private String reason;
    private Double returnQuantity;
    private Double refundAmount;
    private LocalDateTime addedDate;
    private UserDTO addedBy;
    private Boolean archive;

    public StockReturnDTO() {
    }

    public StockReturnDTO(Long stockReturnId, StockInDTO stockIn, String reason, Double returnQuantity, Double refundAmount, LocalDateTime addedDate, UserDTO addedBy, Boolean archive) {
        this.stockReturnId = stockReturnId;
        this.stockIn = stockIn;
        this.reason = reason;
        this.returnQuantity = returnQuantity;
        this.refundAmount = refundAmount;
        this.addedDate = addedDate;
        this.addedBy = addedBy;
        this.archive = archive;
    }

    public Long getStockReturnId() {
        return stockReturnId;
    }

    public void setStockReturnId(Long stockReturnId) {
        this.stockReturnId = stockReturnId;
    }

    public StockInDTO getStockIn() {
        return stockIn;
    }

    public void setStockIn(StockInDTO stockIn) {
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

    public UserDTO getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserDTO addedBy) {
        this.addedBy = addedBy;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "StockReturnDTO{" +
                "stockReturnId=" + stockReturnId +
                ", stockIn=" + stockIn +
                ", reason='" + reason + '\'' +
                ", returnQuantity=" + returnQuantity +
                ", refundAmount=" + refundAmount +
                ", addedDate=" + addedDate +
                ", addedBy=" + addedBy +
                ", archive=" + archive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockReturnDTO)) return false;
        StockReturnDTO that = (StockReturnDTO) o;
        return getStockReturnId().equals(that.getStockReturnId()) && getStockIn().equals(that.getStockIn()) && getReason().equals(that.getReason()) && getReturnQuantity().equals(that.getReturnQuantity()) && getRefundAmount().equals(that.getRefundAmount()) && getAddedDate().equals(that.getAddedDate()) && getAddedBy().equals(that.getAddedBy()) && getArchive().equals(that.getArchive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockReturnId(), getStockIn(), getReason(), getReturnQuantity(), getRefundAmount(), getAddedDate(), getAddedBy(), getArchive());
    }
}
