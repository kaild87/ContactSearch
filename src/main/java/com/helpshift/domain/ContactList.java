package com.helpshift.domain;

import com.helpshift.service.ContainsStringSearchStrategy;
import com.helpshift.service.ExactStringSearchStrategy;
import com.helpshift.service.SearchStrategy;

import java.util.*;

public class ContactList {

	private Dictionary<Character,Contacts> fNameIndexedDict = new Hashtable<Character, Contacts>();
	private Dictionary<Character,Contacts> lNameIndexedDict = new Hashtable<Character, Contacts>();

	public void add(Contact contact) {
		char firstChar = contact.getfName().toLowerCase().charAt(0);
		Contacts contacts = new Contacts();
		if(fNameIndexedDict.get(firstChar) != null){
			contacts = fNameIndexedDict.get(firstChar);
		}
		contacts.add(contact);
		fNameIndexedDict.put(firstChar, contacts);
		if(!contact.getlName().isEmpty()){
			firstChar = contact.getlName().toLowerCase().charAt(0);
			contacts = new Contacts();
			if(lNameIndexedDict.get(firstChar) != null){
				contacts = lNameIndexedDict.get(firstChar);
			}
			contacts.add(contact);
			lNameIndexedDict.put(firstChar, contacts);
		}
	}

	public Dictionary<Character, Contacts> getFNameIndexedDict() {
		return fNameIndexedDict;
	}

	public Dictionary<Character, Contacts> getLNameIndexedDict() {
		return lNameIndexedDict;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1404520684268363023L;

	public List<Contact> searchContacts(String searchStr) {
		LinkedList<SearchStrategy> strategies = new LinkedList<SearchStrategy>(
				Arrays.asList(new ExactStringSearchStrategy(this),
						new ContainsStringSearchStrategy(this)));
		List<Contact> result = new ArrayList<Contact>();
		for(SearchStrategy searchStrategy : strategies){
			List<Contact> temp = searchStrategy.search(searchStr);
			if(temp.size() > 0){
				result.addAll(temp);
				break;
			}
		}
		return result;
	}


	private class Contacts extends ArrayList<Contact>{

		@Override
		public boolean add(Contact contact) {
			return contains(contact) ? false : super.add(contact);
		}
	}

}



