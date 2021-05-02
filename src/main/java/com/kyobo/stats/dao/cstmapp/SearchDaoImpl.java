package com.kyobo.stats.dao.cstmapp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyobo.stats.dao.interfaces.cstmapp.SearchDao;
import com.kyobo.stats.dao.mapper.cstmapp.SearchMapper;
import com.kyobo.stats.model.AgentSearch;
import com.kyobo.stats.web.dto.CodeNamePair;

@Repository
public class SearchDaoImpl implements SearchDao {
	
	@Autowired private SearchMapper searchMapper;
	
	@Override
	public ArrayList<AgentSearch> getAgentSearch(AgentSearch agentSearch) {
		return this.searchMapper.getAgentSearch(agentSearch);
	}

	@Override
	public ArrayList<CodeNamePair> getListByCode(String code) {
		return this.searchMapper.getListByCode(code);
	}
}
