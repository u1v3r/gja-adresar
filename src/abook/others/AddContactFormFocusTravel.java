package abook.others;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.List;

/**
 * Určuje poradie prechodu focusu widgetov
 * 
 * @author Radovan Dvorský
 *
 */
public class AddContactFormFocusTravel extends FocusTraversalPolicy {

	private List<Component> focusList;
	private int focusNumber;
	
	public AddContactFormFocusTravel(List<Component> focusList){
		this.focusList = focusList;
	}
	
	@Override
	public Component getComponentAfter(Container aContainer,
			Component aComponent) {
		
		if(focusList.isEmpty()) return null;
		
		return focusList.get((++focusNumber) % focusList.size());
	}

	@Override
	public Component getComponentBefore(Container aContainer,
			Component aComponent) {
		
		if(focusList.isEmpty()) return null;
		
		return focusList.get((focusList.size() + --focusNumber) % focusList.size());
	}

	@Override
	public Component getFirstComponent(Container aContainer) {
		if(focusList.isEmpty()) return null;
		
		return focusList.get(0);		
	}

	@Override
	public Component getLastComponent(Container aContainer) {
		
		if(focusList.isEmpty()) return null;
		
		return focusList.get(focusList.size() - 1);
	}

	@Override
	public Component getDefaultComponent(Container aContainer) {
		return getFirstComponent(aContainer);
	}
	
	
	public void setFocusComponent(Container aContainer){
		if(focusList.isEmpty()) return;
		this.focusNumber = this.focusList.indexOf(aContainer);	
	}

}
