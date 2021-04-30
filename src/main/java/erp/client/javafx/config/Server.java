package erp.client.javafx.config;

public class Server {
	
	private String protocol;
	private String ipAddress;
	private String port;
	
	public Server() {
		// TODO Auto-generated constructor stub
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Server [protocol=" + protocol + ", ipAddress=" + ipAddress + ", port=" + port + "]";
	}
	
	public String getServerUrl() {
		StringBuilder serverUrl = new StringBuilder(protocol.trim())	//Eg:-	http
				.append("://")											//		http://
				.append(ipAddress.trim())								//		http://127.0.0.1
				.append(":")											//		http://127.0.0.1:
				.append(port);											//		http://127.0.0.1:8080
		return serverUrl.toString();
	}

}
