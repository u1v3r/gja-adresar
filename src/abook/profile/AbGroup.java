package abook.profile;

/**
 * Class for group informations.
 * 
 * @author jurij
 *
 */
public class AbGroup {

	protected String name;
	protected String description;
	
	/**
	 * Creates new group.
	 * 
	 * @param name = group name
	 */
	public AbGroup(String name) {
		this.name = name;
		this.description = "";
	}
	
	/**
	 * Creates new group with a description
	 * 
	 * @param name = group name
	 * @param description = group description
	 */
	public AbGroup(String name, String description)
	{
		this.name = name;
		this.description = description;
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
	
}
