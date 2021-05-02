package com.kyobo.stats.model;

import java.util.Date;

public class SkillGroup {
	private int SkillGroupID;
	private int SkillGroupNumber;
	private String SkillGroupEng;
	private String SkillGroupKor;
	private int Ordr;
	private int ChangeStamp;
	private String Deleted;
	private Date DtRgst;
	private Date tDeleted;
	public int getSkillGroupID() {
		return SkillGroupID;
	}
	public void setSkillGroupID(int skillGroupID) {
		SkillGroupID = skillGroupID;
	}
	public int getSkillGroupNumber() {
		return SkillGroupNumber;
	}
	public void setSkillGroupNumber(int skillGroupNumber) {
		SkillGroupNumber = skillGroupNumber;
	}
	public String getSkillGroupEng() {
		return SkillGroupEng;
	}
	public void setSkillGroupEng(String skillGroupEng) {
		SkillGroupEng = skillGroupEng;
	}
	public String getSkillGroupKor() {
		return SkillGroupKor;
	}
	public void setSkillGroupKor(String skillGroupKor) {
		SkillGroupKor = skillGroupKor;
	}
	public int getOrdr() {
		return Ordr;
	}
	public void setOrdr(int ordr) {
		Ordr = ordr;
	}
	public int getChangeStamp() {
		return ChangeStamp;
	}
	public void setChangeStamp(int changeStamp) {
		ChangeStamp = changeStamp;
	}
	public String getDeleted() {
		return Deleted;
	}
	public void setDeleted(String deleted) {
		Deleted = deleted;
	}
	public Date getDtRgst() {
		return DtRgst;
	}
	public void setDtRgst(Date dtRgst) {
		DtRgst = dtRgst;
	}
	public Date gettDeleted() {
		return tDeleted;
	}
	public void settDeleted(Date tDeleted) {
		this.tDeleted = tDeleted;
	}
	
	

}
