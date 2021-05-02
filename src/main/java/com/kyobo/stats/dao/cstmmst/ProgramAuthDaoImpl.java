package com.kyobo.stats.dao.cstmmst;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyobo.stats.dao.interfaces.cstmmst.ProgramAuthDao;
import com.kyobo.stats.dao.mapper.cstmmst.ProgramAuthMapper;
import com.kyobo.stats.model.ProgramAuth;
@Repository
public class ProgramAuthDaoImpl implements ProgramAuthDao {

	@Autowired ProgramAuthMapper programAuthMapper;
	@Override
	public ArrayList<ProgramAuth> getProgramAuths(ProgramAuth programAuth) {
		return programAuthMapper.getProgramAuths(programAuth);
	}

	@Override
	public void insertProgramAuth(ProgramAuth programAuth) {
		programAuthMapper.insertProgramAuth(programAuth);
	}

	@Override
	public void deleteProgramAuth(ProgramAuth programAuth) {
		programAuthMapper.deleteProgramAuth(programAuth);
	}

	@Override
	public void insertProgramAuthBatch(ArrayList<ProgramAuth> programAuths) {
		for (ProgramAuth programAuth : programAuths){
			this.programAuthMapper.insertProgramAuth(programAuth);
		}
		//programAuthMapper.insertProgramAuthBatch(programAuths);
	}
}
