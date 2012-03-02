package abook.listeners;

import java.util.EventListener;

public interface AbListener extends EventListener {
	
	public final static int WORKSPACE_CHANGED = 1; 
	
	public void myEventOccurred(AbEvent evt, int type);

}
