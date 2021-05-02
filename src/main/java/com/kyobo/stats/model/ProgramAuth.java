package com.kyobo.stats.model;

import java.util.Date;

public class ProgramAuth {
	private int IDX;
	private String PgmID;
	private String UserID;
	private String RgstUserID;
	private Date DtRgst;
	public int getIDX() {
		return IDX;
	}
	public void setIDX(int iDX) {
		IDX = iDX;
	}
	public String getPgmID() {
		return PgmID;
	}
	public void setPgmID(String pgmID) {
		PgmID = pgmID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getRgstUserID() {
		return RgstUserID;
	}
	public void setRgstUserID(String rgstUserID) {
		RgstUserID = rgstUserID;
	}
	public Date getDtRgst() {
		return DtRgst;
	}
	public void setDtRgst(Date dtRgst) {
		DtRgst = dtRgst;
	}
	
	
}
