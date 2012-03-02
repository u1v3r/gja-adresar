package abook.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToolBar;

import abook.AbIGuiComponent;
import abook.profile.AbCard;

public class AbToolBar extends AbActions implements AbIGuiComponent {
	
	protected JToolBar bar;
	protected JButton buttonNew;
    protected JButton buttonOpen;
    protected JButton buttonHelp;
    protected JButton buttonSave;
    protected JButton buttonHome;
    protected JButton buttonGroup;
    protected JButton buttonBirthday;
    protected JButton buttonDatabase;
    protected JButton buttonAdd;
    protected JButton buttonRemove;

    public AbToolBar() {
    	
        bar = new JToolBar();
        
        // NEW //
        buttonNew = new JButton();
        //ActionHelp doKey = new ActionKey();
        //keyButton.setAction(doKey);
        buttonNew.setIcon(new ImageIcon(this.getClass().getResource("/icons/Document.png")));
        buttonNew.setToolTipText("New profile");
        bar.add(buttonNew);
        
        // OPEN //
        buttonOpen = new JButton();
        //ActionHelp doKey = new ActionKey();
        //keyButton.setAction(doKey);
        buttonOpen.setIcon(new ImageIcon(this.getClass().getResource("/icons/Open file.png")));
        buttonOpen.setToolTipText("Open");
        bar.add(buttonOpen);

        // SAVE //
        buttonSave = new JButton();
        buttonSave.setAction(new ActionSaveProfile());
        buttonSave.setIcon(new ImageIcon(this.getClass().getResource("/icons/Save.png")));
        buttonSave.setToolTipText("Save");
        bar.add(buttonSave);
        
        // SEPARATOR //
        bar.addSeparator();
        
        ActionShowView doShowView = new ActionShowView();
        
        // HOME //
        buttonHome = new JButton();
        buttonHome.setAction(doShowView);
        buttonHome.setIcon(new ImageIcon(this.getClass().getResource("/icons/Home.png")));
        buttonHome.setToolTipText("Home");
        buttonHome.setMnemonic(AbCard.HOME);
        bar.add(buttonHome);
        
        // DATABASE //
        buttonDatabase = new JButton();
        buttonDatabase.setAction(doShowView);
        buttonDatabase.setIcon(new ImageIcon(this.getClass().getResource("/icons/Database.png")));
        buttonDatabase.setToolTipText("Database");
        buttonDatabase.setMnemonic(AbCard.DATABASE);
        bar.add(buttonDatabase);
        
        // GROUP //
        buttonGroup = new JButton();
        buttonGroup.setAction(doShowView);
        buttonGroup.setIcon(new ImageIcon(this.getClass().getResource("/icons/User group.png")));
        buttonGroup.setToolTipText("Groups");
        bar.add(buttonGroup);
        
        // BIRTHDAY //
        buttonBirthday = new JButton();
        buttonBirthday.setAction(doShowView);
        buttonBirthday.setIcon(new ImageIcon(this.getClass().getResource("/icons/Gift.png")));
        buttonBirthday.setToolTipText("Birthday");
        bar.add(buttonBirthday);
        
        // SEPARATOR //
        bar.addSeparator();
        
        // GROUP //
        buttonAdd = new JButton();
        buttonAdd.setAction(doShowView);
        buttonAdd.setIcon(new ImageIcon(this.getClass().getResource("/icons/Add.png")));
        buttonAdd.setToolTipText("Add contact");
        bar.add(buttonAdd);
        
        // BIRTHDAY //
        buttonRemove = new JButton();
        buttonRemove.setAction(doShowView);
        buttonRemove.setIcon(new ImageIcon(this.getClass().getResource("/icons/Delete.png")));
        buttonRemove.setToolTipText("Delete contact");
        bar.add(buttonRemove);
        
        // SEPARATOR //
        bar.addSeparator();
        
        // HELP //
        buttonHelp = new JButton();
        ActionHelp doHelp = new ActionHelp();
        buttonHelp.setAction(doHelp);
        buttonHelp.setIcon(new ImageIcon(this.getClass().getResource("/icons/Info.png")));
        buttonHelp.setToolTipText("Help");
        bar.add(buttonHelp);
        
        
    }

   public JComponent getWidget(){
        return bar;
    }

}
