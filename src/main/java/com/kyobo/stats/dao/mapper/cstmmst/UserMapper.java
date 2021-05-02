package com.kyobo.stats.dao.mapper.cstmmst;

import java.util.ArrayList;

import com.kyobo.stats.model.User;

public interface UserMapper {
	public ArrayList<User> getUsers(User user);
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
}
