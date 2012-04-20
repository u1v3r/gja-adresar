package abook.gui.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import abook.gui.AbIGuiComponent;
import abook.gui.dialogs.AbDialogs;
import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbCard;
import abook.profile.InitProfile;

/**
 * Main panel for all tab lines.
 * 
 * @author jurij
 *
 */
public class AbTabLine implements AbIGuiComponent, AbListener {
	
	protected JTabbedPane tabbedPane;
	protected JPanel tabAdd;
	protected AbTabHome tabHome;
	protected AbTabDatabase tabDatabase;
	protected AbTabGroups tabGroups;
	protected AbTabEvents tabEvents;
	protected AbTabContactDetails tabDetails;
	protected List<AbITabComponent> listOfTabs;
	protected Color tabBackgroud;
	protected ImageIcon iconClose;
	protected int tabCounter;
	protected final String[] views = { "Home", "Database", "Groups", "Events","Details" };
	
	/**
	 * Creates list of cards
	 */
	public AbTabLine() {
		
		InitListenerCore.getListenerCore().addListener(this);
		
		// initialization of local variables //
		inicializeVariables();
		
		// crate main widget - root for tabs //
		tabbedPane = new JTabbedPane();
		
		// create all views //
		createTabs();
		
		// creates add tab //
		createAddTab();
		
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
		
		listOfTabs = new ArrayList<AbITabComponent>();
		
		// special views //
		tabHome = new AbTabHome();
		tabDatabase = new AbTabDatabase();
		tabGroups = new AbTabGroups();
		tabEvents = new AbTabEvents();
		tabDetails = new AbTabContactDetails();
	}
	
	/**
	 * Creates add tab button.
	 */
	private void createAddTab() {
		
		// tab which adds new tab //
		tabAdd = new JPanel();
		tabAdd.setLayout(new BoxLayout(tabAdd, BoxLayout.Y_AXIS));
		tabAdd.addMouseListener(new MouseClick());
		tabbedPane.addTab(null, new ImageIcon(this.getClass().getResource("/icons/btn-plus.png")), tabAdd);
		tabbedPane.addMouseListener(new MouseClick());
	}
	
	/**
	 * Method opens tabs selected by profile. 
	 */
	private void initTabs() {
		List<AbCard> list = InitProfile.getProfile().getListOfAbCards();
		int index = 0;
		
		for(AbCard card : list) {
			
			AbITabComponent component = this.getTab(card.getType());
			this.addTabToLine(component, index);
			index++;
		}
		
		tabbedPane.setSelectedIndex(InitProfile.getProfile().getOpenedTab());
	}
	
	/**
	 * Method return existing tab or create new tab if default selected.
	 * 
	 * @param type
	 * @return tab
	 */
	private AbITabComponent getTab(int type) {
		
		AbITabComponent component;
		
		switch (type) {
			case AbCard.HOME:
			
				component = tabHome;
				break;
		
			case AbCard.DATABASE:
			
				component = tabDatabase;
				break;
				
			case AbCard.GROUPS:
				
				component = tabGroups;
				break;
				
			case AbCard.EVENTS:
				
				component = tabEvents;
				break;
			case AbCard.DETAILS:
				
				component = tabDetails;
				break;
			default:
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
	public void addTabToLine(AbITabComponent component, int index) {
		
		tabbedPane.insertTab(component.getName(), null, 
                component.getWidget(), component.getTooltip(), index);
		tabbedPane.setSelectedIndex(index);
		tabbedPane.setTabComponentAt(index,
                new AbTabCloseButton());
		tabCounter++;
	}
	
	public void addTabToLine(AbITabComponent component) {
		addTabToLine(component, tabbedPane.getTabCount()-1);
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
				InitProfile.getProfile().setOpenedTab(index-1);
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
			AbITabComponent component = getTab(type);
			this.addTabToLine(component);
			index = InitProfile.getProfile().getIndexOfCard(type);
		} else {
			tabbedPane.setSelectedIndex(index);
			InitProfile.getProfile().setOpenedTab(index);
		}
		
		InitProfile.getProfile().setOpenedTab(index);
		getTab(InitProfile.getProfile().getListOfAbCards().get(index).getType()).actualizeTab();
	}
	
	public void closeAllTabs() {
		// remove open tabs //
		int count = tabbedPane.getTabCount();
		for(int i = 0; i < (count-1); i++) {
			removeTabFromLine();
		}
	}
	
	/**
	 * Restore tabs to default
	 */
	public void restoreTabs() {
		
		// create new tabs //
		createTabs();
		
		// initialize tabs //
		initTabs();
	}
	
	/**
	 * Method returns main widget (JScrollPane)
	 */
	public JComponent getWidget() {
		return tabbedPane;
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
	
	/**
	 * Inner class for plus button.
	 * 
	 * @author jurij
	 *
	 */
    class MouseClick extends MouseAdapter {

        public MouseClick() {
            super();
        }

        public void mousePressed(MouseEvent e) {
        	if(tabbedPane.getSelectedComponent() == tabAdd) {
        		if(e.getClickCount() == 1) {
        			
            		int type = AbDialogs.select(views, "Zvolte pohled");
            		if(type < 0) {
            			
            		} else {
            			openTab(type);
            		}
            	}
        	} else {
        		int selectedIndex = tabbedPane.getSelectedIndex();
        		if(InitProfile.getProfile().getOpenedTab() != selectedIndex) {
        			//InitProfile.getProfile().setOpenedTab(selectedIndex);
        			//getTab(InitProfile.getProfile().getListOfAbCards().get(selectedIndex).getType()).actualizeTab();
        			openTab(InitProfile.getProfile().getListOfAbCards().get(selectedIndex).getType());
        			//System.out.println("actualize!");
        		}
        	}

        }
    }

	@Override
    public void myEventOccurred(AbEvent evt, int type) {
		if(type == GROUP_SELECTION_CHANGED || type == SEARCH_CHANGED) {
			
			//System.out.println("actualize");
			
			getTab(InitProfile.getProfile().getListOfAbCards().get(tabbedPane.getSelectedIndex()).getType()).actualizeTab();
	    }
    }

}
