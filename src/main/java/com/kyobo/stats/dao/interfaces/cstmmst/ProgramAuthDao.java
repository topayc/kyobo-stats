package com.kyobo.stats.dao.interfaces.cstmmst;

import java.util.ArrayList;

import com.kyobo.stats.model.ProgramAuth;

public interface ProgramAuthDao {
	public ArrayList<ProgramAuth> getProgramAuths(ProgramAuth programAuth);
	public void insertProgramAuth(ProgramAuth programAuth); 
	public void insertProgramAuthBatch(ArrayList<ProgramAuth> programAuths); 
	public void deleteProgramAuth(ProgramAuth programAuth); 
}
