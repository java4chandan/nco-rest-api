package com.nco.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.nco.model.User;
import com.nco.model.UsersAudit;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
	
	private static final String USER_COLLECTION = "User";
	private static final String AUDIT_COLLECTION = "UsersAudit";
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public void createUser(User p) {
		this.mongoTemplate.insert(p, USER_COLLECTION);
		LOGGER.info("create User entry with information: {}" + p);
	}

	@Override
	public User readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoTemplate.findOne(query, User.class, USER_COLLECTION);
	}

	@Override
	public WriteResult update(User p) {
		this.mongoTemplate.save(p, USER_COLLECTION);
		Query query = new Query(Criteria.where("_id").is(p.getId()));
		//build update
		DBObject dbDoc = new BasicDBObject();
		mongoTemplate.getConverter().write(p, dbDoc);
		Update update = Update.fromDBObject(dbDoc, "_id");
		//run it!
		return mongoTemplate.upsert(query, update, USER_COLLECTION);
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoTemplate.remove(query, USER_COLLECTION);
		return result.getN();
	}

	@Override
	public List<User> getAllUser() {
		return mongoTemplate.findAll(User.class, USER_COLLECTION);
	}

	@Override
	public List<UsersAudit> getActivityLog() {
		return mongoTemplate.findAll(UsersAudit.class, AUDIT_COLLECTION);
	}
	
	@Override
	public void createActivityLog(UsersAudit p) {
		this.mongoTemplate.insert(p, AUDIT_COLLECTION);
	}
}
