package com.nco.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.nco.model.Person;
import com.nco.service.PersonService;
import com.nco.util.PersonNotFoundException;

@RestController
@CrossOrigin
public class Personcontroller {

	private static final Logger LOGGER = LoggerFactory.getLogger(Personcontroller.class);

	@Autowired
	PersonService personService;

	@RequestMapping(value = { "/", "/person" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Person> getAllPerson(HttpServletResponse response) {
		return personService.getAllPerson();
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Person findById(@PathVariable String id) {
		return personService.readById(id);
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String updatePerson(@RequestBody Person perrson) {
		LOGGER.info("Updating Person entry with information: {}", perrson);
		WriteResult updated = personService.update(perrson);
		return updated.getN() == 1 ? "Updated successfully !!" : "Update failed!!";
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody String deletePerson(@PathVariable String id) {
		LOGGER.info("Deleting Person entry with information: {}", id);
		int delete = personService.deleteById(id);
		return delete == 1 ? "delete successfully !!" : "delete failed!!";
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handlePersonNotFound(Exception ex) {
		LOGGER.error("Handling error with message: {}", ex.getMessage());
		 return "System Error Occurred ";
	}

	/*@ExceptionHandler(Exception.class)
	@ResponseBody
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String  handlePersonNotFound(Exception ex) {
		LOGGER.error("Handling error with message: {}", ex.getMessage());
		 return "System Error occurred "+ex.getMessage();
	}*/
}
