package abook.profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import com.thoughtworks.xstream.XStream;

import abook.gui.ViewGui;
import abook.gui.dialogs.AbDialogs;
import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;

/**
 * Class for initialization of profile.
 * Class contains static instance of profile.
 * 
 * @author jurij
 *
 */
public class InitProfile {
	
	protected static AbProfile profile;
	protected static String workspace;
	protected static File workspaceFile;
	protected static File userFile;
	protected static File userFileDir;
	
	/**
	 * Creates new profile. Asks user for profile name.
	 */
	public void createProfile() {
		// TODO ... get workspace information from user //
		
		// 1. workspace //
		workspace = new String("./workspace-aBook");
		
		workspaceFile = new File(workspace);
		if(!workspaceFile.exists()) {
			boolean result = workspaceFile.mkdir();
			System.out.println("Workspace: " + result);
		}
		
		// 2. user //
		String user = AbDialogs.input("Type user:");
		if(user == null || user.isEmpty()) {
			user = "user";
			userFile = new File(workspace + "/user.xml");
			userFileDir = new File(workspace + "/user");
		} else {
			userFile = new File(workspace + "/" + user + ".xml");
			userFileDir = new File(workspace + "/" + user);
		}
		
		if(!userFile.exists()) {
			// create new profile
			createNewProfile(user);
			saveProfile();
		} else {
			// open existing profile
			openProfile(userFile);
		}
    }
	
	/**
	 * Creates new profile with selected name.
	 * 
	 * @param user
	 */
	private void createNewProfile(String user) {
		
		// create new instance of core profile //
		profile = new AbProfile(user);
		
		// set default variables //
		profile.addSpecialCard(AbCard.HOME);
		profile.addSpecialCard(AbCard.DATABASE);
		
		// create default groups //
		profile.addGroup("friends");
		profile.addGroup("school");
		
		// create default contacts //
		AbPerson person1 = new AbPerson("Karel", "Novák");
		person1.setCity("Praha");
		person1.addGroup("friends");
		profile.addPerson(person1);
		
		// create default contacts //
		AbPerson person2 = new AbPerson("Laco", "Lakatoš");
		person2.setCity("Košice");
		person2.addGroup("friends");
		person2.addGroup("school");
		profile.addPerson(person2);
		
		
	}
	
	/**
	 * Opens profile witch selected file.
	 * 
	 * @param file
	 */
	public static void openProfile(File file) {

        XStream xstream = new XStream();
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream f;
        try {
            f = new FileInputStream(file);
            try {
                f.read(buffer);
            } catch (IOException ex) {
                AbDialogs.report("Unable to read file" + file.getName() + ".");
            }
        } catch (FileNotFoundException ex) {
            AbDialogs.report("Unable to open file" + file.getName() + "!");
        }

        String a = new String(buffer);
        profile = (AbProfile) xstream.fromXML(a);
        String user = profile.getUserName();        
        userFile = new File(workspace + "/" + user + ".xml");
		userFileDir = new File(workspace + "/" + user);
		
		if(ViewGui.isGuiCreated()) {
			InitListenerCore.getListenerCore().fireListeners(new AbEvent(profile), AbListener.PROFILE_CHANGED);
		}
        
	}
	
	/**
	 * Returns static instance of profile.
	 * 
	 * @return profile
	 */
	public static AbProfile getProfile() {
		return profile;
	}
	
	/**
	 * Returns actual name of workspace.
	 * 
	 * @return workspace
	 */
	public static String getWorkspace() {
		return workspace;
	}

	/**
	 * Returns actual workspace file.
	 * 
	 * @return workspaceFile
	 */
	public static File getWorkspaceFile() {
		return workspaceFile;
	}

	/**
	 * Returns actual user file directory path.
	 * 
	 * @return userFileDir
	 */
	public static File getUserFileDir() {
		return userFileDir;
	}

	/**
     * Method saves the file.
     */
    public static void saveProfile() {
    	
    	XStream xstream = new XStream();
    	String a = xstream.toXML(profile);
    	//System.out.print(a);
    	try {
    		PrintStream tisk = new PrintStream(userFile);
    		tisk.print(a);
    	} catch (FileNotFoundException ex) {
    		AbDialogs.report("Soubor se nepodarilo vytvorit.\nTreba nemate prava pro zapis.");
    	}
    }
    
    /**
     * Method saves "as" the file.
     */
    public static void exportProfile() {
    	File selFile;

        // show file chooser //
        JFrame frame = new JFrame();
        frame.setBounds(200, 200, 500, 350);
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(workspaceFile);
        fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return ".xml";
			}
			
			@Override
			public boolean accept(File file) {
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".xml");
			}
		});
        frame.add(fc);
        fc.showSaveDialog(frame);
        selFile = (fc.getSelectedFile());
        if(selFile == null) { return; }
        //System.out.print(selFile);
        profile.setUserName(selFile.getName() + ".xml");

        XStream xstream = new XStream();
        String a = xstream.toXML(profile);
        //System.out.print(a);
        try {
            PrintStream tisk = new PrintStream(selFile);
            tisk.print(a);
        } catch (FileNotFoundException ex) {
            AbDialogs.report("Soubor se nepodarilo vytvorit.\nTreba nemate prava pro zapis.");
        }
        
        InitListenerCore.getListenerCore().fireListeners(new AbEvent(profile), AbListener.WORKSPACE_STRUCT_CHANGED);
    }

}
