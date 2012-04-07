package abook.profile;


public class AbGroup {

	protected String name;
	//protected boolean selected;
	protected String description;
	
	public AbGroup(String name) {
			this.name = name;
			//this.selected = false;
	}
	
	public String getGroupName()
	{
		return name;
	}
	
	public void setGroupName(String newName)
	{
		this.name = newName;
	}
	
	/*public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected(boolean value)
	{
		this.selected = value;
	}*/

	public String getDescription() {
    	return description;
    }

	public void setDescription(String description) {
    	this.description = description;
    }
	
	
}
