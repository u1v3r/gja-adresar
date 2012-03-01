package abook;

import javax.swing.JComponent;

public interface AbIGuiTabComponent {
	
	public JComponent getWidget();
	
	public boolean isOpen();
	
	public void setOpen(boolean open);
	
	public String getName();
	
	public String getTooltip();

}
