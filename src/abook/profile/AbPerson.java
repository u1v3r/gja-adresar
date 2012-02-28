package abook.profile;

import java.util.HashSet;
import java.util.Set;

public class AbPerson {
	
	protected static int counter = 1;
	protected int id;
	protected String name;
	protected String surname;
	protected Set<String> groupSet;
	protected String city;
	
	public AbPerson(String name, String surname) {
		this.id = counter;
		this.name = name;
		this.name = surname;
		this.groupSet = new HashSet<String>();
		
		counter++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public void addGroup(String group) {
		this.groupSet.add(group);
	}
	
	public void removeGroup(String group) {
		this.groupSet.remove(group);
	}
	
	public Set<String> getGroup() {
		return this.groupSet;
	}
}
