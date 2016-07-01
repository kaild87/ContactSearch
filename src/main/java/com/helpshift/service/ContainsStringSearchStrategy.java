package com.helpshift.service;

import com.helpshift.domain.Contact;
import com.helpshift.domain.ContactList;

import java.util.*;

/**
 * @author Kailash Dalmia
 */

public class ContainsStringSearchStrategy implements SearchStrategy {

	ContactList contactList = null;

	List<Contact> result = new ArrayList<Contact>();

	boolean lastNameMatch = false;

	public ContainsStringSearchStrategy(ContactList contactList) {
		this.contactList = contactList;
	}

	@Override
	public List<Contact> search(String searchStr) {
		List<Contact> contacts = contactList.getFNameIndexedDict().get(searchStr.toLowerCase().charAt(0));
		List<Contact> temp = null;
		if(contacts != null){
			temp = new ArrayList<Contact>(contacts);
			Collections.sort(temp, new Sorter());
			search(temp, searchStr);
			temp.clear();
		}

		if(result.isEmpty()){
			lastNameMatch = true;
			contacts = contactList.getLNameIndexedDict().get(searchStr.toLowerCase().charAt(0));
			if(contacts != null) {
				temp = new ArrayList<Contact>(contacts);
				Collections.sort(temp, new Sorter());
				search(temp, searchStr);
				temp.clear();
			}
		}
		Collections.sort(result, new Sorter());
		return result;
	}

	private int match(String name, String searchStr){
		return name.substring(0, Math.min(searchStr.length(), name.length())).compareToIgnoreCase(searchStr);
	}

	private void search(List<Contact> contacts, String searchStr){
		if(contacts.isEmpty()){
			return;
		}
		int length = contacts.size();
		int midIdx = length/2;
		Contact contact = null;		
		if (length == 1) {
			contact = contacts.get(0);
		} else {
			contact = contacts.get(midIdx);
		}
		String name = contact.getName();
		if(lastNameMatch){
			name = contact.getlName();
		}
		if(match(name, searchStr) < 0){
			searchInLeftOfList(contacts.subList(0,midIdx), searchStr, midIdx);
		} else if(match(name, searchStr) > 0) {
			searchInRightOfList(contacts.subList(midIdx, length), searchStr, midIdx);
		} else if(match(name, searchStr) == 0){
			result.add(contact);
			contacts.remove(contact);
			search(contacts, searchStr);
		}

	}

	private void searchInRightOfList(List<Contact> contacts, String searchStr, int midIdx) {		
		while(contacts.size() >= 1){
			search(contacts, searchStr);
		}		
	}

	private void searchInLeftOfList(List<Contact> contacts, String searchStr, int midIdx) {
		while(contacts.size() >= 1){
			search(contacts, searchStr);
		}
	}

	private class Sorter implements Comparator<Contact> {

		@Override
		public int compare(Contact o1, Contact o2) {
			if (o1.getfName().equalsIgnoreCase(o2.getfName())) {
				if (o1.getlName() == null && o2.getlName() != null) {
					return 1;
				} else if (o1.getlName() != null && o2.getlName() == null) {
					return -1;
				} else if (o1.getlName() != null && o2.getlName() != null) {
					return o2.getlName().compareTo(o1.getlName());
				} else {
					return 0;
				}
			} else {
				return o2.getfName().toLowerCase()
						.compareTo(o1.getfName().toLowerCase());
			}
		}

	}

}
