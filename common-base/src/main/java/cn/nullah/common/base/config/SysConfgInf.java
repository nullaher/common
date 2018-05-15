package cn.nullah.common.base.config;

public class SysConfgInf {
	private String platfmId;
	private String localIp;
	private String publicIp;
	private String serverPort;
	private String logPath;

	public String getPlatfmId() {
		return platfmId;
	}

	public void setPlatfmId(String platfmId) {
		this.platfmId = platfmId;
	}

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

}
