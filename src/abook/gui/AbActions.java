package abook.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import abook.gui.dialogs.AbDialogAddContact;
import abook.gui.dialogs.AbDialogAddGroup;
import abook.gui.dialogs.AbDialogGoogleSync;
import abook.gui.dialogs.AbDialogs;
import abook.gui.dialogs.AbHelp;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbCard;
import abook.profile.InitProfile;

/**
 * Class AbActions contains inner classes appointed to button and other component actions.
 * 
 * @author jurij
 *
 */
public class AbActions {
	
	protected ActionSaveProfile actionSaveProfile;
	protected ActionNewProfile actionNewProfile;
	protected ActionOpenProfile actionOpenProfile;
	protected ActionExitProgram actionExitProgram;
	protected ActionAddContact actionAddContact;
	protected ActionDeleteContact actionDeleteContact;
	protected ActionAddGroup actionAddGroup;
	protected ActionDeleteGroup actionDeleteGroup;
	protected ActionShowView actionShowView;
	protected ActionHelp actionHelp;
	protected ActionSyncContacts actionSyncContacts;
	
	/**
	 * Constructor which creates instances of all actions.
	 */
	public AbActions() {
		actionSaveProfile = new ActionSaveProfile();
		actionNewProfile = new ActionNewProfile();
		actionOpenProfile = new ActionOpenProfile();
		actionExitProgram = new ActionExitProgram();
		actionAddContact = new ActionAddContact();
		actionDeleteContact = new ActionDeleteContact();
		actionShowView = new ActionShowView();
		actionHelp = new ActionHelp();
		actionAddGroup = new ActionAddGroup();
		actionDeleteGroup = new ActionDeleteGroup();
		actionSyncContacts = new ActionSyncContacts();
	}
	
	/**
	 * Method returns instance of action which save profile.
	 * 
	 * @return actionSaveProfile
	 */
	public ActionSaveProfile getActionSaveProfile() {
		return actionSaveProfile;
	}
	
	/**
	 * Method returns instance of action which creates new profile.
	 * 
	 * @return actionNewProfile
	 */
	public ActionNewProfile getActionNewProfile() {
		return actionNewProfile;
	}

	/**
	 * Method returns instance of action which open new profile.
	 * 
	 * @return actionOpenProfile
	 */
	public ActionOpenProfile getActionOpenProfile() {
		return actionOpenProfile;
	}

	/**
	 * Method returns instance of action which exit program.
	 * 
	 * @return actionExitProgram
	 */
	public ActionExitProgram getActionExitProgram() {
		return actionExitProgram;
	}

	/**
	 * Method returns instance of action which add contact.
	 * 
	 * @return actionAddContact
	 */
	public ActionAddContact getActionAddContact() {
		return actionAddContact;
	}

	/**
	 * Method returns instance of action which delete contact.
	 * 
	 * @return actionDeleteConctact
	 */
	public ActionDeleteContact getActionDeleteContact() {
		return actionDeleteContact;
	}
	
	/**
	 * Method returns instance of action which show specific view.
	 * 
	 * @return actionShowView
	 */
	public ActionShowView getActionShowView() {
		return actionShowView;
	}
	
	/**
	 * Method returns instance of action which add groups.
	 * 
	 * @return actionAddGroup
	 */
	public ActionAddGroup getActionAddGroup() {
		return actionAddGroup;
	}

	/**
	 * Method returns instance of action which delete group.
	 * 
	 * @return ActionDeleteGroup
	 */
	public ActionDeleteGroup getActionDeleteGroup() {
		return actionDeleteGroup;
	}

	/**
	 * Method returns instance of action which show help.
	 * 
	 * @return actionHelp
	 */
	public ActionHelp getActionHelp() {
		return actionHelp;
	}
	
	/**
	 * 
	 * @return
	 */
	public ActionSyncContacts getActionSyncContacts(){
		return actionSyncContacts;
	}
	
	// ---------------------------------------------------------------------- //

	/**
	 * Action closes application.
	 * 
	 * @author jurij
	 *
	 */
    @SuppressWarnings("serial")
	class ActionExitProgram extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    /**
	 * Action creates new profile.
	 * 
	 * @author jurij
	 *
	 */
    @SuppressWarnings("serial")
	class ActionNewProfile extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
        	
        	if(!InitProfile.isSaved()) {
        		int result = AbDialogs.YesNoCancel("Do you want to save actual project?");
            	
            	System.out.println(result);
            	
            	// user cancels dialog //
            	if(result == JOptionPane.CANCEL_OPTION) {
            		return;
            	}
            	
            	// user wants to save profile //
            	if(result == JOptionPane.OK_OPTION) {
            		InitProfile.saveProfile();
            	}
        	}
        	
        	String user = AbDialogs.input("Type user:");
        	
        	if(user == null || user.isEmpty()) return;
        	
        	// close all tabs //
        	ViewGui.getAbTabLine().closeAllTabs();
        	
        	// init new profile //
            InitProfile profile = new InitProfile();
            profile.createProfile(user);
            
            // restore tabs //
            ViewGui.getAbTabLine().restoreTabs();
        }
    }
    
    /**
	 * Action saves profile.
	 * 
	 * @author jurij
	 *
	 */
    @SuppressWarnings("serial")
	class ActionSaveProfile extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
        	if(e.getSource() instanceof AbstractButton) {
        		int mnemonic = ((AbstractButton) e.getSource()).getMnemonic();
        		
        		if(mnemonic == KeyEvent.VK_S) {
        			InitProfile.saveProfile();
        		} else {
        			InitProfile.exportProfile();
        		}
        	}
        }
    }
    
    /**
	 * Action opens existing profile. User is asked if he wants to save profile.
	 * Then open dialog appears and user select the file. 
	 * 
	 * @author jurij
	 *
	 */
    @SuppressWarnings("serial")
	class ActionOpenProfile extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
        	
        	// save actual profile //
        	int result = AbDialogs.YesNoCancel("Do you want to save actual project?");
        	
        	// user cancels dialog //
        	if(result == JOptionPane.CANCEL_OPTION) {
        		return;
        	}
        	
        	// user wants to save profile //
        	if(result == JOptionPane.OK_OPTION) {
        		InitProfile.saveProfile();
        	}
        	
        	// get new profile name //
            File workspaceFile = InitProfile.getWorkspaceFile();
            File[] allFiles = workspaceFile.listFiles();
            Vector<String> fileNames = new Vector<String>();
            
            // filter .xml files //
            for(int i = 0; i < allFiles.length; i++) {
            	if(allFiles[i].getName().toLowerCase().endsWith(".xml")) {
            		fileNames.add(allFiles[i].getName());
            	}
            }
            
            // there are no profile files //
            if(fileNames.isEmpty()) {
            	AbDialogs.report("No xml files are in your workspace.");
            	return;
            }
            
            // creates selection dialog //
            String[] a = new String[fileNames.size()];
            result = AbDialogs.select(fileNames.toArray(a), "Select file:");
            
            // user selects new profile file //
            if(result >= 0) {
            	
            	// close all tabs //
            	ViewGui.getAbTabLine().closeAllTabs();
            	
            	// find file from array //
            	String fileName = fileNames.get(result);
            	
            	for(int i = 0; i < allFiles.length; i++) {
                	if(allFiles[i].getName().toLowerCase().endsWith(".xml")) {
                		if(fileName.equals(allFiles[i].getName())) {
                			
                			System.out.println(allFiles[i]);
                			
                			// open new profile //
                			InitProfile.openProfile(allFiles[i]);
                			ViewGui.getAbTabLine().restoreTabs();
                			return;
                		}
                	}
                }
            }
        }
    }
    
    /**
     * Action shows specific tab.
     * 
     * @author jurij
     *
     */
    @SuppressWarnings("serial")
	class ActionShowView extends AbstractAction
    {
    	
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() instanceof AbstractButton) {
				
				AbstractButton source = (AbstractButton) e.getSource();
				
				ViewGui.getAbTabLine().openTab(source.getMnemonic());
			}
        }
    }
    
    /**
     * Action adds new contact.
     * 
     * @author Radovan Dvorsky
     *
     */
    @SuppressWarnings("serial")
	class ActionAddContact extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
           JDialog addContactDialog = new AbDialogAddContact();
           addContactDialog.setVisible(true);
        }
    }
    
    /**
     * Action deletes contact.
     * 
     * @author Radovan Dvorsky
     *
     */
    @SuppressWarnings("serial")
	class ActionDeleteContact extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }

    /**
     * Actions shows dialog with help/informations.
     * 
     * @author jurij
     *
     */
    @SuppressWarnings("serial")
	class ActionHelp extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
            new AbHelp();
        }

    }
    
    /**
     * Action adds new group.
     * 
     * @author Marek Mesar
     *
     */
    @SuppressWarnings("serial")
	class ActionAddGroup extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
        	new AbDialogAddGroup();
        }
    }
    
    /**
     * Action deletes group.
     * 
     * @author Marek Mesar
     *
     */
    @SuppressWarnings("serial")
	class ActionDeleteGroup extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
        	if(InitProfile.getProfile().getListOfAbCards().get(InitProfile.getProfile().getOpenedTab()).getType() == AbCard.GROUPS){
        		InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.TRY_DELETE_GROUP);
        	}
        		
        }
    }
    
    /**
     * Action which sync contats.
     * 
     * @author Radovan Dvorsky
     *
     */
    @SuppressWarnings("serial")
	class ActionSyncContacts extends AbstractAction
    {
    	
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() instanceof AbstractButton) {
				new AbDialogGoogleSync().setVisible(true);
			}
        }
    }

}
