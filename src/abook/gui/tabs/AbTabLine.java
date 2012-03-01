package abook.gui.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import abook.AbIGuiComponent;
import abook.AbIGuiTabComponent;
import abook.gui.table.AbTable;
import abook.profile.AbCard;
import abook.profile.InitProfile;

public class AbTabLine implements AbIGuiComponent, MouseListener {
	
	protected JTabbedPane tabbedPane;
	protected JPanel tabAdd;
	protected AbTabHome tabHome;
	protected AbTabDatabase tabDatabase;
	protected Color tabBackgroud;
	protected ImageIcon iconClose;
	protected int tabCounter;
	
	/**
	 * Creates list of cards
	 */
	public AbTabLine() {
		
		// initialization of local variables //
		inicializeVariables();
		
		// crate main widget - root for tabs //
		tabbedPane = new JTabbedPane();
		
		// create special tabs specified by profile //
		createTabs();
		
		// open tabs //
		initTabs();
		
	}
	
	/**
	 * Method initializes local variables.
	 */
	private void inicializeVariables() {
		tabBackgroud = new Color(0, true);
		iconClose = new ImageIcon(this.getClass().getResource("/icons/gtk-close.png"));
		tabCounter = 1;
	}
	
	/**
	 * Method creates special views.
	 */
	private void createTabs() {
		
		// special views //
		tabHome = new AbTabHome();
		tabDatabase = new AbTabDatabase();
		
		// tab which adds new tab //
		tabAdd = new JPanel();
		tabAdd.setLayout(new BoxLayout(tabAdd, BoxLayout.Y_AXIS));
		tabbedPane.addTab(null, new ImageIcon(this.getClass().getResource("/icons/btn-plus.png")), tabAdd);
		tabbedPane.addMouseListener(this);
	}
	
	/**
	 * Method opens tabs selected by profile. 
	 */
	private void initTabs() {
		List<AbCard> list = InitProfile.getProfile().getListOfAbCards();
		int index = 0;
		
		for(AbCard card : list) {
			
			AbIGuiTabComponent component = this.getTab(card.getType());
			this.addTabToLine(component, index);
			index++;
		}
	}
	
	/**
	 * Method return existing tab or create new tab if default selected.
	 * 
	 * @param type
	 * @return tab
	 */
	protected AbIGuiTabComponent getTab(int type) {
		
		AbIGuiTabComponent component;
		
		switch (type) {
			case AbCard.HOME:
			
				component = tabHome;
				break;
		
			case AbCard.DATABASE:
			
				component = tabDatabase;
				break;

			default:
				// TODO creates new tab component
				component = null;
				break;
		}
		
		return component;
	}
	
	/**
	 * Method adds new tab with selected component to selected index.
	 * 
	 * @param component
	 * @param index
	 */
	public void addTabToLine(AbIGuiTabComponent component, int index) {
		
		tabbedPane.insertTab(component.getName(), null, 
                component.getWidget(), component.getTooltip(), index);
		tabbedPane.setSelectedIndex(index);
		tabbedPane.setTabComponentAt(index,
                new AbTabCloseButton());
		tabCounter++;
	}
	
	public void addTabToLine(AbIGuiTabComponent component) {
		addTabToLine(component, tabbedPane.getTabCount()-1);
	}
	
	/**
	 * Method adds new default tab.
	 */
	public void addTabToLine() {
		JScrollPane panel = new JScrollPane(new AbTable().getWidget());
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
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
	 * Method removes specific tab.
	 * 
	 * @param index
	 */
	public void removeTabFromLine(int index) {
		
		int count = tabbedPane.getTabCount();
		//AbIGuiTabComponent component = (AbIGuiTabComponent) tabbedPane.getComponentAt(i);
		
		if(index < count && index >= 0) {
			if(index == count-2) {
				tabbedPane.setSelectedIndex(index-1);
			}
			tabbedPane.remove(index);
		}
		
		InitProfile.getProfile().removeAbICard(index);
	}
	
	/**
	 * Method removes last tab.
	 */
	public void removeTabFromLine() {
		removeTabFromLine(tabbedPane.getTabCount()-2);
	}
	
	
	public void openTab(int type) {
		
		int index = InitProfile.getProfile().getIndexOfCard(type);
		
		if(index == -1) {
			InitProfile.getProfile().addSpecialCard(type);
			AbIGuiTabComponent component = getTab(type);
			this.addTabToLine(component);
		} else {
			tabbedPane.setSelectedIndex(index);
		}
	}
	
	/**
	 * Method returns main widget (JScrollPane)
	 */
	public JComponent getWidget() {
		return tabbedPane;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1
				&& tabbedPane.getSelectedComponent() == tabAdd) {
			addTabToLine();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// do nothing //
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// do nothing //
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// do nothing //
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// do nothing //
	}
	

	
	// ----------------------------------------------------------------------------------//
	
	
	
	
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
			removeTabFromLine(tabbedPane.indexOfTabComponent(AbTabCloseButton.this));
		}
		
	}

}
