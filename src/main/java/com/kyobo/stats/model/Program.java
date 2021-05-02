package com.kyobo.stats.model;

import java.util.Date;

public class Program {
	private int IDX;
	private String PgmID;
	private String PgmNM;
	private String ParentPgmID;
	private String UseYN;
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
	public String getPgmNM() {
		return PgmNM;
	}
	public void setPgmNM(String pgmNM) {
		PgmNM = pgmNM;
	}
	public String getParentPgmID() {
		return ParentPgmID;
	}
	public void setParentPgmID(String parentPgmID) {
		ParentPgmID = parentPgmID;
	}
	public String getUseYN() {
		return UseYN;
	}
	public void setUseYN(String useYN) {
		UseYN = useYN;
	}
	public Date getDtRgst() {
		return DtRgst;
	}
	public void setDtRgst(Date dtRgst) {
		DtRgst = dtRgst;
	}
	
}
