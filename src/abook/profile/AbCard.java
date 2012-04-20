package abook.profile;

/**
 * Class for tab profile informations.
 * 
 * @author jurij
 *
 */
public class AbCard {
	
	protected int type;
	protected int position;
	public final static int HOME = 0;
	public final static int DATABASE = 1;
	public final static int GROUPS = 2;
	public final static int EVENTS = 3;
	public static final int DETAILS = 4;
	
	/**
	 * Creates new card.
	 * 
	 * @param type
	 */
	public AbCard(int type) {
		
		this.type = type;
	}

	/**
	 * Returns type of card.
	 * 
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Returns actual index.
	 * 
	 * @return index
	 */
	public int getIndex() {
		
		return InitProfile.getProfile().getListOfAbCards().indexOf(this);
	}

}
