package abook.profile;

import java.util.ArrayList;
import java.util.List;

public class AbPerson {
	
	protected static int counter = 1;
	protected int id;
	protected String name;
	protected String surname;
	protected List<Integer> listOfGroupIndex;
	protected String city;
	
	public AbPerson(String name, String surname) {
		this.id = counter;
		this.name = name;
		this.name = surname;
		this.listOfGroupIndex = new ArrayList<Integer>();
		
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
	
	public void addGroup(Integer group) {
		this.listOfGroupIndex.add(group);
	}
	
	public void removeGroup(String group) {
		this.listOfGroupIndex.remove(group);
	}
	
	public List<Integer> getGroup() {
		return this.listOfGroupIndex;
	}
}
