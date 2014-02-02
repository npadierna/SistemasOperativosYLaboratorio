package co.edu.udea.os.ahorcado.service.config.impl;

public class WebServiceServer {

	private String context;
	private String ip;
	private String port;
	private String protocol;
	private String webService;

	public WebServiceServer() {
		super();
		this.setContext(null);
		this.setIp(null);
		this.setPort(null);
		this.setProtocol(null);
		this.setWebService(null);
	}

	public String getContext() {

		return (this.context);
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getIp() {

		return (this.ip);
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {

		return (this.port);
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProtocol() {

		return (this.protocol);
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getWebService() {

		return (this.webService);
	}

	public void setWebService(String webService) {
		this.webService = webService;
	}

	@Override()
	public String toString() {
		StringBuilder s = new StringBuilder();

		s.append("Context: ").append(this.getContext()).append("\n");
		s.append("IP: ").append(this.getIp()).append("\n");
		s.append("Port: ").append(this.getPort()).append("\n");
		s.append("Protocol: ").append(this.getProtocol()).append("\n");
		s.append("Web Service: ").append(this.getWebService()).append("\n");

		return (s.toString());
	}
}
