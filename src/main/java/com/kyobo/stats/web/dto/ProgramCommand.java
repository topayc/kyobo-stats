package com.kyobo.stats.web.dto;

import java.util.ArrayList;

public class ProgramCommand {
	public String primaryProgramName;
	public String primaryProgramID;
	public ArrayList<ProgramDTO> programs;
	
	public ProgramCommand(){
		this.programs = new ArrayList<ProgramDTO>();
	}

	public String getPrimaryProgramName() {
		return primaryProgramName;
	}

	public void setPrimaryProgramName(String primaryProgramName) {
		this.primaryProgramName = primaryProgramName;
	}

	public String getPrimaryProgramID() {
		return primaryProgramID;
	}

	public void setPrimaryProgramID(String primaryProgramID) {
		this.primaryProgramID = primaryProgramID;
	}

	public ArrayList<ProgramDTO> getPrograms() {
		return programs;
	}

	public void setPrograms(ArrayList<ProgramDTO> programs) {
		this.programs = programs;
	}
}
