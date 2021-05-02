package com.kyobo.stats.web.dto;

public class MRDParameter {
	public String requestCode;
	public String groupID;
	public String teamID;
	public String agentID;
	public String selBRCode;
	
	public String frDT;
	public String toDT;
	public String frTM;
	public String toTM;
	
	public String groupBy;
	public String secType;
	public String selWeek;
	public String calltreat;
	public String ronaYN;
	public String ani;
	public String sid;
	public String subYN;
	public String accType;
	
	public MRDParameter(){
		this.requestCode = "";
		this.groupID = "";
		this.teamID = "";
		this.agentID = "";
		this.selBRCode = "";
		
		this.frDT = "";
		this.toDT = "";
		this.frTM = "";
		this.toTM = "";
		
		this.groupBy = "";
		this.secType = "";
		this.selWeek = "";
		this.calltreat = "";
		this.ronaYN = "";
		this.ani = "";
		this.sid = "";
		this.subYN = "";
		this.accType = "";
	}

	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getSelBRCode() {
		return selBRCode;
	}

	public void setSelBRCode(String selBRCode) {
		this.selBRCode = selBRCode;
	}

	public String getFrDT() {
		return frDT;
	}

	public void setFrDT(String frDT) {
		this.frDT = frDT;
	}

	public String getToDT() {
		return toDT;
	}

	public void setToDT(String toDT) {
		this.toDT = toDT;
	}

	public String getFrTM() {
		return frTM;
	}

	public void setFrTM(String frTM) {
		this.frTM = frTM;
	}

	public String getToTM() {
		return toTM;
	}

	public void setToTM(String toTM) {
		this.toTM = toTM;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getSecType() {
		return secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	public String getSelWeek() {
		return selWeek;
	}

	public void setSelWeek(String selWeek) {
		this.selWeek = selWeek;
	}

	public String getCalltreat() {
		return calltreat;
	}

	public void setCalltreat(String calltreat) {
		this.calltreat = calltreat;
	}

	public String getRonaYN() {
		return ronaYN;
	}

	public void setRonaYN(String ronaYN) {
		this.ronaYN = ronaYN;
	}

	public String getAni() {
		return ani;
	}

	public void setAni(String ani) {
		this.ani = ani;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSubYN() {
		return subYN;
	}

	public void setSubYN(String subYN) {
		this.subYN = subYN;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}
}
