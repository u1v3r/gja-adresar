package abook.gui.tabs;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

import abook.AbIGuiTabComponent;
import abook.gui.table.AbTable;

public class AbTabHome implements AbIGuiTabComponent {
	
	protected boolean open;
	protected JScrollPane panel;
	protected final String name = "Home";
	protected final String tooltip = "Home card";
	protected AbTable table;
	
	public AbTabHome() {
		
		this.panel = new JScrollPane();
		this.open = false;
		
		this.table = new AbTable();
		
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
