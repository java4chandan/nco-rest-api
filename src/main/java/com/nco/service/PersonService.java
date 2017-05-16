package com.nco.service;

import java.util.List;

import com.mongodb.WriteResult;
import com.nco.model.Person;

public interface PersonService {

	public void create(Person p);

	public Person readById(String id);

	public WriteResult update(Person p);

	public int deleteById(String id);

	List<Person> getAllPerson();
}
