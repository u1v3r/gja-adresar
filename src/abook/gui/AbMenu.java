package abook.gui;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import abook.profile.AbCard;

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
        
        // -- New profile //
        JMenuItem menuNewProfile = fileMenu.add("New");
        menuNewProfile.addActionListener(actions.getActionNewProfile());
        
        // -- Save //
        JMenuItem menuSave = fileMenu.add("Save");
        menuSave.setMnemonic(0);
        menuSave.addActionListener(actions.getActionSaveProfile());

        // -- Save as //
        JMenuItem menuSaveAs = fileMenu.add("Save as");
        menuSaveAs.setMnemonic(1);
        menuSaveAs.addActionListener(actions.getActionSaveProfile());
        
        // -- Exit //
        JMenuItem menuExitFile = fileMenu.add("Exit");
        menuExitFile.addActionListener(actions.getActionExitProgram());
        menuBar.add(fileMenu);
        
        // 2.) VIEW //
        JMenu viewMenu = new JMenu("View");

        // -- Home //
        JMenuItem menuShowHome = viewMenu.add("Home");
        menuShowHome.setMnemonic(AbCard.HOME);
        menuShowHome.addActionListener(actions.getActionShowView());
        
        // -- Database //
        JMenuItem menuShowDatabase = viewMenu.add("Database");
        menuShowDatabase.setMnemonic(AbCard.DATABASE);
        menuShowDatabase.addActionListener(actions.getActionShowView());
        
        // -- Groups //
        JMenuItem menuShowGroups = viewMenu.add("Groups");
        menuShowGroups.setMnemonic(AbCard.GROUPS);
        menuShowGroups.addActionListener(actions.getActionShowView());
        
        // -- Events //
        JMenuItem menuShowEvents = viewMenu.add("Events");
        menuShowEvents.setMnemonic(AbCard.EVENTS);
        menuShowEvents.addActionListener(actions.getActionShowView());
        
        menuBar.add(viewMenu);

        // 3.) HELP //
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // a) Help //
        JMenuItem menuHelp = helpMenu.add("Help");
        menuHelp.addActionListener(actions.getActionHelp());

        // TODO ... 
    }

    public JComponent getWidget() {
        return menuBar;
    }
}
