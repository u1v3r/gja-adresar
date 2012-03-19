package abook.listeners;

import java.util.EventListener;

public interface AbListener extends EventListener {
	
	public final static int WORKSPACE_CHANGED = 1;
	public final static int GROUP_SELECTION_CHANGED = 2;
	public final static int NEW_PROFILE_OPENED = 3;
	public final static int NEW_GROUP_ADDED = 4;
	public final static int GROUP_REMOVED = 5;
	public final static int GROUP_EDITED = 6;
	
	public void myEventOccurred(AbEvent evt, int type);

}
