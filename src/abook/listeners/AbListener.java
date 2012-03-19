package abook.listeners;

import java.util.EventListener;

public interface AbListener extends EventListener {
	
	public final static int WORKSPACE_CHANGED = 1;
	public final static int GROUP_SELECTION_CHANGED = 2;
	public final static int NEW_PROFILE_OPENED = 3;
	public final static int GROUPS_CHANGED = 4;
	
	public void myEventOccurred(AbEvent evt, int type);

}
