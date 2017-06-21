package com.nco.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.WriteResult;
import com.nco.model.User;
import com.nco.model.UsersAudit;
import com.nco.service.UserService;
import com.nco.util.UsersAuditUtil;

@RestController
@CrossOrigin
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@RequestMapping(value = { "/", "/user" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<User> getAllUser() {
		return userService.getAllUser();
	}

	@RequestMapping(value = { "/user" }, method = RequestMethod.PUT, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	void createUser(@RequestBody @Valid User user) {
		LOGGER.info("Creating a new todo entry with information: {}", user);
		userService.createUser(user);
		userService.createActivityLog(UsersAuditUtil.getUsersAudit("Create Profile"));
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody User findById(@PathVariable String id) {
		return userService.readById(id);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST, produces = "application/json")
	public String updateUser(@RequestBody User user) {
		LOGGER.info("Updating User entry with information: {}", user);
		WriteResult updated = userService.update(user);
		userService.createActivityLog(UsersAuditUtil.getUsersAudit("Update Profile"));
		return updated.getN() == 1 ? "Updated successfully" : "Update failed";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable String id) {
		LOGGER.info("Deleting User entry with information: {}", id);
 		int delete = userService.deleteById(id);
		userService.createActivityLog(UsersAuditUtil.getUsersAudit("Delete Profile"));
		return delete == 1 ? "delete successfully" : "delete failed";
	}

	@RequestMapping(value = { "/logs" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<UsersAudit> getActivityLog() {
		return userService.getActivityLog();
	}

	@RequestMapping(value = { "/createlog" }, method = RequestMethod.PUT, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	void createActivityLog(@RequestBody UsersAudit userAudit) {
		/*
		 * UsersAudit userAudit=new UsersAudit(); userAudit.setUserId("1111");
		 * userAudit.setLicense("license_1111");
		 * userAudit.setUserName("chandan");
		 */
		userAudit.setTimestamp(new Date());
		/* userAudit.setActivityType("update profile"); */
		userService.createActivityLog(userAudit);
	}

	

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleUserNotFound(Exception ex) {
		LOGGER.error("Handling error with message: {}", ex.getMessage());
		return "System Error ccurred";
	}

}
