package abook.gui;

import java.awt.Component;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import abook.AbIGuiComponent;

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

        // 2.) HELP //
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // a) Help //
        JMenuItem menuHelp = helpMenu.add("Help");
        ActionHelp doHelp = new ActionHelp();
        menuHelp.addActionListener(doHelp);

        // TODO ... 
    }

    public Component getWidget() {
        return menuBar;
    }
}
