package com.kyobo.stats.model;

import java.util.Date;

public class AgentTeam {
	private String AgentTeamID;
	private String AgentTeamEng;
	private String AgentTeamKor;
	private int ChangeStamp;
	public String getAgentTeamID() {
		return AgentTeamID;
	}
	public void setAgentTeamID(String agentTeamID) {
		AgentTeamID = agentTeamID;
	}
	public String getAgentTeamEng() {
		return AgentTeamEng;
	}
	public void setAgentTeamEng(String agentTeamEng) {
		AgentTeamEng = agentTeamEng;
	}
	public String getAgentTeamKor() {
		return AgentTeamKor;
	}
	public void setAgentTeamKor(String agentTeamKor) {
		AgentTeamKor = agentTeamKor;
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
	public Date getDtDeleted() {
		return DtDeleted;
	}
	public void setDtDeleted(Date dtDeleted) {
		DtDeleted = dtDeleted;
	}
	private String Deleted;
	private Date DtRgst;
	private Date DtDeleted;
}
