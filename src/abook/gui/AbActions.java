package abook.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class AbActions {
	
	/**
     * Closes the program
     */
    @SuppressWarnings("serial")
	class ActionExitProgram extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
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
