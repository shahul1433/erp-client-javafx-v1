package erp.client.javafx.entity;

import java.time.LocalDateTime;

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
	
	public TDealer() {
		// TODO Auto-generated constructor stub
	}

	public TDealer(String name, String shop, String address, String email, String phone, String gstin,
			TGstStateCode gstStateCode, Double balance, LocalDateTime addedDate, LocalDateTime modifiedDate,
			Boolean archive) {
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((archive == null) ? 0 : archive.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gstStateCode == null) ? 0 : gstStateCode.hashCode());
		result = prime * result + ((gstin == null) ? 0 : gstin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((shop == null) ? 0 : shop.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TDealer other = (TDealer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (archive == null) {
			if (other.archive != null)
				return false;
		} else if (!archive.equals(other.archive))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gstStateCode == null) {
			if (other.gstStateCode != null)
				return false;
		} else if (!gstStateCode.equals(other.gstStateCode))
			return false;
		if (gstin == null) {
			if (other.gstin != null)
				return false;
		} else if (!gstin.equals(other.gstin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (shop == null) {
			if (other.shop != null)
				return false;
		} else if (!shop.equals(other.shop))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TDealer [id=" + id + ", name=" + name + ", shop=" + shop + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ", gstin=" + gstin + ", gstStateCode=" + gstStateCode + ", balance=" + balance
				+ ", addedDate=" + addedDate + ", modifiedDate=" + modifiedDate + ", archive=" + archive + "]";
	}
	
}
