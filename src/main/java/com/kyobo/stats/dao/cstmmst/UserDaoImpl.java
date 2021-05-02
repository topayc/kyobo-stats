package com.kyobo.stats.dao.cstmmst;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyobo.stats.dao.interfaces.cstmmst.UserDao;
import com.kyobo.stats.dao.mapper.cstmmst.UserMapper;
import com.kyobo.stats.model.User;
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired 
	private UserMapper userMapper;
	
	@Override
	public ArrayList<User> getUsers(User user) {
		return userMapper.getUsers(user);
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}
}
