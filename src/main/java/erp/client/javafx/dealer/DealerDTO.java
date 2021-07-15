package erp.client.javafx.dealer;

import erp.client.javafx.gst.GstStateCodeDTO;

import java.beans.Transient;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

public class DealerDTO {

    private Long dealerId;
    private String name;
    private String shop;
    private String address;
    private String email;
    private String phone;
    private String gstin;
    private GstStateCodeDTO gstStateCode;
    private Double balance;
    private LocalDateTime addedDate;
    private LocalDateTime modifiedDate;
    private Boolean archive;

    public DealerDTO() {
    }

    public DealerDTO(Long dealerId, String name, String shop, String address, String email, String phone, String gstin, GstStateCodeDTO gstStateCode, Double balance, LocalDateTime addedDate, LocalDateTime modifiedDate, Boolean archive) {
        this.dealerId = dealerId;
        this.name = name;
        this.shop = shop;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.gstin = gstin;
        this.gstStateCode = gstStateCode;
        this.balance = balance;
        this.addedDate = addedDate;
        this.modifiedDate = modifiedDate;
        this.archive = archive;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public GstStateCodeDTO getGstStateCode() {
        return gstStateCode;
    }

    public void setGstStateCode(GstStateCodeDTO gstStateCode) {
        this.gstStateCode = gstStateCode;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DealerDTO{" +
                "dealerId=" + dealerId +
                ", name='" + name + '\'' +
                ", shop='" + shop + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gstin='" + gstin + '\'' +
                ", gstStateCode=" + gstStateCode +
                ", balance=" + balance +
                ", addedDate=" + addedDate +
                ", modifiedDate=" + modifiedDate +
                ", archive=" + archive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DealerDTO)) return false;
        DealerDTO dto = (DealerDTO) o;
        return getDealerId().equals(dto.getDealerId()) && getName().equals(dto.getName()) && Objects.equals(getShop(), dto.getShop()) && Objects.equals(getAddress(), dto.getAddress()) && Objects.equals(getEmail(), dto.getEmail()) && getPhone().equals(dto.getPhone()) && Objects.equals(getGstin(), dto.getGstin()) && Objects.equals(getGstStateCode(), dto.getGstStateCode()) && getBalance().equals(dto.getBalance()) && getArchive().equals(dto.getArchive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDealerId(), getName(), getShop(), getAddress(), getEmail(), getPhone(), getGstin(), getGstStateCode(), getBalance(), getArchive());
    }

    @Transient
    public String getBriefInfo() {
        NumberFormat rupees = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        return name + " - " + shop + " - " + gstin + " - " + phone;
    }
}
