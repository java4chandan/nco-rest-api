package com.nco.util;

/**
 * This exception is thrown when the requested todo entry is not found.
 * 
 * @author Petri Kainulainen
 */
public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(String id) {
		super(String.format("No Person entry found with id: <%s>", id));
	}
}
