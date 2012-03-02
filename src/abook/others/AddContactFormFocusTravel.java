package abook.others;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;

/**
 * Určuje poradie prechodu focusu widgetov
 * 
 * @author Radovan Dvorský
 *
 */
public class AddContactFormFocusTravel extends FocusTraversalPolicy {

	private Component[] focusList;
	private int focusNumber;
	
	public AddContactFormFocusTravel(Component[] focusList){
		this.focusList = focusList;
	}
	
	@Override
	public Component getComponentAfter(Container aContainer,
			Component aComponent) {
		
		if(focusList.length == 0) return null;
		
		return focusList[(++focusNumber) % focusList.length];
	}

	@Override
	public Component getComponentBefore(Container aContainer,
			Component aComponent) {
		
		if(focusList.length == 0) return null;
		
		return focusList[(focusList.length + --focusNumber) % focusList.length];
	}

	@Override
	public Component getFirstComponent(Container aContainer) {
		if(focusList.length == 0) return null;
		
		return focusList[0];		
	}

	@Override
	public Component getLastComponent(Container aContainer) {
		
		if(focusList.length == 0) return null;
		
		return focusList[focusList.length - 1];
	}

	@Override
	public Component getDefaultComponent(Container aContainer) {
		return getFirstComponent(aContainer);
	}

}
