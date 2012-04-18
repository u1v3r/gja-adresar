package abook.profile;

/**
 * Class for group informations.
 * 
 * @author jurij
 *
 */
public class AbGroup {

	protected String name;
	//protected boolean selected;
	protected String description;
	
	/**
	 * Creates new group.
	 * 
	 * @param name
	 */
	public AbGroup(String name) {
			this.name = name;
			//this.selected = false;
	}
	
	/**
	 * Returns name of group.
	 * 
	 * @return name
	 */
	public String getGroupName()
	{
		return name;
	}
	
	/**
	 * Sets name of group.
	 * 
	 * @param newName
	 */
	public void setGroupName(String newName)
	{
		this.name = newName;
	}

	/**
	 * Returns description of group.
	 * 
	 * @return description
	 */
	public String getDescription() {
    	return description;
    }

	/**
	 * Sets description of group.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
    	this.description = description;
    }
	
	/*public boolean isSelected()
	{
		return selected;
	}
	
	public void setSelected(boolean value)
	{
		this.selected = value;
	}*/
	
	
}
