package abook.profile;

import java.awt.event.KeyEvent;

/**
 * Class for tab profile informations.
 * 
 * @author jurij
 *
 */
public class AbCard {
	
	protected int type;
	protected int position;
	public final static int HOME = KeyEvent.VK_H;
	public final static int DATABASE = KeyEvent.VK_D;
	public final static int GROUPS = KeyEvent.VK_G;
	public final static int EVENTS = KeyEvent.VK_V;
	public static final int DETAILS = KeyEvent.VK_T;
	
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
