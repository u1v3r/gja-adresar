package abook;

import javax.swing.UIManager;

import abook.gui.ViewGui;
import abook.profile.InitProfile;

public class Main {

	/**
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
        
        InitProfile newProfile = new InitProfile();
        newProfile.createProfile();
        
        ViewGui newGui = new ViewGui();
        newGui.launchAppliacation();
	}

}
