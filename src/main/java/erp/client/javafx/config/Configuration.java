package erp.client.javafx.config;

public class Configuration {

	private Server server;
	
	public Configuration() {
		// TODO Auto-generated constructor stub
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	@Override
	public String toString() {
		return "Configuration [server=" + server + "]";
	}
	
	public void createDefaultConfiguration() {
		Server server = new Server();
		server.setProtocol("http");
		server.setIpAddress("127.0.0.1");
		server.setPort("8080");
		
		setServer(server);
	}
}
