package erp.client.javafx.http;

public class RefreshTokenAttributes {

	private boolean isTokenRefreshed;
	private int maximumAttempt;
	private int currentAttempt;
	
	public RefreshTokenAttributes(int maximumAttempt) {
		this.isTokenRefreshed = false;
		this.maximumAttempt = maximumAttempt;
		this.currentAttempt = 0;
	}

	public boolean isTokenRefreshed() {
		return isTokenRefreshed;
	}

	public void setTokenRefreshed(boolean isTokenRefreshed) {
		this.isTokenRefreshed = isTokenRefreshed;
	}

	public int getMaximumAttempt() {
		return maximumAttempt;
	}

	public void setMaximumAttempt(int maximumAttempt) {
		this.maximumAttempt = maximumAttempt;
	}

	public int getCurrentAttempt() {
		return currentAttempt;
	}

	public void setCurrentAttempt(int currentAttempt) {
		this.currentAttempt = currentAttempt;
	}
	
}
