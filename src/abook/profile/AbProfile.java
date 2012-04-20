package abook.profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Main profile class.
 * 
 * @author jurij
 *
 */
public class AbProfile {
	
	protected static int counter = 1;
	protected int id;
	protected String user;
	protected List<AbPerson> listOfAbPersons;
	protected List<AbCard> listOfAbCards;
	protected int openedTab;
	protected String searchText;
	protected List<AbGroup> listOfGroups;
	protected List<String> listOfSelectedGroups;
	
	/**
	 * Creates new profile.
	 */
	public AbProfile(String user) {
		
		this.id = counter;
		this.user = user;
		this.listOfAbPersons = new ArrayList<AbPerson>();
		this.listOfAbCards = new ArrayList<AbCard>();
		//this.listOfGroups = new ArrayList<String>();
		this.listOfSelectedGroups = new ArrayList<String>();
		this.openedTab = 0;
		this.listOfGroups = new ArrayList<AbGroup>();
		this.searchText = new String();
	}

	/**
	 * Returns user name.
	 * 
	 * @return user
	 */
	public String getUserName() {
		return user;
	}

	/**
	 * Set user name.
	 * 
	 * @param user
	 */
	public void setUserName(String user) {
		this.user = user;
		InitProfile.setSaved(false);
	}
	
	/**
	 * Adds new contact.
	 * 
	 * @param person
	 */
	public void addPerson(AbPerson person) {
		listOfAbPersons.add(person);
		InitProfile.setSaved(false);
	}
	
	/**
	 * Returns contact with selected name and surname.
	 * 
	 * @param name
	 * @param surname
	 * @return person
	 */
	public AbPerson addPerson(String name, String surname) {
		
		AbPerson person = new AbPerson(name, surname);
		listOfAbPersons.remove(person);
		InitProfile.setSaved(false);
		
		return person;
	}
	
	/**
	 * Removes person from contact list.
	 * 
	 * @param person
	 */
	public void removePerson(AbPerson person) {
		listOfAbPersons.remove(person);
		InitProfile.setSaved(false);
	}
	
	/**
	 * Adds new special card.
	 * 
	 * @param type
	 * @return cpecialCard
	 */
	public AbCard addSpecialCard(int type) {
		
		AbCard specialCard = (AbCard) new AbCard(type);
		listOfAbCards.add(specialCard);
		InitProfile.setSaved(false);
		
		return (AbCard) specialCard;	
	}
	
	/**
	 * Returns list of cards (tabs).
	 * 
	 * @return listOfAbCars
	 */
	public List<AbCard> getListOfAbCards() {
		return listOfAbCards;
	}
	
	/**
	 * Returns actual index of card with selected type.
	 * 
	 * @param type
	 * @return index
	 */
	public int getIndexOfCard(int type) {
		
		int index = 0;
		for(AbCard card : listOfAbCards) {
			
			if(card.getType() == type) {
				return index;
			}
			
			index++;
		}
		
		return -1;
	}
	
	/**
	 * Removes card (tab) on selected index.
	 * 
	 * @param index
	 * @return AbCard
	 */
	public AbCard removeAbICard(int index) {
		
		InitProfile.setSaved(false);
		
		return (listOfAbCards.remove(index));
		
	}
	
	/**
	 * Removes last card (tab).
	 * 
	 * @return AbCard
	 */
    public AbCard removeAbICard() {
    	
    	InitProfile.setSaved(false);
		
		return removeAbICard(listOfAbCards.size());
		
	}
    
    /**
     * Returns list of all contacts.
     * 
     * @return listOfAbPersons
     */
    public List<AbPerson> getListOfAbPersons() {
    	return listOfAbPersons;
    }
    
    /**
     * Returns index of tab which is actually shown.
     * 
     * @return openedTab
     */
	public int getOpenedTab() {
    	return openedTab;
    }

	/**
	 * Actualizes index of tab which is actually shown.
	 * 
	 * @param openedTab
	 */
	public void setOpenedTab(int openedTab) {
    	this.openedTab = openedTab;
    }
	
    /**
     * Method adds new group.
     * 
     * @param newGroup
     */
    public void addGroup(String newGroup) {
    	
    	// check if group with selected name exists //
    	for(AbGroup gr : listOfGroups) {
    		if(gr.getGroupName().equals(newGroup)) return;
    	}
    	
    	// create new group and add to list //
    	listOfGroups.add(new AbGroup (newGroup));
    	
    	InitProfile.setSaved(false);
    }
    
    /**
     * Method removes group with selected name.
     * 
     * @param GroupName
     */
    public void removeGroup(String groupName) {
    	
    	// find group with selected name and remove it. //
    	for(AbGroup gr : listOfGroups) {
    		if(gr.getGroupName().equals(groupName)) {
    			listOfGroups.remove(gr);
    			return;
    		}
    	}
    	
    	// update all persons //
    	for(AbPerson person : listOfAbPersons) {
    		person.removeGroup(groupName);
    	}
    	
    	InitProfile.setSaved(false);
    }
    
    /**
     * Method changes name of group. 
     * 
     * @param oldName
     * @param newName
     * @return nameChanged
     */
    public boolean updateGroupName(String oldName, String newName) {
    	
    	// if selected names empty, return //
    	if(newName != "" && oldName != "") {
    		// find group with old name //
	    	for(AbGroup gr : listOfGroups) {
	    		// and rename it //
	    		if(gr.getGroupName().equals(oldName)) {
	    			gr.setGroupName(newName);
	    			return true;
	    		}
	    	}
    	}
    	
    	InitProfile.setSaved(false);
    	
    	return false;
    }    
    
    /**
     * Method returns list of groups.
     * 
     * @return listOfGroups
     */
    public List<AbGroup> getListOfGroups() {
    	return listOfGroups;
    }
    
    /**
     * Method returns list of group names.
     * 
     * @return listOfGroupsName
     */
    public List<String> getListOfGroupNames()
    {
    	List<String> ret = new ArrayList<String>();

    	for(AbGroup gr : listOfGroups) {
    		ret.add(gr.getGroupName());
    	}
    	return ret;
    }
    
    /**
     * Method adds group to list of selected groups.
     * 
     * @param newGroup
     */
    public void addSelectedGroup(String newGroup) {
    	
    	for(String group : listOfSelectedGroups) {
    		if(newGroup.equals(group)) return;
    	}
    	
    	listOfSelectedGroups.add(newGroup);
    	
    	InitProfile.setSaved(false);
    }
    
    /**
     * Removes group with selected name.
     * 
     * @param oldGroup
     */
    public void removeSelectedGroup(String oldGroup) {
    	
    	for(String group : listOfSelectedGroups) {
    		if(oldGroup.equals(group)) listOfSelectedGroups.remove(group);
    	}
    	
    	InitProfile.setSaved(false);
    	
    	return;
    }

    /**
     * Method returns list of selected groups.
     * 
     * @return listOfSelectedGroups
     */
    public List<String> getListOfSelectedGroups()
    {
    	return listOfSelectedGroups;
    }
    
    /**
     * Method returns boolean value if selected groups exists.
     * 
     * @return containsGroup
     */
    public boolean containsGroup(String group)
    {
    	// check if group with selected name exists //
    	for(AbGroup gr : listOfGroups) {
    		if(gr.getGroupName().equals(group)) return true;
    	}
    	
    	return false;
    }
    
    /**
     * Method returns boolean value if selected groups exists.
     * 
     * @return containsGroup
     */
    public boolean containsSelectedGroup(String group)
    {
    	// check if group with selected name exists //
    	
    	return listOfSelectedGroups.contains(group);
    }

    /**
     * Method returns actual searching pattern.
     * 
     * @return searchText
     */
	public String getSearchText() {
    	return searchText;
    }

	/**
	 * Method actualizes searching pattern.
	 * 
	 * @param searchText
	 */
	public void setSearchText(String searchText) {
    	this.searchText = searchText;
    	
    	InitProfile.setSaved(false);
    }
    
	/**
     * 
     * @param newGroup
     */
    /*public void addGroup(String newGroup) {
    	
    	for(String group : listOfGroups) {
    		if(newGroup.equals(group)) return;
    	}
    	
    	listOfGroups.add(newGroup);
    }*/
    
    /**
     * 
     * @param newGroup
     */
    /*public void removeGroup(String newGroup) {
    	
    	for(String group : listOfGroups) {
    		if(newGroup.equals(group)) {
    			listOfGroups.remove(group);
    			return;
    		}
    	}
    } */  
    
    /**
     * 
     * @return
     */
    /*public List<String> getListOfGroups() {
    	return listOfGroups;
    }*/
    
    /**
     * 
     * @return
     */
    /*public List<Integer> getListOfSelectedGroups() {
    	return listOfSelectedGroups;
    }*/

    
}
