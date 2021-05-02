package com.kyobo.stats.model;

import java.util.Date;

public class SkillGroupMember {
	private String AgentID;
	private int SkillGroupID;
	private int ChangeStamp;
	private Date DtRgst;
	public String getAgentID() {
		return AgentID;
	}
	public void setAgentID(String agentID) {
		AgentID = agentID;
	}
	public int getSkillGroupID() {
		return SkillGroupID;
	}
	public void setSkillGroupID(int skillGroupID) {
		SkillGroupID = skillGroupID;
	}
	public int getChangeStamp() {
		return ChangeStamp;
	}
	public void setChangeStamp(int changeStamp) {
		ChangeStamp = changeStamp;
	}
	public Date getDtRgst() {
		return DtRgst;
	}
	public void setDtRgst(Date dtRgst) {
		DtRgst = dtRgst;
	}
	
	
}
