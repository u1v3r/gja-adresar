package abook.listeners;

import java.util.ArrayList;
import java.util.List;

/**
 * Main core for which contains array list of all listener instances.
 * 
 * @author jurij
 *
 */
public class AbListenersCore {
	
	/**
	 * list of all listeners
	 */
	protected List<AbListener> listOfListeners;
	
	/**
	 * Creates new listener core.
	 */
	public AbListenersCore() {
		this.listOfListeners = new ArrayList<AbListener>();
	}
	
	/**
	 * Adds new listener to array list.
	 * 
	 * @param listener
	 */
	public void addListener(AbListener listener) {
		listOfListeners.add(listener);
	}
	
	/**
	 * Invokes all listeners.
	 * 
	 * @param event
	 * @param type
	 */
	public void fireListeners(AbEvent event, int type) {
		for(AbListener listener : listOfListeners) {
			listener.myEventOccurred(event, type);
		}
	}

}
