package abook.profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	protected static boolean saved;
	
	/**
	 * Creates new profile. Asks user for profile name.
	 */
	public void createProfile(String user) {
		// TODO ... get workspace information from user //
		
		// 1. workspace //
		workspace = new String("." + File.separator + "workspace-aBook");
		
		workspaceFile = new File(workspace);
		if(!workspaceFile.exists()) {
			boolean result = workspaceFile.mkdir();
			System.out.println("Workspace: " + result);
		}
		
		// 2. user //
		if(user == null || user.isEmpty()) {
			user = "user";
			userFile = new File(workspace + File.separator + "user.xml");
			userFileDir = new File(workspace + File.separator + "user");
		} else {
			userFile = new File(workspace + File.separator + user + ".xml");
			userFileDir = new File(workspace + File.separator + user);
		}
		
		if(!userFile.exists()) {
			// create new profile
			System.out.println("new profile");
			createNewProfile(user);
			saveProfile();
		} else {
			// open existing profile
			openProfile(userFile);
		}
		
		saved = true;
		
		if(ViewGui.isGuiCreated()) {
			InitListenerCore.getListenerCore().fireListeners(new AbEvent(profile), AbListener.PROFILE_CHANGED);
			InitListenerCore.getListenerCore().fireListeners(new AbEvent(profile), AbListener.WORKSPACE_STRUCT_CHANGED);
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
		profile.addGroup("all");
		/*profile.addGroup("friends");
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
		profile.addPerson(person2);*/
		
		saved = true;
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
        userFile = new File(workspace + File.separator + user + ".xml");
		userFileDir = new File(workspace + File.separator  + user);
		
		if(ViewGui.isGuiCreated()) {
			InitListenerCore.getListenerCore().fireListeners(new AbEvent(profile), AbListener.PROFILE_CHANGED);
		}
        
		saved = true;
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
    		AbDialogs.report("Soubor " + userFile +  " se nepodarilo vytvorit.\nTreba nemate prava pro zapis.");
    	}
    	
    	setSaved(true);
    }
    
    /**
     * Method saves "as" the file.
     */
    public static void saveAsProfile(boolean newProfile) {
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

        XStream xstream = new XStream();
        String a = xstream.toXML(profile);
        //System.out.print(a);
        try {
        	File xmlFile = new File(selFile.getPath() + ".xml");
            PrintStream tisk = new PrintStream(xmlFile);
            tisk.print(a);
            
            if(newProfile) {
            	profile.setUserName(selFile.getName());
                userFile = new File(workspace + File.separator + xmlFile);
            }
			File newUserFileDir = new File(workspace + File.separator + selFile.getName());
			
			if (!newUserFileDir.exists()) {
                newUserFileDir.mkdir();
            }
			
			String[] children = userFileDir.list();
	        for (int i = 0; i < children.length; i++) {
	        	System.out.println(children[i]);
	        	
	        	InputStream in = new FileInputStream(new File(userFileDir + File.separator + children[i]));
	            OutputStream out = new FileOutputStream(new File(newUserFileDir + File.separator + children[i]));
	            
	            byte[] buf = new byte[1024];
	            int len;
	            while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	            in.close();
	            out.close();
	        }
	        
	        if(newProfile) {
	        	userFileDir = newUserFileDir;
	        }
            
        } catch (FileNotFoundException ex) {
            AbDialogs.report("Soubor se nepodarilo vytvorit.\nTreba nemate prava pro zapis.");
        } catch (IOException e) {
        	AbDialogs.report("Kopirovani slozky se nepodarilo.\nTreba nemate prava pro zapis.");
        }
        
        setSaved(true);
        
        InitListenerCore.getListenerCore().fireListeners(new AbEvent(profile), AbListener.WORKSPACE_STRUCT_CHANGED);
    }

	public static boolean isSaved() {
    	return saved;
    }

	public static void setSaved(boolean saved) {
		
		if(InitProfile.saved != saved) {
			InitProfile.saved = saved;
			InitListenerCore.getListenerCore().fireListeners(new AbEvent(profile), AbListener.SAVED_CHANGED);
		}
    }

	public static void importProfile() {
	    // TODO Auto-generated method stub
	    
    }

	public static void exportProfile() {
	    saveAsProfile(false);
    }

}
