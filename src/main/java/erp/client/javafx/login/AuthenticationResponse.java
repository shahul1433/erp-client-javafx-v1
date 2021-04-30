package erp.client.javafx.login;

public class AuthenticationResponse {

	private String token;
	
	public AuthenticationResponse() {
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
