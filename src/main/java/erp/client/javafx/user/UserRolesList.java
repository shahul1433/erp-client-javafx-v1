package erp.client.javafx.user;

import java.util.List;

public class UserRolesList {

	private List<UserRoleDTO> roles;
	
	public UserRolesList() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRolesList(List<UserRoleDTO> roles) {
		this.roles = roles;
	}

	public List<UserRoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoleDTO> roles) {
		this.roles = roles;
	}
	
}