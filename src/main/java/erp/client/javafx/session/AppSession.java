package erp.client.javafx.session;

import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.entity.TUser;
import erp.client.javafx.entity.TUserRole;

public class AppSession {

	private static String authorization;
	private static TUser loggedUser;
	
	public AppSession() {
		// TODO Auto-generated constructor stub
	}

	public static String getAuthorization() {
		return authorization;
	}

	public static void setAuthorization(String authorization) {
		AppSession.authorization = authorization;
	}

	public static TUser getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(TUser loggedUser) {
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
		for(TUserRole ur: loggedUser.getRoles()) {
			if(ur.getRole().equalsIgnoreCase(userRole.getName())) {
				return true;
			}
		}
		return false;
	}
}
