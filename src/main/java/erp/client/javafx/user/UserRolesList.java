package erp.client.javafx.user;

import java.util.List;

import erp.client.javafx.entity.TUserRole;

public class UserRolesList {

	private List<TUserRole> roles;
	
	public UserRolesList() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRolesList(List<TUserRole> roles) {
		this.roles = roles;
	}

	public List<TUserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TUserRole> roles) {
		this.roles = roles;
	}
	
}