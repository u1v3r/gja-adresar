package abook.listeners;

import java.util.EventListener;

/**
 * Interface for our listener.
 * 
 * @author jurij
 *
 */
public interface AbListener extends EventListener {
	
	/**
	 * When tree structure of files is changed.
	 */
	public final static int WORKSPACE_STRUCT_CHANGED = 1;
	
	/**
	 * When group selection is changed.
	 */
	public final static int GROUP_SELECTION_CHANGED = 2;
	
	/**
	 * When new profile is opened.
	 */
	public final static int PROFILE_CHANGED = 3;
	
	/**
	 * When group structure is changed.
	 */
	public final static int GROUPS_CHANGED = 4;

	//public final static int TRY_DELETE_GROUP = 5;
	
	/**
	 * When search pattern is changed.
	 */
	public final static int SEARCH_CHANGED = 6;
	
	public void myEventOccurred(AbEvent evt, int type);

}
