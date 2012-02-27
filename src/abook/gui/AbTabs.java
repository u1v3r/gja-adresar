package abook.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicButtonUI;

import abook.AbIGuiComponent;
import abook.others.ButtonTabComponent;

public class AbTabs implements AbIGuiComponent, ComponentListener {
	
	protected JTabbedPane tabbedPane;
	protected JPanel panelHome;
	protected JPanel panelAdd;
	protected Color tabBackgroud;
	protected ImageIcon iconClose;
	protected int tabCounter;
	
	/**
	 * Creates list of cards
	 */
	public AbTabs() {
		
		tabBackgroud = new Color(0, true);
		iconClose = new ImageIcon(this.getClass().getResource("/icons/gtk-close.png"));
		tabCounter = 1;
		
		tabbedPane = new JTabbedPane();
		
		panelHome = new JPanel();
		panelHome.setLayout(new BoxLayout(panelHome, BoxLayout.Y_AXIS));
		tabbedPane.addTab("Home", panelHome);
		
		panelAdd = new JPanel();
		panelAdd.setLayout(new BoxLayout(panelAdd, BoxLayout.Y_AXIS));
		panelAdd.addComponentListener(this);
		tabbedPane.addTab(null, new ImageIcon(this.getClass().getResource("/icons/btn-plus.png")), panelAdd);
		
	}
	
	public Component getWidget() {
		return tabbedPane;
	}
	
	/**
	 * Adds new tab.
	 */
	public void addTab() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//tabbedPane.addTab(new String("Tab number" + tabbedPane.getTabCount()), panel);
		int index = tabbedPane.getTabCount()-1;
		//tabbedPane.add(panel, index);
		//tabbedPane.setSelectedIndex(index);
		tabbedPane.insertTab("Tab" + tabCounter, null, 
                panel, "nový panel", index);
		tabbedPane.setSelectedIndex(index);
		tabbedPane.setTabComponentAt(index,
                new AbTabCloseButton());
		tabCounter++;
	}
	
	/**
	 * Removes specific tab
	 * 
	 * @param i
	 */
	public void removeTab(int i) {
		
		int count = tabbedPane.getTabCount();
		
		if(i < count && i > 0) {
			if(i == count-2) {
				tabbedPane.setSelectedIndex(i-1);
			}
			tabbedPane.remove(i);
		}
	}
	
	/**
	 * Removes last tab.
	 */
	public void removeTab() {
		removeTab(tabbedPane.getTabCount()-1);
	}	
	
	/**
	 * 
	 * @author jurij
	 * 
	 * Idea is from this tutorial: http://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
	 *
	 */
	@SuppressWarnings("serial")
	private class AbTabCloseButton extends JPanel implements ActionListener {
		
		public AbTabCloseButton() {
			
			super(new FlowLayout(FlowLayout.LEFT, 0, 0));
			setOpaque(false);
			
			//make JLabel read titles from JTabbedPane
	        JLabel label = new JLabel() {
	            public String getText() {
	                int i = tabbedPane.indexOfTabComponent(AbTabCloseButton.this);
	                if (i != -1) {
	                    return tabbedPane.getTitleAt(i);
	                }
	                return null;
	            }
	        };
	         
	        add(label);
	        // add more space between the label and the button //
	        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
	        //tab button
	        JButton button = new JButton();
	        button.setIcon(iconClose);
	        
	        int size = 14;
            button.setPreferredSize(new Dimension(size, size));
            button.setToolTipText("close this tab");
            button.setOpaque(true);
            button.setFocusable(false);
            
            // transparent //
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createEtchedBorder());
            button.setBorderPainted(false);
            button.addActionListener(this);
	        add(button);
	        
	        // add more space above the button //
	        button.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			removeTab(tabbedPane.indexOfTabComponent(AbTabCloseButton.this));
		}
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// do nothing //
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// do nothing //
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// do nothing //
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		if(tabbedPane.getSelectedComponent() == panelAdd) {
			addTab();
		}
	}

}
