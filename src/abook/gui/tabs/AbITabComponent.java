package abook.gui.tabs;

import javax.swing.JComponent;

public interface AbITabComponent {
	
	public JComponent getWidget();
	
	public String getName();
	
	public String getTooltip();

	void actualizeTab();

}
