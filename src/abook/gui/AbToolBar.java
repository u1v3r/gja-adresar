package abook.gui;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToolBar;

import abook.profile.AbCard;

/**
 * Tool bar GUI class with icon buttons for basic user actions. 
 * 
 * @author jurij
 * TODO mnemonics
 *
 */
public class AbToolBar extends AbActions implements AbIGuiComponent {
	
	protected JToolBar bar;
	protected JButton buttonNew;
    protected JButton buttonOpen;
    protected JButton buttonHelp;
    protected JButton buttonSave;
    protected JButton buttonSaveAs;
    protected JButton buttonHome;
    protected JButton buttonGroup;
    protected JButton buttonBirthday;
    protected JButton buttonDatabase;
    protected JButton buttonContactDetail;
    protected JButton buttonAdd;
    protected JButton buttonRemove;
    protected JButton buttonContactsSync;

    /**
     * Creates new toolbar.
     */
    public AbToolBar() {
    	
        bar = new JToolBar();
        
        AbActions actions = ViewGui.getActions();
        
        // PROFILE //
        addButton("New profile", "/icons/Document.png", actions.getActionNewProfile(), 0);
        addButton("Open profile", "/icons/Open file.png", actions.getActionOpenProfile(), 0);
        addButton("Save", "/icons/Save.png", actions.getActionSaveProfile(), 0);
        
        // SEPARATOR //
        bar.addSeparator();
        
        addButton("Import", "/icons/flash1.png", actions.getActionSaveProfile(), 1);
        addButton("Export", "/icons/flash2.png", actions.getActionSaveProfile(), 1);
        
        // SEPARATOR //
        bar.addSeparator();
        
        // VIEWS //
        addButton("Home", "/icons/Home.png", actions.getActionShowView(), AbCard.HOME);
        addButton("Database", "/icons/Database.png", actions.getActionShowView(), AbCard.DATABASE);
        addButton("Details", "/icons/Clien list.png", actions.getActionShowView(), AbCard.DETAILS);
        addButton("Groups", "/icons/User group.png", actions.getActionShowView(), AbCard.GROUPS);
        addButton("Birthday", "/icons/Gift.png", actions.getActionShowView(), AbCard.EVENTS);
        
        // SEPARATOR //
        bar.addSeparator();
        
        // ADD/REMOVE CONTACT //
        addButton("Add contact", "/icons/Add.png", actions.getActionAddContact(), 0);
        addButton("Delete contact", "/icons/Delete.png", actions.getActionDeleteContact(), 0);
        
        // SEPARATOR //
        bar.addSeparator();
        
        // ADD/REMOVE GROUP //
        addButton("Add group", "/icons/Add.png", actions.getActionAddGroup(), 0);
        addButton("Delete group", "/icons/Delete.png", actions.getActionDeleteGroup(), 0);
        
        // SEPARATOR //
        bar.addSeparator();
        addButton("Import google contacts", "/icons/Google-icon.png", actions.getActionSyncContacts(), 0);
        
        // SEPARATOR //
        bar.addSeparator();
        
        // SYSTEM //
        addButton("Help", "/icons/Info.png", actions.getActionHelp(), 0);
    }
    
    /**
     * Adds new button at the of the tool bar.
     * 
     * @param tooltip
     * @param iconPath
     * @param action
     * @param mnemonic
     */
	private void addButton(String tooltip, String iconPath, Action action, int mnemonic) {
    	
    	JButton button = new JButton();
    	button.setAction(action);
    	button.setIcon(new ImageIcon(this.getClass().getResource(iconPath)));
    	button.setToolTipText(tooltip);
    	button.setMnemonic(mnemonic);
    	bar.add(button);
    }
    
	@Override
    public JComponent getWidget(){
        return bar;
    }

}
