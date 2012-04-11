package abook.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import abook.gui.dialogs.AbDialogs;
import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbGroup;
import abook.profile.InitProfile;

/**
 * Left side bar which contains tree of files, list of groups and search text field.
 * 
 * @author jurij
 *
 */
public class AbSideBar implements AbIGuiComponent, AbListener, ActionListener {
	
	protected JPanel panel;
	protected JSplitPane splitPane;
	protected JScrollPane scrollPaneTree;
	protected JScrollPane scrollPaneList;
	protected JPanel panelForTree;
	protected JPanel panelForCheckBox;
	protected JPanel panelForSearch;
	protected JTree tree;
	protected JList list;
	protected JTextField textField;
	protected List<JCheckBox> listOfCheckBox;
	
	/**
	 * Constructor creates side-bar panel with tree.
	 */
	@SuppressWarnings("serial")
	public AbSideBar() {
		
		InitListenerCore.getListenerCore().addListener(this);
		
		String workspace = InitProfile.getWorkspace();
		
		// create TREE panel //
		tree = new JTree() {
			 public Insets getInsets()
			 { return new Insets(5,5,5,5); }
		};
        tree.addMouseListener(new DoubleClick());
        tree.setOpaque(false);
		fillTree(workspace);
		
		// create CHECK BOX panel //
		panelForCheckBox = new JPanel() {
			 public Insets getInsets()
			 { return new Insets(5,5,5,5); }
		};
		panelForCheckBox.setLayout(new BoxLayout(panelForCheckBox, BoxLayout.Y_AXIS));
		panelForCheckBox.setOpaque(false);
		
		listOfCheckBox = new ArrayList<JCheckBox>();		
		fillCheckBox();
		
		// create scroll panes //
		scrollPaneTree = new JScrollPane(tree);
		scrollPaneList = new JScrollPane(panelForCheckBox);
		
		// create main panel //
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, scrollPaneTree, scrollPaneList);
		splitPane.setDividerLocation(250);
		
		// create search //
		textField = new JTextField() {
			public Insets getInsets()
			{ return new Insets(5,5,5,5); }
		};
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				find();
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				find();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// do nothing //
			}
		});
		panelForSearch = new JPanel(new GridLayout());
		panelForSearch.add(textField);
		
		panel = new JPanel(new BorderLayout());
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(panelForSearch, BorderLayout.SOUTH);
	}
	
	/**
	 * Method fills check box panel with check buttons of groups from profile.
	 */
    private void fillCheckBox() {
		
		listOfCheckBox.clear();
		panelForCheckBox.removeAll();
		List<String> listOfSelectedGroups = InitProfile.getProfile().getListOfSelectedGroups();
		
		int i = 0;
		for(AbGroup group : InitProfile.getProfile().getListOfGroups()) {
			JCheckBox checkBox = new JCheckBox(group.getGroupName());
			checkBox.setOpaque(false);
			checkBox.setSelected(listOfSelectedGroups.contains(group.getGroupName()));
			checkBox.addActionListener(this);
			panelForCheckBox.add(checkBox);
			listOfCheckBox.add(checkBox);
			i++;
		}
		
		panelForCheckBox.repaint();
	}
	
	/**
	 * Method fills tree with nodes of profiles from profile.
	 * 
	 * @param workspace
	 */
	private void fillTree(String workspace) {
		
		// set root nodes and his child nodes //
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(workspace);
        try {
            createNodes(rootNode, new File(workspace));
        } catch (NullPointerException npe) {
            //IjaTasks.hlaseni("Neni slozka examples");
            rootNode = new DefaultMutableTreeNode("ROOT");
            createNodes(rootNode, new File("."));
        }
        
        tree.setModel(new DefaultTreeModel(rootNode));
        tree.repaint();
	}

	/**
	 * Creates nodes. (recursive function)
	 *
	 * @param korenovyUzel
	 * @param korenovySoubor
	 */
	private void createNodes(DefaultMutableTreeNode korenovyUzel, File korenovySoubor) {
		File[] soubory = korenovySoubor.listFiles();
		
		// add child nodes to root node //
		for (int i = 0; i < soubory.length; i++)
		{
			DefaultMutableTreeNode potomek = new DefaultMutableTreeNode(soubory[i].getName());
			korenovyUzel.add(potomek);
		         
			// repeat with child nodes //
			if(soubory[i].isDirectory())
			{
				createNodes(potomek, soubory[i]);
			}
		}
	}
	
	/**
	 * Inner class opens selected node (by double click)
	 */
	class DoubleClick extends MouseAdapter {

		public DoubleClick() {
			super();
		}
		
		public void mousePressed(MouseEvent e) {
			int selRow = tree.getRowForLocation(e.getX(), e.getY());
			if(selRow != -1) {
				if(e.getClickCount() == 2) {
					//System.out.println(tree.getSelectionPath());
					TreePath selection = tree.getSelectionPath();
					if(selection.getPathCount() == 2) {
						String selectedComponent = selection.getLastPathComponent().toString();
						if(selectedComponent.endsWith(".xml")) {
							
							// save actual profile //
							int result = AbDialogs.YesNoCancel("Do you want to save actual project?");
							
							if(result == JOptionPane.CANCEL_OPTION) {
								return;
							}
							
							if(result == JOptionPane.OK_OPTION) {
								InitProfile.saveProfile();
							}
							
							File file = new File(InitProfile.getWorkspace() + "/" + selectedComponent);
							
							ViewGui.getAbTabLine().closeAllTabs();
							
							System.out.println("open");
							
							InitProfile.openProfile(file);
							ViewGui.getAbTabLine().restoreTabs();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Method change text and invokes listener.
	 */
	private void find() {
		InitProfile.getProfile().setSearchText(textField.getText());
		
		InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.SEARCH_CHANGED);
	}

	/** Return widget with tree
	 * 
	 * @return panel
	 */
	public JComponent getWidget() {
		return panel;
	}

	@Override
	public void myEventOccurred(AbEvent evt, int type) {
		
		if(type == AbListener.WORKSPACE_STRUCT_CHANGED) {
			
			fillTree(InitProfile.getWorkspace());

		} else if(type == AbListener.PROFILE_CHANGED || type == AbListener.GROUPS_CHANGED) {
			
			fillCheckBox();
			splitPane.setDividerLocation(splitPane.getDividerLocation()); // repaint() didn't work, but this works fine
		}
		
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		
		List<String> listOfSelectedGroups = InitProfile.getProfile().getListOfSelectedGroups();
		listOfSelectedGroups.clear();
			
		int i = 0;
		for(JCheckBox box : listOfCheckBox) {
			if(box.isSelected()) {
				listOfSelectedGroups.add(box.getText());
			}
			i++;
		}
		
		InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.GROUP_SELECTION_CHANGED);
    }
}
