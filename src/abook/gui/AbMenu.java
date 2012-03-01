package abook.gui;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import abook.AbIGuiComponent;
import abook.profile.AbCard;

public class AbMenu extends AbActions implements AbIGuiComponent {

	private JMenuBar menuBar;

    /**
     * Constructor initializes JMenuBar with items
     */
    public AbMenu() {

        menuBar = new JMenuBar();
        //menuBar.setBackground(Color.GRAY);

        // 1.) FILE //
        JMenu souborMenu = new JMenu("File");

        // -- Exit //
        JMenuItem menuExitFile = souborMenu.add("Exit");
        ActionExitProgram doExitProgram = new ActionExitProgram();
        menuExitFile.addActionListener(doExitProgram);
        menuBar.add(souborMenu);
        
        // 2.) VIEW //
        JMenu viewMenu = new JMenu("View");
        ActionShowView doShowView = new ActionShowView();

        // -- Home //
        JMenuItem menuShowHome = viewMenu.add("Home");
        menuShowHome.setMnemonic(AbCard.HOME);
        menuShowHome.addActionListener(doShowView);
        
        // -- Home //
        JMenuItem menuShowDatabase = viewMenu.add("Database");
        menuShowDatabase.setMnemonic(AbCard.DATABASE);
        menuShowDatabase.addActionListener(doShowView);
        
        menuBar.add(viewMenu);

        // 3.) HELP //
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // a) Help //
        JMenuItem menuHelp = helpMenu.add("Help");
        ActionHelp doHelp = new ActionHelp();
        menuHelp.addActionListener(doHelp);

        // TODO ... 
    }

    public JComponent getWidget() {
        return menuBar;
    }
}
