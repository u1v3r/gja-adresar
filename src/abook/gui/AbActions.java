package abook.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JDialog;

import abook.gui.dialogs.AbDialogAddContact;
import abook.gui.dialogs.AbHelp;

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
            //InitProfile.saveProfile(); TODO
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

}
