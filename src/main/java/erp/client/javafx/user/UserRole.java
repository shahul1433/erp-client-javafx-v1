package erp.client.javafx.user;

import erp.client.javafx.entity.TUserRole;
import javafx.beans.property.SimpleStringProperty;

public class UserRole {

	private SimpleStringProperty role;
	
	private TUserRole userRole;
	
	public UserRole(TUserRole useRole) {
		this.userRole = useRole;
		this.role = new SimpleStringProperty(userRole.getRole());
	}
	
	public TUserRole getUserRole() {
		return userRole;
	}
	
	public void setRole(String role) {
		this.role.set(role);
	}
	
	public String getRole() {
		return this.role.get();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
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
		UserRole other = (UserRole) obj;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}
	
}
