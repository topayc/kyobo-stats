package com.kyobo.stats.web.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kyobo.stats.common.Constants;
import com.kyobo.stats.dao.interfaces.cstmapp.SearchDao;
import com.kyobo.stats.dao.interfaces.cstmmst.ProgramAuthDao;
import com.kyobo.stats.dao.interfaces.cstmmst.ProgramDao;
import com.kyobo.stats.dao.interfaces.cstmmst.UserDao;
import com.kyobo.stats.model.AgentSearch;
import com.kyobo.stats.model.Program;
import com.kyobo.stats.model.ProgramAuth;
import com.kyobo.stats.model.User;
import com.kyobo.stats.web.dto.CodeNamePair;
import com.kyobo.stats.web.dto.ProgramCommand;
import com.kyobo.stats.web.dto.ProgramDTO;
import com.kyobo.stats.web.dto.UserProgramAuth;
import com.kyobo.stats.web.dto.UserSession;

@Controller
@RequestMapping
public class MainController extends ApplicationController{
	
	@Autowired UserDao userDao;
	@Autowired ProgramDao programDao;
	@Autowired ProgramAuthDao programAuthDao;
	@Autowired SearchDao searchDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale,  
			@RequestParam(value = "requestCode", required = true, defaultValue = "intro") String requestCode,
			HttpServletRequest request, HttpServletResponse reposne,Model model) {
		UserSession userSession =this.prepareUserSession();
		ProgramAuth pAuthOption  = new ProgramAuth();
		pAuthOption.setUserID(userSession.getId());
		
		this.buildMenuAuth(model, userSession);
		
		model.addAttribute("userSession", userSession);
		model.addAttribute("requestCode",requestCode);
		return Forward.main_view;
	}
	
	//사용자별 메뉴 권한 정보를 생성
	public void buildMenuAuth(Model model, UserSession userSession) {
		Program pOption = new Program();
		pOption.setUseYN("Y");
		ArrayList<Program> programs = this.programDao.getPrograms(pOption);
		
		if (!userSession.getType().equals(Constants.AccoutType.SUPER)){
			ProgramAuth pao  = new ProgramAuth();
			pao.setUserID(userSession.getId());
			ArrayList<ProgramAuth> programAuths = this.programAuthDao.getProgramAuths(pao);
			ArrayList<Program> userPrograms = new ArrayList<Program>();
			
			for (int i = 0; i < programs.size() ; i++) {
				Program p = programs.get(i);
				if (!p.getPgmID().startsWith("RD") && (!p.getPgmID().startsWith("MN"))) {
					userPrograms.add(p);
					continue;
				}
				
				for (int j = 0; j < programAuths.size(); j++){
					if (programs.get(i).getPgmID().equals(programAuths.get(j).getPgmID())){
						userPrograms.add(p);
						break;
					}
				}
			}
			programs = userPrograms;
		}
		
		ArrayList<ProgramCommand> programCommandList = new ArrayList<ProgramCommand>();
		for (Program program : programs) {
			if (!program.getPgmID().startsWith("RD") && !program.getPgmID().startsWith("MN")) {
				ProgramCommand p = new ProgramCommand();
				p.setPrimaryProgramName(program.getPgmNM());
				p.setPrimaryProgramID(program.getPgmID());
				
				for (Program pc : programs) {
					if (p.getPrimaryProgramID().equals(pc.getParentPgmID())){
						ProgramDTO pDTO = new ProgramDTO();
						pDTO.setProgramId(pc.getPgmID());
						pDTO.setProgramName(pc.getPgmNM());
						pDTO.setUseYN(pc.getUseYN());
						p.getPrograms().add(pDTO);
					}
				}
				programCommandList.add(p);
			}
		}
	
		model.addAttribute("programCommandList", programCommandList);
	}
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(
			Locale locale, 
			@RequestParam(value = "requestCode", required = true, defaultValue = "intro") String requestCode, 
			Model model) {
		
		ArrayList<CodeNamePair> groupList = this.searchDao.getListByCode(Constants.ListCode.SKILL_GROUP);
		ArrayList<CodeNamePair> teamList = this.searchDao.getListByCode(Constants.ListCode.TEAM);
		ArrayList<CodeNamePair> branchList = this.searchDao.getListByCode(Constants.ListCode.BRANCH);
		ArrayList<CodeNamePair> callTreatList = this.searchDao.getListByCode(Constants.ListCode.CALL_TREAT);
		ArrayList<AgentSearch> agentSearchList = this.searchDao.getAgentSearch(new AgentSearch());
		
		model.addAttribute("groupList",groupList);
		model.addAttribute("teamList",teamList);
		model.addAttribute("branchList",branchList);
		model.addAttribute("agentSearchList",agentSearchList );
		model.addAttribute("callTreatList",callTreatList);
		model.addAttribute("requestCode",requestCode);
		
		switch(requestCode) {
		/* 조직관리*/
		case "MN210":  
			break;
		
		/* 계정 관리*/
		case "MN310":  
			break;
		
		/* 사용자별 메뉴 권한 관리*/
		case "MN410":  
			this.buildRollCommand(model, null);
			break;
		}
		return Forward.content_view;
	}
	
	public void buildRollCommand (Model model, String userId) {
		Program pOption = new Program();
		pOption.setUseYN("Y");
		ArrayList<Program> programs = this.programDao.getPrograms(pOption);
		ArrayList<User> users = this.userDao.getUsers(new User());
		ArrayList<ProgramAuth> programAuths = this.programAuthDao.getProgramAuths(new ProgramAuth());
		ArrayList<UserProgramAuth> userProgramAuthList = new ArrayList<UserProgramAuth>();   
		
		for (User user : users) {
			UserProgramAuth upa = new UserProgramAuth(user.getUserID(), user.getUserNM(),programs);
			for (ProgramAuth programAuth : programAuths ){
				if (programAuth.getUserID().equals(user.getUserID())) {
					upa.getUserProgramAuthMap().put(programAuth.getPgmID(), "Y");
				}
			}
			userProgramAuthList.add(upa); 
		}
		
		ArrayList<ProgramCommand> programCommandList = new ArrayList<ProgramCommand>();
		for (Program program : programs) {
			if (!program.getPgmID().startsWith("RD") && !program.getPgmID().startsWith("MN")) {
				ProgramCommand p = new ProgramCommand();
				p.setPrimaryProgramName(program.getPgmNM());
				p.setPrimaryProgramID(program.getPgmID());
				
				for (Program pc : programs) {
					if (p.getPrimaryProgramID().equals(pc.getParentPgmID())){
						ProgramDTO pDTO = new ProgramDTO();
						pDTO.setProgramId(pc.getPgmID());
						pDTO.setProgramName(pc.getPgmNM());
						pDTO.setUseYN(pc.getUseYN());
						p.getPrograms().add(pDTO);
					}
				}
				programCommandList.add(p);
			}
		}
		
		model.addAttribute("programCommandList", programCommandList);
		model.addAttribute("userProgramAuthList", userProgramAuthList);
		model.addAttribute("programs", programs);
	}
	
	@RequestMapping(value = "/getAgents", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<AgentSearch> getAgents(
			@RequestParam(value = "groupID", required = true, defaultValue = "") String groupID,
			@RequestParam(value = "teamID", required = true, defaultValue = "") String teamID,
			HttpServletRequest request, HttpServletResponse reposne,Model model) {
		AgentSearch agentSearch = new AgentSearch();
		agentSearch.setSearchGroup(groupID);
		agentSearch.setSearchTeam(teamID);
		ArrayList<AgentSearch> agentList = this.searchDao.getAgentSearch(agentSearch);
		return agentList;
	}
}
