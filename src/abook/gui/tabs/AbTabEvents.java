package abook.gui.tabs;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 * Tab with events.
 * 
 * @author jurij
 *
 */
public class AbTabEvents implements AbITabComponent {
	
	protected JScrollPane panel;
	protected final String name = "Events";
	protected final String tooltip = "Events card";
	
	/**
	 * Creates new event tab.
	 */
	public AbTabEvents() {
		this.panel = new JScrollPane();
	}

	@Override
	public JComponent getWidget() {
		return panel;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTooltip() {
		return tooltip;
	}

	@Override
    public void actualizeTab() {
	    // TODO Auto-generated method stub
	    
    }

}
