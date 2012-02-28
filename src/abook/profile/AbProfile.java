package abook.profile;

import java.util.HashSet;
import java.util.Set;

public class AbProfile {
	
	protected static int counter = 1;
	protected int id;
	protected String user;
	protected Set<AbPerson> directory;
	
	/**
	 * Creates new profile
	 */
	public AbProfile(String user) {
		
		this.id = counter;
		this.user = user;
		this.directory = new HashSet<AbPerson>();
		
	}

}
