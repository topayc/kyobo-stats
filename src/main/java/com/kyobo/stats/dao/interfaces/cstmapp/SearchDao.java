package com.kyobo.stats.dao.interfaces.cstmapp;

import java.util.ArrayList;

import com.kyobo.stats.model.AgentSearch;
import com.kyobo.stats.web.dto.CodeNamePair;

public interface SearchDao {
	public ArrayList<AgentSearch> getAgentSearch(AgentSearch agentSearch);
	public ArrayList<CodeNamePair> getListByCode(String code);
}
