package erp.client.javafx.stock.stockin;

import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.user.UserDTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class StockInDTO {

    private Long stockInId;
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
    private DealerDTO dealer;
    private UserDTO addedBy;
    private LocalDateTime addedDate;
    private Boolean archive;

    public StockInDTO() {
    }

    public StockInDTO(Long stockInId, String barcode, String name, String model, String category, String company, String warranty, String guarantee, Double stockQuantity, Double currentQuantity, Double reorderLimit, Double stockPrice, Double customerPrice, Double gst, Double gstAmount, Double netAmount, ProductScale scale, String specifications, DealerDTO dealer, UserDTO addedBy, LocalDateTime addedDate, Boolean archive) {
        this.stockInId = stockInId;
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

    public Long getStockInId() {
        return stockInId;
    }

    public void setStockInId(Long stockInId) {
        this.stockInId = stockInId;
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

    public DealerDTO getDealer() {
        return dealer;
    }

    public void setDealer(DealerDTO dealer) {
        this.dealer = dealer;
    }

    public UserDTO getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserDTO addedBy) {
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
        return "StockInDTO{" +
                "stockInId=" + stockInId +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", company='" + company + '\'' +
                ", warranty='" + warranty + '\'' +
                ", guarantee='" + guarantee + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", currentQuantity=" + currentQuantity +
                ", reorderLimit=" + reorderLimit +
                ", stockPrice=" + stockPrice +
                ", customerPrice=" + customerPrice +
                ", gst=" + gst +
                ", gstAmount=" + gstAmount +
                ", netAmount=" + netAmount +
                ", scale=" + scale +
                ", specifications='" + specifications + '\'' +
                ", dealer=" + dealer +
                ", addedBy=" + addedBy +
                ", addedDate=" + addedDate +
                ", archive=" + archive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockInDTO)) return false;
        StockInDTO that = (StockInDTO) o;
        return getStockInId().equals(that.getStockInId()) && Objects.equals(getBarcode(), that.getBarcode()) && getName().equals(that.getName()) && Objects.equals(getModel(), that.getModel()) && Objects.equals(getCategory(), that.getCategory()) && Objects.equals(getCompany(), that.getCompany()) && Objects.equals(getWarranty(), that.getWarranty()) && Objects.equals(getGuarantee(), that.getGuarantee()) && getStockQuantity().equals(that.getStockQuantity()) && getCurrentQuantity().equals(that.getCurrentQuantity()) && Objects.equals(getReorderLimit(), that.getReorderLimit()) && getStockPrice().equals(that.getStockPrice()) && getCustomerPrice().equals(that.getCustomerPrice()) && Objects.equals(getGst(), that.getGst()) && Objects.equals(getGstAmount(), that.getGstAmount()) && getNetAmount().equals(that.getNetAmount()) && getScale() == that.getScale() && Objects.equals(getSpecifications(), that.getSpecifications()) && getDealer().equals(that.getDealer()) && getAddedBy().equals(that.getAddedBy()) && getAddedDate().equals(that.getAddedDate()) && getArchive().equals(that.getArchive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockInId(), getBarcode(), getName(), getModel(), getCategory(), getCompany(), getWarranty(), getGuarantee(), getStockQuantity(), getCurrentQuantity(), getReorderLimit(), getStockPrice(), getCustomerPrice(), getGst(), getGstAmount(), getNetAmount(), getScale(), getSpecifications(), getDealer(), getAddedBy(), getAddedDate(), getArchive());
    }
}
