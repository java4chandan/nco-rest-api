package com.nco.service;

import java.util.List;

import com.mongodb.WriteResult;
import com.nco.model.UsersAudit;
import com.nco.model.User;
import com.nco.model.UsersAudit;

public interface UserService {

	public void createUser(User p);

	public User readById(String id);

	public WriteResult update(User p);

	public int deleteById(String id);

	public List<User> getAllUser();

	public List<UsersAudit> getActivityLog();

	public void createActivityLog(UsersAudit p);

}
