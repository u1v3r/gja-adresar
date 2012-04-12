package abook.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xml.internal.security.Init;

import abook.profile.AbPerson;
import abook.profile.AbProfile;
import abook.profile.GoogleSync;
import abook.profile.InitProfile;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;

/**
 * Google contacts import dialog
 * 
 * @author Radovan Dvorsky
 *
 */
public class AbDialogGoogleSync extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField loginTextField;
	private JPasswordField pwdTextField;
	private JLabel loadingLabel;

	/**
	 * Create the dialog.
	 */
	public AbDialogGoogleSync() {
		setBounds(100, 100, 411, 262);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "cell 0 0,alignx left,aligny center");
			panel.setLayout(new MigLayout("", "[34px][][1px]", "[10px]"));
			{
				JButton googleImage = new JButton("");
				googleImage.setFocusTraversalKeysEnabled(false);
				googleImage.setFocusPainted(false);
				googleImage.setBorderPainted(false);
				googleImage.setBorder(null);
				googleImage.setIcon(new ImageIcon(this.getClass().getResource("/icons/Google.png")));
				panel.add(googleImage, "cell 0 0,alignx left,aligny center");
				
			}
			{
				JLabel lblGoogleContactsImport = new JLabel(Messages.getString("AbDialogGoogleSync.lblGoogleContactsImport.text")); //$NON-NLS-1$
				lblGoogleContactsImport.setFont(new Font("Dialog", Font.BOLD, 16));
				panel.add(lblGoogleContactsImport, "cell 1 0");
			}
			{
				JLabel label = new JLabel("");
				panel.add(label, "cell 2 0,alignx left,aligny center");
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
			contentPanel.add(panel, "cell 0 1,grow");
			panel.setLayout(new MigLayout("", "[][grow]", "[][][]"));
			{
				JLabel lblLogin = new JLabel(Messages.getString("AbDialogGoogleSync.lblLogin.text")); //$NON-NLS-1$
				panel.add(lblLogin, "cell 0 0,alignx trailing");
			}
			{
				loginTextField = new JTextField();
				panel.add(loginTextField, "cell 1 0,growx");
				loginTextField.setColumns(10);
			}
			{
				JLabel lblPassword = new JLabel(Messages.getString("AbDialogGoogleSync.lblPassword.text")); //$NON-NLS-1$
				panel.add(lblPassword, "cell 0 1,alignx trailing");
			}
			{
				pwdTextField = new JPasswordField();
				panel.add(pwdTextField, "cell 1 1,growx");
			}
			{
				loadingLabel = new JLabel("");
				loadingLabel.setForeground(Color.GRAY);
				panel.add(loadingLabel, "cell 1 2,alignx center");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton syncButton = new JButton(Messages.getString("AbDialogGoogleSync.syncButton.text")); //$NON-NLS-1$
				syncButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {	
						loadingLabel.setText("working..");
						syncContacts();
					}
				});
				syncButton.setActionCommand("OK");
				buttonPane.add(syncButton);
				getRootPane().setDefaultButton(syncButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Imports google contacts
	 */
	private void syncContacts() {
		
		String login = loginTextField.getText();
		String pwd = new String(pwdTextField.getPassword());
		
				
		if(login.isEmpty()){
			return;
		}
		
		if(pwd.isEmpty()){
			return;
		}
				
		
		GoogleSync sync = new GoogleSync(login, pwd);
		
		AbProfile profile = InitProfile.getProfile();
		
		for (AbPerson person : sync.fetchContacts()) {
			profile.addPerson(person);
		}
		
		this.setVisible(false);
	}
}
