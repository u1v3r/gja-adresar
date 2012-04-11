package abook.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

public class AbDialogGoogleSync extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField loginTextField;
	private JPasswordField pwdTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AbDialogGoogleSync dialog = new AbDialogGoogleSync();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			contentPanel.add(panel, "cell 0 0,grow");
			{
				JButton button = new JButton("");
				panel.add(button);
			}
			{
				JLabel label = new JLabel("");
				panel.add(label);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, "cell 0 1,grow");
			panel.setLayout(new MigLayout("", "[][grow]", "[][]"));
			{
				JLabel lblLogin = new JLabel("Login:");
				panel.add(lblLogin, "cell 0 0,alignx trailing");
			}
			{
				loginTextField = new JTextField();
				panel.add(loginTextField, "cell 1 0,growx");
				loginTextField.setColumns(10);
			}
			{
				JLabel lblPassword = new JLabel("Password:");
				panel.add(lblPassword, "cell 0 1,alignx trailing");
			}
			{
				pwdTextField = new JPasswordField();
				panel.add(pwdTextField, "cell 1 1,growx");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton syncButton = new JButton("Sync");
				syncButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						syncContacts();
					}
				});
				syncButton.setActionCommand("OK");
				buttonPane.add(syncButton);
				getRootPane().setDefaultButton(syncButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void syncContacts() {
		
		String login = loginTextField.getText();
		String pwd = pwdTextField.getPassword().toString();
		
		
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
	}
}
