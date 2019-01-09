package com.whty.code.vo;

public class Server {
	/**
	 * 服务器部署模块名称
	 */
	private String name;
	/**
	 * 服务器ip
	 */
	private String ip;
	/**
	 * 服务器登陆用户
	 */
	private String username;
	/**
	 * 服务器登陆密码
	 */
	private String password;
	/**
	 * 服务部署路径
	 */
	private String serverPath;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServerPath() {
		return serverPath;
	}
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	
}
