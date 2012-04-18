package abook.listeners;

/**
 * Class which contains static instance of listener core.
 * 
 * @author jurij
 *
 */
public class InitListenerCore {
	
	/**
	 * Static instance of listener core which contains array list of all listeners.
	 */
	protected static AbListenersCore listenerCore;
	
	/**
	 * Initializes listener core.
	 */
	public InitListenerCore() {
		listenerCore = new AbListenersCore();
	}
	
	/**
	 * Getter for static instance of listener core.
	 * 
	 * @return listenerCore
	 */
	public static AbListenersCore getListenerCore() {
		return listenerCore;
	}

}
