package abook.gui.tabs;

import javax.swing.JComponent;

import abook.profile.AbCard;

public abstract class AbTab {
	
	protected boolean open;
	protected AbCard card;
	protected String name;
	protected String tooltip;
	
	public AbTab() {
		
		// tab is closed and has got selected no card //
		this.open = false;
		this.card = null;
	}

	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getName() {
		return name;
	}

	public String getTooltip() {
		return tooltip;
	}
	
	public abstract JComponent getWidget();

}
