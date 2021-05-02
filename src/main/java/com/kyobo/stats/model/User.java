package com.kyobo.stats.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties({"UserPWD"})
public class User {
	
	private int IDX;
	private String UserID;
	private String UserNM;
	private byte[] UserPWD;
	private int PWDErrCnt;
	private String PWDErrDt;
	private String LockYN;
	private String DtRgst;
	private String UserPWDText; 
	
	@JsonProperty("UserPWDText")
	public String getUserPWDText() {
		return new String(UserPWD);
	}

	public void setUserPWDText(String userPWDText) {
		UserPWDText = userPWDText;
		UserPWD =  UserPWDText.getBytes();
	}

	public int getIDX() {
		return IDX;
	}
	
	@JsonProperty("IDX")
	public void setIDX(int iDX) {
		this.IDX = iDX;
	}
	@JsonProperty("UserID")
	public String getUserID() {
		return this.UserID;
	}
	public void setUserID(String userID) {
		this.UserID = userID;
	}
	@JsonProperty("UserNM")
	public String getUserNM() {
		return this.UserNM;
	}
	public void setUserNM(String userNM) {
		this.UserNM = userNM;
	}
	
	@JsonProperty("UserPWD")
	public byte[] getUserPWD() {
		return this.UserPWD;
	}

	public void setUserPWD(byte[] userPWD) {
		this.UserPWD = userPWD;
		this.UserPWDText  = new String(this.UserPWD);
	}
	
	@JsonProperty("PWDErrCnt")
	public int getPWDErrCnt() {
		return this.PWDErrCnt;
	}


	public void setPWDErrCnt(int pWDErrCnt) {
		this.PWDErrCnt = pWDErrCnt;
	}
	
	@JsonProperty("PWDErrDt")
	public String getPWDErrDt() {
		return PWDErrDt;
	}
	public void setPWDErrDt(String pWDErrDt) {
		this.PWDErrDt = pWDErrDt;
	}
	
	@JsonProperty("LockYN")
	public String getLockYN() {
		return this.LockYN;
	}
	public void setLockYN(String lockYN) {
		this.LockYN = lockYN;
	}
	
	@JsonProperty("DtRgst")
	public String getDtRgst() {
		return DtRgst;
	}
	public void setDtRgst(String dtRgst) {
		this.DtRgst = dtRgst;
	}
	
	
}
