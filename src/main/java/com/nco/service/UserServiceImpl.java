package com.nco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import com.nco.dao.UserDAO;
import com.nco.model.UsersAudit;
import com.nco.model.User;
import com.nco.model.UsersAudit;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao;

	@Override
	public void createUser(User p) {
		userDao.createUser(p);
	}

	@Override
	public User readById(String id) {
		return userDao.readById(id);
	}

	@Override
	public WriteResult update(User p) {
		
		return userDao.update(p);
	}

	@Override
	public int deleteById(String id) {
		return userDao.deleteById(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public List<UsersAudit> getActivityLog() {
		return userDao.getActivityLog();
	}

	@Override
	public void createActivityLog(UsersAudit p) {
		userDao.createActivityLog(p);
	}
}
