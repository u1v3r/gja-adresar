package abook.profile;

import java.util.ArrayList;
import java.util.List;


public class AbProfile {
	
	protected static int counter = 1;
	protected int id;
	protected String user;
	protected List<AbPerson> listOfAbPersons;
	protected List<AbCard> listOfAbCards;
	protected List<String> listOfGroups;
	protected List<Integer> listOfSelectedGroups;
	protected int openedTab;
	
	protected List<AbGroup> n_listOfGroups;
	
	/**
	 * Creates new profile
	 */
	public AbProfile(String user) {
		
		this.id = counter;
		this.user = user;
		this.listOfAbPersons = new ArrayList<AbPerson>();
		this.listOfAbCards = new ArrayList<AbCard>();
		this.listOfGroups = new ArrayList<String>();
		this.listOfSelectedGroups = new ArrayList<Integer>();
		this.openedTab = 0;
		
		this.n_listOfGroups = new ArrayList<AbGroup>();
	}

	/**
	 * 
	 * @return
	 */
	public String getUserName() {
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public void setUserName(String user) {
		this.user = user;
	}
	
	/**
	 * 
	 * @param person
	 */
	public void addPerson(AbPerson person) {
		listOfAbPersons.add(person);
	}
	
	/**
	 * 
	 * @param name
	 * @param surname
	 * @return
	 */
	public AbPerson addPerson(String name, String surname) {
		
		AbPerson person = new AbPerson(name, surname);
		listOfAbPersons.remove(person);
		
		return person;
	}
	
	/**
	 * 
	 * @param person
	 */
	public void getPerson(AbPerson person) {
		listOfAbPersons.remove(person);
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public AbCard addSpecialCard(int type) {
		
		AbCard specialCard = (AbCard) new AbCard(type);
		listOfAbCards.add(specialCard);
		
		return (AbCard) specialCard;	
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AbCard> getListOfAbCards() {
		return listOfAbCards;
	}
	
	/**
	 * 
	 * @param type
	 * @return
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
	 * 
	 * @param index
	 * @return
	 */
	public AbCard removeAbICard(int index) {
		
		return (listOfAbCards.remove(index));
		
	}
	
	/**
	 * 
	 * @return
	 */
    public AbCard removeAbICard() {
		
		return removeAbICard(listOfAbCards.size());
		
	}
    
    /**
     * 
     * @return
     */
    public List<AbPerson> getListOfAbPersons() {
    	return listOfAbPersons;
    }
    
    /**
     * 
     * @param newGroup
     */
    public void addGroup(String newGroup) {
    	
    	for(String group : listOfGroups) {
    		if(newGroup.equals(group)) return;
    	}
    	
    	listOfGroups.add(newGroup);
    }
    
    /**
     * 
     * @param newGroup
     */
    public void removeGroup(String newGroup) {
    	
    	for(String group : listOfGroups) {
    		if(newGroup.equals(group)) {
    			listOfGroups.remove(group);
    			return;
    		}
    	}
    }   
    
    /**
     * 
     * @return
     */
    public List<String> getListOfGroups() {
    	return listOfGroups;
    }
    
    /**
     * 
     * @return
     */
    public List<Integer> getListOfSelectedGroups() {
    	return listOfSelectedGroups;
    }

    /**
     * 
     * @return
     */
	public int getOpenedTab() {
    	return openedTab;
    }

	/**
	 * 
	 * @param openedTab
	 */
	public void setOpenedTab(int openedTab) {
    	this.openedTab = openedTab;
    }
	
    /// NEW GROUPS ///
    public void n_addGroup(String newGroup)
    {
    	for(AbGroup gr : n_listOfGroups)
    	{
    		if(gr.getGroupName().compareTo(newGroup) == 0)
    			return;
    	}
    	
    	n_listOfGroups.add(new AbGroup (newGroup));
    }
    public void n_removeGroup(String GroupName)
    {
    	for(AbGroup gr : n_listOfGroups)
    	{
    		if(gr.getGroupName().compareTo(GroupName) == 0)
    		{
    			n_listOfGroups.remove(gr);
    			return;
    		}
    	}
    }
    
    public List<AbGroup> n_getListOfGroups()
    {
    	return n_listOfGroups;
    }
    public List<String> n_getListOfGroupNames()
    {
    	List<String> ret = new ArrayList<String>();

    	for(AbGroup gr : n_listOfGroups)
    	{
    		ret.add(gr.getGroupName());
    	}
    	return ret;
    }

    public List<String> n_getListOfSelectedGroups()
    {
    	List<String> ret = new ArrayList<String>();

    	for(AbGroup gr : n_listOfGroups)
    	{
    		if(gr.isSelected())
    			ret.add(gr.getGroupName());
    	}
    	return ret;
    }
    
    
}
