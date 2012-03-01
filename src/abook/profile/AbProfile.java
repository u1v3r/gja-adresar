package abook.profile;

import java.util.ArrayList;
import java.util.List;

public class AbProfile {
	
	protected static int counter = 1;
	protected int id;
	protected String user;
	protected List<AbPerson> listOfAbPersons;
	protected List<AbCard> listOfAbCards;
	
	/**
	 * Creates new profile
	 */
	public AbProfile(String user) {
		
		this.id = counter;
		this.user = user;
		this.listOfAbPersons = new ArrayList<AbPerson>();
		this.listOfAbCards = new ArrayList<AbCard>();
	}

	/**
	 * 
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public void setUser(String user) {
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
    public List<AbPerson> getListOfAbPesrons() {
    	return listOfAbPersons;
    }
    

}
