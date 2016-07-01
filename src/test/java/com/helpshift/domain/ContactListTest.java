package com.helpshift.domain;

import org.junit.Test;

import java.util.List;

/**
 * @author Kailash Dalmia
 */
public class ContactListTest {
	@Test
	public void add() throws Exception {
		ContactList contactList = new ContactList();
		contactList.add(new Contact("Kailash",""));
		contactList.add(new Contact("Kailash","Dalmia"));
		contactList.add(new Contact("Chris",""));
		contactList.add(new Contact("Chris","Harris"));

		assert(contactList.getFNameIndexedDict().size() == 2);
		assert(contactList.getLNameIndexedDict().size() == 2);
		assert ((List<Contact>)contactList.getFNameIndexedDict().get('k')).size() == 2;
		assert ((List<Contact>)contactList.getFNameIndexedDict().get('c')).size() == 2;
		assert ((List<Contact>)contactList.getLNameIndexedDict().get('h')).size() == 1;
		assert ((List<Contact>)contactList.getLNameIndexedDict().get('d')).size() == 1;
	}

	@Test
	public void searchContacts() throws Exception {
		ContactList contactList = new ContactList();
		contactList.add(new Contact("Kailash",""));
		contactList.add(new Contact("Kailash","Dalmia"));
		contactList.add(new Contact("Chris",""));
		contactList.add(new Contact("Chris","Harris"));

		List<Contact> result = contactList.searchContacts("Ka");
		assert result.size() == 2;
		assert result.get(0).toString().equals("Kailash Dalmia");
		assert result.get(1).toString().equals("Kailash");
		
		result = contactList.searchContacts("Kailash D");
		assert result.size() == 1;
		assert result.get(0).toString().equals("Kailash Dalmia");
	}

}