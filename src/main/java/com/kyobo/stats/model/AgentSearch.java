package com.kyobo.stats.model;

public class AgentSearch {
	//기본 프로퍼티
	public String agentID;
	public String agentName;
	public String agentTeamKor;
	public String skillGroupKor;
	
	//쿼리를 위한 프로퍼티
	public String searchGroup;
	public String searchTeam;
	public String orderBy;
	public String groupBy;
	
	public AgentSearch(){
		this.searchGroup = "";
		this.searchTeam = "";
	}

	public String getAgentID() {
		return agentID;
	}

	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentTeamKor() {
		return agentTeamKor;
	}

	public void setAgentTeamKor(String agentTeamKor) {
		this.agentTeamKor = agentTeamKor;
	}

	public String getSkillGroupKor() {
		return skillGroupKor;
	}

	public void setSkillGroupKor(String skillGroupKor) {
		this.skillGroupKor = skillGroupKor;
	}

	public String getSearchGroup() {
		return searchGroup;
	}

	public void setSearchGroup(String searchGroup) {
		this.searchGroup = searchGroup;
	}

	public String getSearchTeam() {
		return searchTeam;
	}

	public void setSearchTeam(String searchTeam) {
		this.searchTeam = searchTeam;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	
	
}
