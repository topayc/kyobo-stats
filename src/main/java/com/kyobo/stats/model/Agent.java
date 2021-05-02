package com.kyobo.stats.model;

import java.util.Date;

public class Agent {
	private String AgentID;
	private String AgentName;
	private String AgentTeamID;
	private int ChangeStamp;
	private int ChangeStamp2;
	private int DefaultSkillGroupID;
	private String Deleted;
	private Date DtRgst;
	private Date DtDeleted;
	public String getAgentID() {
		return AgentID;
	}
	public void setAgentID(String agentID) {
		AgentID = agentID;
	}
	public String getAgentName() {
		return AgentName;
	}
	public void setAgentName(String agentName) {
		AgentName = agentName;
	}
	public String getAgentTeamID() {
		return AgentTeamID;
	}
	public void setAgentTeamID(String agentTeamID) {
		AgentTeamID = agentTeamID;
	}
	public int getChangeStamp() {
		return ChangeStamp;
	}
	public void setChangeStamp(int changeStamp) {
		ChangeStamp = changeStamp;
	}
	public int getChangeStamp2() {
		return ChangeStamp2;
	}
	public void setChangeStamp2(int changeStamp2) {
		ChangeStamp2 = changeStamp2;
	}
	public int getDefaultSkillGroupID() {
		return DefaultSkillGroupID;
	}
	public void setDefaultSkillGroupID(int defaultSkillGroupID) {
		DefaultSkillGroupID = defaultSkillGroupID;
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

	
}
