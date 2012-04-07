package abook.gui;

import javax.swing.JComponent;

/**
 * Interface for all GUI components which are contained in main shell.
 * 
 * @author jurij
 *
 */
public interface AbIGuiComponent {
	
	/**
	 * Returns main JComponent of GUI component.
	 * 
	 * @return widget
	 */
	public JComponent getWidget();

}
