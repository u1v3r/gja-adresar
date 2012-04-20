package abook;

import javax.swing.UIManager;

import abook.gui.ViewGui;
import abook.gui.dialogs.AbDialogs;
import abook.listeners.InitListenerCore;
import abook.profile.InitProfile;

/**
 * Main class which initiates profile, listeners and creates GUI.
 * 
 * @author jurij
 *
 */
public class Main {

	/**
	 * Main method which initiates profile, listeners and creates GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("ahoj");
		
		try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (Exception e) {
           System.err.println("Oops!  Something went wrong!");
        }
        
        new InitListenerCore();
        
        String user = AbDialogs.input("Type user:");
        InitProfile newProfile = new InitProfile();
        newProfile.createProfile(user);
        
        ViewGui newGui = new ViewGui();
        newGui.launchAppliacation();
	}

}
