package com.kyobo.stats.dao.cstmmst;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyobo.stats.dao.interfaces.cstmmst.ProgramDao;
import com.kyobo.stats.dao.mapper.cstmmst.ProgramMapper;
import com.kyobo.stats.model.Program;
@Repository
public class ProgramDaoImpl implements ProgramDao {
	
	@Autowired ProgramMapper programMapper;
	@Override
	public ArrayList<Program> getPrograms(Program program) {
		// TODO Auto-generated method stub
		return this.programMapper.getPrograms(program);
	}

}
