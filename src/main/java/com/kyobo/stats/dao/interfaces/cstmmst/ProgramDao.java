package com.kyobo.stats.dao.interfaces.cstmmst;

import java.util.ArrayList;

import com.kyobo.stats.model.Program;

public interface ProgramDao {
	public ArrayList<Program> getPrograms(Program program);
}
