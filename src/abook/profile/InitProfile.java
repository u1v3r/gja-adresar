package abook.profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import abook.gui.dialogs.AbReports;
import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;

import com.thoughtworks.xstream.XStream;


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
		profile.addPerson(person1);
		
		// create default contacts //
		AbPerson person2 = new AbPerson("Laco", "Lakatoš");
		person2.setCity("Košice");
		person2.addGroup(0);
		person2.addGroup(1);
		profile.addPerson(person2);
		
		
	}
	
	public static AbProfile getProfile() {
		return profile;
	}
	
	
	/**
     * Methods save the file
     */
    public static void saveProfile() {

        File selFile;

        // show file chooser //
        JFrame frame = new JFrame();
        frame.setBounds(200, 200, 500, 350);
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(profile.getWorkspace()));
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
            AbReports.hlaseni("Soubor se nepodarilo vytvorit.\nTreba nemate prava pro zapis.");
        }
        
        InitListenerCore.getListenerCore().fireListeners(new AbEvent(profile), AbListener.WORKSPACE_CHANGED);
        
    }

}
