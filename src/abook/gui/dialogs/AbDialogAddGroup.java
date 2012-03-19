package abook.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbProfile;
import abook.profile.InitProfile;


/**
 * 
 * @author Marek Mešár
 *
 */

public class AbDialogAddGroup extends JFrame{
	
	private AbProfile profile;
	private List<String> existingGroups;
	
    protected JPanel textPanel;
    protected JLabel label;
	protected JTextField groupNameTextField;
	protected ImageIcon validIcon;
	
    protected JPanel buttonPanel;
    protected JButton okeyButton;
    protected JButton cancelButton;

	
	/**
	 * creating dialog
	 * @return 
	 */
	public AbDialogAddGroup() {

		profile = InitProfile.getProfile();
		existingGroups = profile.n_getListOfGroupNames();
		
		// ---Layout handling--- 
		setLayout(new BorderLayout());
		Point middle = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int width = 300;
        int height = 100;
        setBounds((middle.x - width / 2), (middle.y - height / 2), width, height);
		
        
        // Input text panel //
        textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        
        label = new JLabel("Group name: ");
        label.setLabelFor(groupNameTextField);
        
        groupNameTextField = new JTextField();
        groupNameTextField.setBounds(30, 30,40, 40);
        groupNameTextField.setOpaque(true);
        groupNameTextField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// filters already existing group names
				HandleAddButtonEnabled(arg0);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
        
        //validIcon = new ImageIcon();
        //TODO validate image icon
        
        textPanel.add(label,BorderLayout.WEST);
        textPanel.add(groupNameTextField,BorderLayout.CENTER);
        
        
        // Button panel//
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        
        okeyButton = new JButton("    Add    ");
        okeyButton.setEnabled(false);
        okeyButton.setActionCommand("OK");
        okeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveNewGroup();
				setVisible(false);
			}
		});
        
        cancelButton  = new JButton(" Cancel ");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
        
        buttonPanel.add(okeyButton, BorderLayout.WEST);
        buttonPanel.add(cancelButton, BorderLayout.EAST);
        
        
        // add all elements to frame
        add(textPanel,BorderLayout.NORTH);
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
			
			if(! existingGroups.contains(text) && text.length()!= 0)
				okeyButton.setEnabled(true);
			else 
				okeyButton.setEnabled(false);
		}		
			
	}
	
	/** Saves new user group to profile
	 * 
	 */
	protected void SaveNewGroup() {
		
		//TODO remove old style adding group below
		profile.addGroup(groupNameTextField.getText());
		
		profile.n_addGroup(groupNameTextField.getText());
		InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.GROUPS_CHANGED);	
	}


}
