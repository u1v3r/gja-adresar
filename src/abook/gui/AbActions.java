package abook.gui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import abook.gui.dialogs.AbDialogAddContact;
import abook.gui.dialogs.AbDialogAddGroup;
import abook.gui.dialogs.AbDialogs;
import abook.gui.dialogs.AbHelp;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.InitProfile;

/**
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
	
	/**
	 * Constructor which creates actions.
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
	}
	
	/**
	 * 
	 * @return
	 */
	public ActionSaveProfile getActionSaveProfile() {
		return actionSaveProfile;
	}
	
	/**
	 * 
	 * @return
	 */
	public ActionNewProfile getActionNewProfile() {
		return actionNewProfile;
	}

	/**
	 * 
	 * @return
	 */
	public ActionOpenProfile getActionOpenProfile() {
		return actionOpenProfile;
	}

	/**
	 * 
	 * @return
	 */
	public ActionExitProgram getActionExitProgram() {
		return actionExitProgram;
	}

	/**
	 * 
	 * @return
	 */
	public ActionAddContact getActionAddContact() {
		return actionAddContact;
	}

	/**
	 * 
	 * @return
	 */
	public ActionDeleteContact getActionDeleteContact() {
		return actionDeleteContact;
	}
	
	/**
	 * 
	 * @return
	 */
	public ActionShowView getActionShowView() {
		return actionShowView;
	}
	
	/**
	 * 
	 * @return
	 */
	public ActionAddGroup getActionAddGroup() {
		return actionAddGroup;
	}

	/**
	 * 
	 * @return
	 */
	public ActionDeleteGroup getActionDeleteGroup() {
		return actionDeleteGroup;
	}

	/**
	 * 
	 * @return
	 */
	public ActionHelp getActionHelp() {
		return actionHelp;
	}
	
	// ---------------------------------------------------------------------- //

	/**
	 * Action which closes application.
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
	 * Action which creates new profile.
	 * 
	 * @author jurij
	 *
	 */
    @SuppressWarnings("serial")
	class ActionNewProfile extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
        	
        	int result = AbDialogs.YesNoCancel("Do you want to save actual project?");
        	
        	System.out.println(result);
        	
            //InitProfile.saveProfile(); TODO
        }
    }
    
    /**
	 * Action which closes application.
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
        		
        		if(mnemonic == 0) {
        			InitProfile.saveProfile();
        		} else if(mnemonic == 1) {
        			InitProfile.exportProfile();
        		}
        	}
        }
    }
    
    /**
	 * Action which open existing profile.
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
        	
        	if(result == JOptionPane.CANCEL_OPTION) {
        		return;
        	}
        	
        	if(result == JOptionPane.OK_OPTION) {
        		InitProfile.saveProfile();
        	}
        	
        	// get new profile name //
            File workspaceFile = InitProfile.getWorkspaceFile();
            File[] allFiles = workspaceFile.listFiles();
            Vector<String> fileNames = new Vector<String>();
            
            for(int i = 0; i < allFiles.length; i++) {
            	if(allFiles[i].getName().toLowerCase().endsWith(".xml")) {
            		fileNames.add(allFiles[i].getName());
            	}
            }
            
            if(fileNames.isEmpty()) {
            	AbDialogs.report("No xml files are in your workspace.");
            	return;
            }
            
            String[] a = new String[fileNames.size()];
            
            result = AbDialogs.select(fileNames.toArray(a), "Select file:");
            
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
     * Action which shows specific tab.
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
    
    @SuppressWarnings("serial")
	class ActionAddContact extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
           JDialog addContactDialog = new AbDialogAddContact();
           addContactDialog.setVisible(true);
        }
    }
    
    @SuppressWarnings("serial")
	class ActionDeleteContact extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
            // TODO
        }
    }

    @SuppressWarnings("serial")
	class ActionHelp extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
            new AbHelp();
        }

    }
    
    @SuppressWarnings("serial")
	class ActionAddGroup extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
        	new AbDialogAddGroup();
        }
    }
    
    @SuppressWarnings("serial")
	class ActionDeleteGroup extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
            // TODO
        	InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.TRY_DELETE_GROUP);
        }
    }

}
