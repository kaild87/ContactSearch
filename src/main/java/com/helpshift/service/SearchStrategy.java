package com.helpshift.service;

import com.helpshift.domain.Contact;

import java.util.List;

/**
 * @author Kailash Dalmia
 */

public interface SearchStrategy {

	public List<Contact> search(String searchStr);
	
}
