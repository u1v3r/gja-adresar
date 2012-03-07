package abook.gui.tabs;

import javax.swing.JComponent;
import javax.swing.JScrollPane;


public class AbTabHome implements AbITabComponent {
	
	protected JScrollPane panel;
	protected final String name = "Home";
	protected final String tooltip = "Home card";
	
	public AbTabHome() {
		
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
