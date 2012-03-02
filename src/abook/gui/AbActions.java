package abook.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JDialog;

import abook.gui.dialogs.AbDialogAddContact;

public class AbActions {
	
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
            /*AbHelp napoveda = new AbHelp();
            napoveda.setVisible(true);*/
        }

    }

}
