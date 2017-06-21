package com.nco.util;

import java.util.Date;

import com.nco.model.UsersAudit;

public class UsersAuditUtil {
	public static UsersAudit getUsersAudit(String activity) {
		UsersAudit userAudit = new UsersAudit();
		userAudit.setUserId("1111");
		userAudit.setLicense("license_1111");
		userAudit.setUserName("Chandan");
		userAudit.setTimestamp(new Date());
		userAudit.setActivityType(activity);
		return userAudit;
	}
}
