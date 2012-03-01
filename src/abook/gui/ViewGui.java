package abook.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;

import abook.gui.tabs.AbTabLine;


public class ViewGui {
	
	protected static JFrame frame;
	protected AbMenu menu;
	protected AbToolBar toolbar;
	protected AbSideBar sidebar;
	protected AbStatus statebar;
	protected static AbTabLine tabbedArea;
	protected JSplitPane mainField;
	
	public void launchAppliacation() {
		
		// creates new Frame //
		frame = new JFrame("aBook | Projekt GJA 2012");
		
		// set position (in the middle of screen) //
		Toolkit nastroje = frame.getToolkit();
		Dimension screenSize = nastroje.getScreenSize();
		frame.setBounds((screenSize.width/2-450), screenSize.height/2-300, 900, 600);
		
		// 1. MENU //
        menu = new AbMenu();
        frame.setJMenuBar((JMenuBar) menu.getWidget());
        
        // 2. TOOLBAR //
        toolbar = new AbToolBar();
        frame.getContentPane().add(toolbar.getWidget(), BorderLayout.NORTH);
        
        // 4a. SIDE BAR //
        sidebar = new AbSideBar();
        
        // 4b. TABBED AREA //
        tabbedArea = new AbTabLine();
        
        // 4. MAIN FIELD //
        mainField = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, sidebar.getWidget(), tabbedArea.getWidget());
        mainField.setDividerLocation(200);
        mainField.setOneTouchExpandable(true);
        
        frame.getContentPane().add(mainField, BorderLayout.CENTER);
        
        // 3. STATE BAR //
        statebar = new AbStatus();
        frame.getContentPane().add(statebar.getWidget(), BorderLayout.SOUTH);
        
        // ... //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	public static AbTabLine getAbTabLine() {
		
		return tabbedArea;
	}

}
