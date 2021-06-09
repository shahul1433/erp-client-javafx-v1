package erp.client.javafx.session;

import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.user.UserDTO;
import erp.client.javafx.user.UserRoleDTO;

public class AppSession {

	private static String authorization;
	private static UserDTO loggedUser;
	
	public AppSession() {
		// TODO Auto-generated constructor stub
	}

	public static String getAuthorization() {
		return authorization;
	}

	public static void setAuthorization(String authorization) {
		AppSession.authorization = authorization;
	}

	public static UserDTO getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(UserDTO loggedUser) {
		AppSession.loggedUser = loggedUser;
	}
	
	/*
	 * This method will clear Authorization token and Logged User info from the session.
	 */
	public static void clearSession() {
		setAuthorization(null);
		setLoggedUser(null);
	}
	
	public static boolean hasRole(UserRole userRole) {
		for(UserRoleDTO ur: loggedUser.getRoles()) {
			if(ur.getRole().equalsIgnoreCase(userRole.getName())) {
				return true;
			}
		}
		return false;
	}
}
