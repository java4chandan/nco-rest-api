package com.nco.dao;

import java.util.List;

import com.mongodb.WriteResult;
import com.nco.model.Person;

public interface PersonDAO {
	public void create(Person p);

	public Person readById(String id);

	public WriteResult update(Person p);

	public int deleteById(String id);

	List<Person> getAllPerson();
}
