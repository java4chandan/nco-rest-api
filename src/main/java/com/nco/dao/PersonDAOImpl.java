package com.nco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.nco.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {
	@Autowired
	MongoTemplate mongoTemplate;
	private static final String PERSON_COLLECTION = "Person";

	@Override
	public void create(Person p) {
		this.mongoTemplate.insert(p, PERSON_COLLECTION);
	}

	@Override
	public Person readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoTemplate.findOne(query, Person.class, PERSON_COLLECTION);
	}

	@Override
	public WriteResult update(Person p) {
		this.mongoTemplate.save(p, PERSON_COLLECTION);
		Query query = new Query(Criteria.where("_id").is(p.getId()));
		//build update
		DBObject dbDoc = new BasicDBObject();
		mongoTemplate.getConverter().write(p, dbDoc);
		Update update = Update.fromDBObject(dbDoc, "_id");
		//run it!
		return mongoTemplate.upsert(query, update, PERSON_COLLECTION);
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoTemplate.remove(query, PERSON_COLLECTION);
		return result.getN();
	}

	@Override
	public List<Person> getAllPerson() {
		return mongoTemplate.findAll(Person.class, PERSON_COLLECTION);
	}
}
