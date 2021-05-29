package erp.client.javafx.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class TDealer {

	private Long id;
	private String name;
	private String shop;
	private String address;
	private String email;
	private String phone;
	private String gstin;
	private TGstStateCode gstStateCode;
	private Double balance;
	private LocalDateTime addedDate;
	private LocalDateTime modifiedDate;
	private Boolean archive;
	private List<TLedger> ledgers;
	
	public TDealer() {
		// TODO Auto-generated constructor stub
	}

	public TDealer(String name, String shop, String address, String email, String phone, String gstin, TGstStateCode gstStateCode, Double balance, LocalDateTime addedDate, LocalDateTime modifiedDate, Boolean archive, List<TLedger> ledgers) {
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
		this.ledgers = ledgers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public TGstStateCode getGstStateCode() {
		return gstStateCode;
	}

	public void setGstStateCode(TGstStateCode gstStateCode) {
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

	public List<TLedger> getLedgers() {
		return ledgers;
	}

	public void setLedgers(List<TLedger> ledgers) {
		this.ledgers = ledgers;
	}

	@Override
	public String toString() {
		return "TDealer{" +
				"id=" + id +
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
				", ledgers=" + ledgers +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TDealer dealer = (TDealer) o;
		return Objects.equals(id, dealer.id) && Objects.equals(name, dealer.name) && Objects.equals(shop, dealer.shop) && Objects.equals(address, dealer.address) && Objects.equals(email, dealer.email) && Objects.equals(phone, dealer.phone) && Objects.equals(gstin, dealer.gstin) && Objects.equals(gstStateCode, dealer.gstStateCode) && Objects.equals(balance, dealer.balance) && Objects.equals(addedDate, dealer.addedDate) && Objects.equals(modifiedDate, dealer.modifiedDate) && Objects.equals(archive, dealer.archive) && Objects.equals(ledgers, dealer.ledgers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, shop, address, email, phone, gstin, gstStateCode, balance, addedDate, modifiedDate, archive, ledgers);
	}
}
