package abook.profile;


public class InitProfile {
	
	protected static AbProfile profile;
	
	public void createProfile() {
		// TODO ... get information from user //
		
		// create default new profile
		createNewProfile();
		// TODO open existing profile
    }
	
	private void createNewProfile() {
		
		// create new instance of core profile //
		profile = new AbProfile("default");
		
		// set default variables //
		profile.addSpecialCard(AbCard.HOME);
		profile.addSpecialCard(AbCard.DATABASE);
		
		// create default groups //
		profile.addGroup("friends");
		profile.addGroup("school");
		
		// create default contacts //
		AbPerson person1 = new AbPerson("Karel", "Novák");
		person1.setCity("Praha");
		person1.addGroup(0);
		
		// create default contacts //
		AbPerson person2 = new AbPerson("Laco", "Lakatoš");
		person2.setCity("Košice");
		person2.addGroup(0);
		person2.addGroup(1);
		
		
	}
	
	public static AbProfile getProfile() {
		return profile;
	}

}
