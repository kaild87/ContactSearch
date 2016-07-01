package com.helpshift.domain;

/**
 * @author Kailash Dalmia
 */
public class Contact implements Comparable<Contact>{

	private String fName;
	
	private String lName = "";
	
	public Contact(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	@Override
	public String toString() {
		return fName + (lName.equals("") ? "" : " "+lName);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null)                return false;
	    if(!(o instanceof Contact)) return false;

	    Contact other = (Contact) o;
	    if(! this.fName.equals(other.fName)) return false;
	    if(this.lName != null && ! this.lName.equals(other.lName))   return false;

	    return true;
	}
	
	@Override
	public int hashCode() {
		return (fName.hashCode() * (lName != null ? lName.hashCode() : 0));
	}

	@Override
	public int compareTo(Contact o) {
		int result = o.getfName().compareTo(this.getfName());
		result += o.getlName().compareTo(this.getlName());
		return result;
	}

	public String getName(){
		return fName+" "+lName;
	}
}
