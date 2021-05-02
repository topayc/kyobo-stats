package com.kyobo.stats.web.dto;

import java.util.Date;

import com.kyobo.stats.utils.Crypto;

public class UserSession {
	private String type;;
	private String id;
	private String password;
	private String cryptoPassword;
	private String remoteIp;
	private boolean loginStatus;
	private Date loginDate;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		try {
			this.cryptoPassword = Crypto.getEncMD5(this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getCryptoPassword() {
		return cryptoPassword;
	}
	public void setCryptoPassword(String cryptoPassword) {
		this.cryptoPassword = cryptoPassword;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
}
