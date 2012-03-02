package abook.gui.tabs;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

public class AbTabEvents implements AbITabComponent {
	
	protected JScrollPane panel;
	protected final String name = "Events";
	protected final String tooltip = "Events card";
	
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

}
