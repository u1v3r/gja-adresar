package abook.profile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbProfile {
	
	protected static int counter = 1;
	protected int id;
	protected String user;
	protected Set<AbPerson> directory;
	protected List<AbCard> listOfAbCards;
	
	/**
	 * Creates new profile
	 */
	public AbProfile(String user) {
		
		this.id = counter;
		this.user = user;
		this.directory = new HashSet<AbPerson>();
		listOfAbCards = new ArrayList<AbCard>();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void addPerson(AbPerson person) {
		directory.add(person);
	}
	
	public void getPerson(AbPerson person) {
		directory.remove(person);
	}
	
	public AbCard addSpecialCard(int type) {
		
		AbCard specialCard = (AbCard) new AbCard(type);
		listOfAbCards.add(specialCard);
		
		return (AbCard) specialCard;	
	}
	
	public List<AbCard> getListOfAbCards() {
		return listOfAbCards;
	}
	
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
	
	public AbCard removeAbICard(int index) {
		
		return (listOfAbCards.remove(index));
		
	}
	
    public AbCard removeAbICard() {
		
		return removeAbICard(listOfAbCards.size());
		
	}

}
