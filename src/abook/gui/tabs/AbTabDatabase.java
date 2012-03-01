package abook.gui.tabs;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import abook.AbIGuiTabComponent;

public class AbTabDatabase implements AbIGuiTabComponent {
	
	protected boolean open;
	protected JScrollPane panel;
	protected final String name = "Database";
	protected final String tooltip = "Table of all contacts";
	
	public AbTabDatabase() {
		
		this.panel = new JScrollPane();
		this.open = false;
	}

	@Override
	public JComponent getWidget() {
		return panel;
	}

	@Override
	public boolean isOpen() {
		return open;
	}
	
	@Override
	public void setOpen(boolean open) {
		this.open = open;
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