package abook.gui;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import abook.AbIGuiComponent;

public class AbToolBar extends AbActions implements AbIGuiComponent {
	
	protected JToolBar bar;
    protected JButton buttonHelp;
    protected JButton saveButton;

    public AbToolBar() {
        bar = new JToolBar();

        // KEY //
        saveButton = new JButton();
        //ActionHelp doKey = new ActionKey();
        //keyButton.setAction(doKey);
        saveButton.setIcon(new ImageIcon(this.getClass().getResource("/icons/Save.png")));
        saveButton.setToolTipText("Save");
        bar.add(saveButton);

        // HELP //
        buttonHelp = new JButton();
        ActionHelp doHelp = new ActionHelp();
        buttonHelp.setAction(doHelp);
        buttonHelp.setIcon(new ImageIcon(this.getClass().getResource("/icons/Info.png")));
        buttonHelp.setToolTipText("Help");
        bar.add(buttonHelp);
    }

   public Component getWidget(){
        return bar;
    }

}
