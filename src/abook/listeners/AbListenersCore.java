package abook.listeners;

import java.util.ArrayList;
import java.util.List;

public class AbListenersCore {
	
	protected List<AbListener> listOfListeners;
	
	public AbListenersCore() {
		this.listOfListeners = new ArrayList<AbListener>();
	}
	
	public void addListener(AbListener listener) {
		listOfListeners.add(listener);
	}
	
	public void fireListeners(AbEvent event, int type) {
		for(AbListener listener : listOfListeners) {
			listener.myEventOccurred(event, type);
		}
	}

}
