package abook.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import abook.AbIGuiComponent;
import abook.profile.InitProfile;

public class AbSideBar implements AbIGuiComponent {
	
	protected JPanel panel;
	protected JSplitPane splitPane;
	protected JScrollPane scrollPaneTree;
	protected JScrollPane scrollPaneList;
	protected JPanel panelForCheckBox;
	protected JPanel panelForSearch;
	protected JTree tree;
	protected JList list;
	protected JTextField textField;
	
	/**
	 * Constructor creates side-bar panel with tree.
	 */
	@SuppressWarnings("serial")
	public AbSideBar() {

        // set root nodes and his child nodes //
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("./workspace");
        try {
            createNodes(rootNode, new File("./workspace"));
            tree = new JTree(rootNode){
                public Insets getInsets()
                { return new Insets(5,5,5,5); }
            };
        } catch (NullPointerException npe) {
            //IjaTasks.hlaseni("Neni slozka examples");
            DefaultMutableTreeNode rootNode2 = new DefaultMutableTreeNode("ROOT");
            createNodes(rootNode2, new File("."));
            tree = new JTree(rootNode2){
                public Insets getInsets()
                { return new Insets(5,5,5,5); }
            };
            //tree.setBackground(InitProfile.getIjaVariables().getBarvaPozadi());
            //DoubleClick ml = new DoubleClick();
            //tree.addMouseListener(ml);
	    //treePanel.add(tree, BorderLayout.CENTER);
            //panel.setBackground(InitProfile.getIjaVariables().getBarvaPozadi());
            //return;
        }
        //tree.setBackground(InitProfile.getIjaVariables().getBarvaPozadi());
        
        //DoubleClick ml = new DoubleClick();
        //tree.addMouseListener(ml);

        // add tree to panel //
        //treePanel.add(tree, BorderLayout.WEST);
        //panel.setBackground(InitProfile.getIjaVariables().getBarvaPozadi());
        
        panelForCheckBox = new JPanel() {
            public Insets getInsets()
            { return new Insets(5,5,5,5); }
        };
        panelForCheckBox.setLayout(new BoxLayout(panelForCheckBox, BoxLayout.Y_AXIS));
        panelForCheckBox.setOpaque(false);
        
        for(String group : InitProfile.getProfile().getListOfGroups()) {
        	JCheckBox checkBox = new JCheckBox(group);
        	checkBox.setOpaque(false);
        	panelForCheckBox.add(checkBox);
        }        
        
        // create scroll panes //
        scrollPaneTree = new JScrollPane(tree);
        scrollPaneList = new JScrollPane(panelForCheckBox);
        
        // create main panel //
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, scrollPaneTree, scrollPaneList);
		splitPane.setDividerLocation(250);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		textField = new JTextField() {
            public Insets getInsets()
            { return new Insets(5,5,5,5); }
        };
		panelForSearch = new JPanel(new GridLayout());
		panelForSearch.add(textField);
		
		panel = new JPanel(new BorderLayout());
		panel.add(splitPane, BorderLayout.CENTER);
		panel.add(panelForSearch, BorderLayout.SOUTH);
	}
	
	/** Creates nodes. (recursive function)
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
    * TODO Inner class which open selected node (by double click)
    */
   /*class DoubleClick extends MouseAdapter {

       public DoubleClick() {
           super();
       }

       public void mousePressed(MouseEvent e) {
           int selRow = strom.getRowForLocation(e.getX(), e.getY());
           if(selRow != -1) {
               if(e.getClickCount() == 2) {
                   ActionAddTreeCard pridejKartu = new ActionAddTreeCard();
                   pridejKartu.actionPerformed(null);
               }
           }
       }
   }*/
   

   /** Return widget with tree
    *
    * @return panel
    */
   public JComponent getWidget() {
       return panel;
   }

}
