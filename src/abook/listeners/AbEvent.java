package abook.listeners;

import java.util.EventObject;

/**
 * Class for our events.
 * 
 * @author jurij
 *
 */
@SuppressWarnings("serial")
public class AbEvent extends EventObject {

	/**
	 * Creates new event.
	 * 
	 * @param source
	 */
	public AbEvent(Object source) {
		super(source);
	}

}
