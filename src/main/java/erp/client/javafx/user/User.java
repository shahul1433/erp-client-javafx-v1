package erp.client.javafx.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import erp.client.javafx.entity.TUser;
import erp.client.javafx.entity.UserType;
import javafx.beans.property.SimpleStringProperty;

public class User{
	private final SimpleStringProperty name;
	private final SimpleStringProperty userType;
	private final SimpleStringProperty designation;
	private final SimpleStringProperty email;
	private final SimpleStringProperty phone;
	private final SimpleStringProperty username;
	private final SimpleStringProperty addedDate;
	private final SimpleStringProperty modifiedDate;
	
	private TUser user;
	
	public User(TUser user) {
		this.user = user;
		this.name = new SimpleStringProperty(user.getName());
		this.userType = new SimpleStringProperty(getUserType(user.getUserType()));
		this.designation = new SimpleStringProperty(user.getDesignation());
		this.email = new SimpleStringProperty(user.getEmail());
		this.phone = new SimpleStringProperty(user.getPhone());
		this.username = new SimpleStringProperty(user.getUsername());
		this.addedDate = user.getAddedDate() != null ? new SimpleStringProperty(user.getAddedDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))) : new SimpleStringProperty("");
		this.modifiedDate = user.getModifiedDate() != null ? new SimpleStringProperty(user.getModifiedDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))) : new SimpleStringProperty("");
	}
	
	
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getUserType() {
		return userType.get();
	}
	
	public void setUserType(String userType) {
		this.userType.set(userType);
	}
	
	public String getDesignation() {
		return designation.get();
	}
	
	public void setDesignation(String designation) {
		this.designation.set(designation);
	}

	public String getEmail() {
		return email.get();
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}

	public String getPhone() {
		return phone.get();
	}
	
	public void setPhone(String phone) {
		this.phone.set(phone);
	}

	public String getUsername() {
		return username.get();
	}
	
	public void setUsername(String username) {
		this.username.set(username);
	}

	public String getAddedDate() {
		return addedDate.get();
	}
	
	public void setAddedDate(LocalDateTime createdDate) {
		if(createdDate != null)
			this.addedDate.set(createdDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
		else
			this.addedDate.set("");
	}

	public String getModifiedDate() {
		return modifiedDate.get();
	}
	
	public void setModifiedDate(LocalDateTime modifiedDate) {
		if(modifiedDate != null)
			this.modifiedDate.set(modifiedDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
		else
			this.modifiedDate.set("");
	}

	public TUser getUser() {
		return user;
	}
	
	private String getUserType(UserType userType) {
		switch (userType) {
		case ADMINISTRATOR:
			return UserType.ADMINISTRATOR.getUserType();
		case LOCAL_USER:
			return UserType.LOCAL_USER.getUserType();
		default:
			return "null";
		}
	}
}