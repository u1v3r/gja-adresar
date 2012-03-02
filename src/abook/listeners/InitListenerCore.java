package abook.listeners;

public class InitListenerCore {
	
	protected static AbListenersCore listenerCore;
	
	public InitListenerCore() {
		listenerCore = new AbListenersCore();
	}
	
	public static AbListenersCore getListenerCore() {
		return listenerCore;
	}

}
