package com.whty.code.vo;

import java.util.List;

public class CodeReleaseShell {
	/**
	 * 代码版本
	 */
	private String codeVersion;
	/**
	 * 源码目录
	 */
	private String sourcePath;
	/**
	 * 发布的平台名称
	 */
	private String platformName;
	/**
	 * 服务器信息
	 */
	private List<Server> Servers;
	public String getCodeVersion() {
		return codeVersion;
	}
	public void setCodeVersion(String codeVersion) {
		this.codeVersion = codeVersion;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public List<Server> getServers() {
		return Servers;
	}
	public void setServers(List<Server> servers) {
		Servers = servers;
	}
}
