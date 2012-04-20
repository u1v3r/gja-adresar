package abook;

import javax.swing.UIManager;

import abook.gui.ViewGui;
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
        
        InitProfile newProfile = new InitProfile();
        newProfile.createProfile();
        
        ViewGui newGui = new ViewGui();
        newGui.launchAppliacation();
	}

}
