package abook.gui.tabs;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

public class AbTabGroups implements AbITabComponent {
	
	protected JScrollPane panel;
	protected final String name = "Groups";
	protected final String tooltip = "Contact groups card";
	
	public AbTabGroups() {
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

}
