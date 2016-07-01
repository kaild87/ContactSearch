package com.helpshift.service;

import com.helpshift.domain.Contact;
import com.helpshift.domain.ContactList;

import java.util.*;


public class ExactStringSearchStrategy implements SearchStrategy {
	
	ContactList contactList = null;

	List<Contact> result = new ArrayList<Contact>();

	
	public ExactStringSearchStrategy(ContactList contactList) {
		this.contactList = contactList;
	}

	@Override
	public List<Contact> search(String searchStr) {

		List<Contact> contacts = contactList.getFNameIndexedDict().get(searchStr.toLowerCase().charAt(0));
		if(contacts == null){
			return result;
		}
		List<Contact> temp = new ArrayList<Contact>(contacts);
		Collections.sort(temp, new Sorter());
		search(temp, searchStr);
		Collections.sort(result, new Sorter());
		temp.clear();
		return result;
	}

	private int match(String name, String searchStr){
		return name.compareToIgnoreCase(searchStr);
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
		if(match(contact.getfName(), searchStr) > 0){
			searchInLeftOfList(contacts.subList(0,midIdx), searchStr, midIdx);
		} else if(match(contact.getfName(), searchStr) < 0) {
			searchInRightOfList(contacts.subList(midIdx, length), searchStr, midIdx);
		} else if(match(contact.getfName(), searchStr) == 0){
			result.add(contact);
			contacts.remove(contact);
			search(contacts, searchStr);
		}

	}

	private void searchInRightOfList(List<Contact> contacts, String searchStr, int midIdx) {
		while(contacts.size() > 1){
			search(contacts, searchStr);
		}
	}

	private void searchInLeftOfList(List<Contact> contacts, String searchStr, int midIdx) {
		while(contacts.size() > 1){
			search(contacts, searchStr);
		}
	}
	
	private class Sorter implements Comparator<Contact>{

		@Override
		public int compare(Contact o1, Contact o2) {
			if(o1.getfName().equalsIgnoreCase(o2.getfName())){
				if(o1.getlName() == null && o2.getlName() != null){
					return -1;
				}else if (o1.getlName() != null && o2.getlName() == null){
					return 1;
				}else if (o1.getlName() != null && o2.getlName() != null){
					return o1.getlName().compareTo(o2.getlName());
				}else{
					return 0;
				}
			}else {
				return o1.getfName().toLowerCase().compareTo(o2.getfName().toLowerCase()); 
			}
		}
		
	}

}
