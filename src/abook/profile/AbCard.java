package abook.profile;

public class AbCard {
	
	protected int type;
	protected int position;
	public final static int HOME = 0;
	public final static int DATABASE = 1;
	public final static int GROUPS = 2;
	public final static int EVENTS = 3;
	
	public AbCard(int type) {
		
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public int getIndex() {
		
		return InitProfile.getProfile().getListOfAbCards().indexOf(this);
	}

}
