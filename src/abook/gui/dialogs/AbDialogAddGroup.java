package abook.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbProfile;
import abook.profile.InitProfile;


/**
 * 
 * @author xmesar00
 *
 */

@SuppressWarnings("serial")
public class AbDialogAddGroup extends JDialog{
	
	private AbProfile profile;
	
    protected JPanel textPanel;
    protected JLabel label;
   
	protected JTextField groupNameTextField;

	//protected ImageIcon validIcon;
	
	protected JPanel DescrPanel;
	protected JLabel label2;
	protected JTextArea groupDescriptionTxtArea;
	private JScrollPane scrollBar1; // Scroll pane for text area
	
    protected JPanel buttonPanel;
    protected JButton okeyButton;
    protected JButton cancelButton;

	
	/**
	 * creating dialog
	 * @return 
	 */
	public AbDialogAddGroup() {

		profile = InitProfile.getProfile();
		
		// ---Layout handling--- 
		setLayout(new BorderLayout());
		Point middle = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 330;
        int height = 220;
        setBounds((middle.x - width / 2), (middle.y - height / 2), width, height);
		
        
        // Input text panel //
        textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        
        label = new JLabel("* Group name: ");
        label.setLabelFor(groupNameTextField);
        
        groupNameTextField = new JTextField();
        groupNameTextField.setBounds(30, 30,40, 40);
        groupNameTextField.setOpaque(true);
        groupNameTextField.createToolTip();
        groupNameTextField.setToolTipText("Group name. (Required)");
        groupNameTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// do nothing //
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// filters already existing group names
				HandleAddButtonEnabled(arg0);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// do nothing //
			}
		});
        
        textPanel.add(label,BorderLayout.WEST);
        textPanel.add(groupNameTextField,BorderLayout.CENTER);
        
        // Description text area //
        DescrPanel = new JPanel(new BorderLayout());
        DescrPanel.setOpaque(false);
        
        label2 = new JLabel("  Description:   ");
        label2.setLabelFor(groupDescriptionTxtArea);
        
        groupDescriptionTxtArea = new JTextArea();
        groupDescriptionTxtArea.setRows(5);
		groupDescriptionTxtArea.setColumns(20);
        groupDescriptionTxtArea.setWrapStyleWord(true);
        groupDescriptionTxtArea.setLineWrap(true);
        groupDescriptionTxtArea.createToolTip();
        groupDescriptionTxtArea.setToolTipText("Description of the contact group. (Optional)");
        scrollBar1 = new JScrollPane(groupDescriptionTxtArea);
        scrollBar1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        DescrPanel.add(label2,BorderLayout.WEST);
        DescrPanel.add(scrollBar1, BorderLayout.CENTER);
 
        
        // Button panel//
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        
        okeyButton = new JButton("    Add    ");
        okeyButton.setEnabled(false);
        okeyButton.setActionCommand("OK");
        okeyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveNewGroup();
				setVisible(false);
			}
		});
        
        cancelButton  = new JButton(" Cancel ");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
        
        buttonPanel.add(okeyButton, BorderLayout.WEST);
        buttonPanel.add(cancelButton, BorderLayout.EAST);
        
        
        // add all elements to frame
        add(textPanel,BorderLayout.NORTH);
        add(DescrPanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        setVisible(true);
        groupNameTextField.requestFocus();
	}
	
	/** Key event listener for new group name text field. Handles filtering of existing group names by disabling Add button.
	 * 
	 * @param key = key pressed
	 */
	private void HandleAddButtonEnabled(KeyEvent key)
	{	
		
		if(key.getKeyCode() == KeyEvent.VK_ENTER && okeyButton.isEnabled())
		{
			okeyButton.doClick();
		}else
		{			
			String text = groupNameTextField.getText();
			
			if(! profile.containsGroup(text) && text.length()!= 0)
				okeyButton.setEnabled(true);
			else 
				okeyButton.setEnabled(false);
		}		
			
	}
	
	/** Saves new user group to profile
	 * 
	 */
	protected void SaveNewGroup() {
		
		profile.addGroup(groupNameTextField.getText(),groupDescriptionTxtArea.getText());		
		InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.GROUPS_CHANGED);	
	}


}
