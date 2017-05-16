package com.nco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import com.nco.dao.PersonDAO;
import com.nco.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonDAO personDao;

	@Override
	public void create(Person p) {
		personDao.create(p);
	}

	@Override
	public Person readById(String id) {
		return personDao.readById(id);
	}

	@Override
	public WriteResult update(Person p) {
		
		return personDao.update(p);
	}

	@Override
	public int deleteById(String id) {
		return personDao.deleteById(id);
	}

	@Override
	public List<Person> getAllPerson() {
		return personDao.getAllPerson();
	}
}
