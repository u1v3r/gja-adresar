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
	}
	
	public static AbProfile getProfile() {
		return profile;
	}

}
