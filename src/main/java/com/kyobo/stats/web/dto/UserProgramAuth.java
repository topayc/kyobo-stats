package com.kyobo.stats.web.dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.kyobo.stats.model.Program;

public class UserProgramAuth {
	public String userId;
	public String userName;
	public HashMap<String, String> userProgramAuthMap;
	
	public UserProgramAuth(String userId, String userName, ArrayList<Program> programs){
		this.userId = userId;
		this.userName = userName;
		this.userProgramAuthMap = new HashMap<String, String>();
		this.initMap(programs);
	}
	private void initMap(ArrayList<Program> programs) {
		for (Program program : programs){
			this.userProgramAuthMap.put(program.getPgmID(),"N");
		}
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public HashMap<String, String> getUserProgramAuthMap() {
		return userProgramAuthMap;
	}
	public void setUserProgramAuthMap(HashMap<String, String> userProgramAuthMap) {
		this.userProgramAuthMap = userProgramAuthMap;
	}
}
