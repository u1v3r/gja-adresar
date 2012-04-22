package abook.gui;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import abook.profile.AbCard;

/**
 * Menu with action buttons.
 * 
 * @author jurij
 *
 */
public class AbMenu extends AbActions implements AbIGuiComponent {

	private JMenuBar menuBar;

    /**
     * Constructor initializes JMenuBar with items
     */
    public AbMenu() {

        menuBar = new JMenuBar();
        //menuBar.setBackground(Color.GRAY);
        
        AbActions actions = ViewGui.getActions();

        // 1.) FILE //
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        // -- New profile //
        JMenuItem menuNewProfile = fileMenu.add("New");
        menuNewProfile.setMnemonic(KeyEvent.VK_N);
        menuNewProfile.addActionListener(actions.getActionNewProfile());
        
        // -- Save //
        JMenuItem menuSave = fileMenu.add("Save");
        menuSave.setMnemonic(KeyEvent.VK_S);
        menuSave.addActionListener(actions.getActionSaveProfile());

        // -- Save as //
        JMenuItem menuSaveAs = fileMenu.add("Save as");
        menuSaveAs.setMnemonic(KeyEvent.VK_A);
        menuSaveAs.addActionListener(actions.getActionSaveProfile());
        
        // -- Exit //
        JMenuItem menuExitFile = fileMenu.add("Exit");
        menuExitFile.addActionListener(actions.getActionExitProgram());
        menuExitFile.setMnemonic(KeyEvent.VK_X);
        menuBar.add(fileMenu);
        
        // 2.) VIEW //
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);

        // -- Home //
        JMenuItem menuShowHome = viewMenu.add("Home");
        menuShowHome.setMnemonic(AbCard.HOME);
        menuShowHome.addActionListener(actions.getActionShowView());
        
        // -- Database //
        JMenuItem menuShowDatabase = viewMenu.add("Database");
        menuShowDatabase.setMnemonic(AbCard.DATABASE);
        menuShowDatabase.addActionListener(actions.getActionShowView());
        
        // -- Details //
        JMenuItem menuShowDetails = viewMenu.add("Details");
        menuShowDetails.setMnemonic(AbCard.DETAILS);
        menuShowDetails.addActionListener(actions.getActionShowView());
        
        // -- Groups //
        JMenuItem menuShowGroups = viewMenu.add("Groups");
        menuShowGroups.setMnemonic(AbCard.GROUPS);
        menuShowGroups.addActionListener(actions.getActionShowView());
        
        // -- Events //
        JMenuItem menuShowEvents = viewMenu.add("Events");
        menuShowEvents.setMnemonic(AbCard.EVENTS);
        menuShowEvents.addActionListener(actions.getActionShowView());
        
        menuBar.add(viewMenu);
        
        // 3.) EDIT //
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(editMenu);

        // a) Add contact //
        JMenuItem menuAddContact = editMenu.add("Add contact");
        menuAddContact.setMnemonic(KeyEvent.VK_PLUS);
        menuAddContact.addActionListener(actions.getActionAddContact());
        
        // b) Help //
        JMenuItem menuRemoveContact = editMenu.add("Delete contact");
        menuRemoveContact.setMnemonic(KeyEvent.VK_PLUS);
        menuRemoveContact.addActionListener(actions.getActionDeleteContact());
        
        editMenu.addSeparator();

        // c) Add contact //
        JMenuItem menuAddGroup = editMenu.add("Add group");
        //menuAddGroup.setMnemonic(KeyEvent.VK_A);
        menuAddGroup.addActionListener(actions.getActionAddGroup());
        
        // b) Help //
        JMenuItem menuRemoveGroup = editMenu.add("Delete group");
        //menuRemoveGroup.setMnemonic(KeyEvent.VK_R);
        menuRemoveGroup.addActionListener(actions.getActionDeleteGroup());

        // 6.) HELP //
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);

        // a) Help //
        JMenuItem menuHelp = helpMenu.add("Help");
        menuHelp.setMnemonic(KeyEvent.VK_H);
        menuHelp.addActionListener(actions.getActionHelp());

        // TODO ... 
    }

    @Override
    public JComponent getWidget() {
        return menuBar;
    }
}
