package com.helpshift.main;

import com.helpshift.Utils.ValidationUtils;
import com.helpshift.domain.Contact;
import com.helpshift.domain.ContactList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Kailash Dalmia
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ContactList contactList = new ContactList();
        boolean exit = false;
        do {
			System.out.println("1) Add Contact 2) Search Contact 3) Exit");
			String line = br.readLine();
			switch (Integer.parseInt(line)) {
			case 1:
				System.out.println("Enter Name:");
				line = br.readLine();
				if(!ValidationUtils.validateString(line)){
					System.out.println("Enter Name Of Length 50 Only");
					break;
				}
				String[] strings = line.split(" ");
				if(strings.length == 1){
					contactList.add(new Contact(strings[0], ""));
				} else if(strings.length == 2){
					contactList.add(new Contact(strings[0], strings[1]));
				}
				break;
			case 2:
				System.out.println("Enter Name :");
				line = br.readLine();
				List<Contact> result = contactList.searchContacts(line);
				for (Contact contact : result) {
					System.out.println(contact);
				}
				break;
			case 3:
				System.out.println("Happy Searching");
				exit = true;
				break;
			default:
				break;
			}
		} while (!exit);
	}

}
