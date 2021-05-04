package erp.client.javafx.container.status;

public enum StatusBarStatus {
	WORKING ("Working..."),
	READY ("Ready"),
	ERROR ("Error");

	private String status;

	StatusBarStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
