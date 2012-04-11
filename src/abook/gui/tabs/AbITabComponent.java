package abook.gui.tabs;

import javax.swing.JComponent;

/**
 * Interface for tab instances.
 * 
 * @author jurij
 *
 */
public interface AbITabComponent {
	
	/**
	 * Returns main widget.
	 * 
	 * @return widget
	 */
	public JComponent getWidget();
	
	/**
	 * Returns name of tab component.
	 * 
	 * @return name
	 */
	public String getName();
	
	/**
	 * Returns tool tip text.
	 * 
	 * @return toolTip
	 */
	public String getTooltip();

	/**
	 * Method actualizes tab. This method is invoked when profile structure is changed and is important to redraw tab component.
	 */
	void actualizeTab();

}
