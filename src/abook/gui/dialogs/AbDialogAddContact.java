package abook.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import net.miginfocom.swing.MigLayout;

import org.apache.commons.validator.GenericValidator;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbGroup;
import abook.profile.AbPerson;
import abook.profile.AbProfile;
import abook.profile.InitProfile;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

/**
 * User add dialog
 * 
 * @author Radovan Dvorský
 *
 */
@SuppressWarnings("serial")
public class AbDialogAddContact extends JDialog {
	
	
	private static final int IMAGE_WIDTH = 100;
	private static final int IMAGE_HEIGHT = 100;
	private static final Color VALIDATE_ERROR_COLOR = Color.RED;
	
	/**
	 * File dialog filter
	 */
	private class ImageFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			String name = f.getName().toLowerCase();
			
			if(name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith("png") 
					|| name.endsWith(".gif") || f.isDirectory()){
				return true;
			}
			
			return false;
		}

		@Override
		public String getDescription() {
			return ".jpg, .jpeg, .png and .gif files";
		}

	}
	
	private DefaultListModel groupListModel;
	private AbProfile profile;

	private final JPanel contentPanel = new JPanel();
	private Image userImage;
	private JTextField namePrefixTextField;
	private JTextField firstNameTextField;
	private JTextField lastnameTextField;
	private JTextField textField_3;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private JTextField pscTextField;
	private JTextField countryTextField;
	private JTextField phoneHomeTextField;
	private JTextField phoneWorkTextField;
	private JTextField cellPhoneTextField;
	private JTextField emailHomeTextField;
	private JTextField emailWorkTextField;
	private JTextField icqTextField;
	private JTextField skypeTextField;
	private JTextField jabberTextField;
	private JTextField gtalkTextField;
	private JDateChooser birthdayDateChooser;
	private JTextArea noteTextArea;
	private JButton btnUserImage;
	private JList groupsList;
	
	/**
	 * Create empty dialog.
	 */
	public AbDialogAddContact() {
		
		this.profile = InitProfile.getProfile();
		this.groupListModel = new DefaultListModel();
		setAlwaysOnTop(true);
		setModal(true);
		setBounds(100, 100, 830, 565);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel formPanelHead = new JPanel();
			contentPanel.add(formPanelHead, BorderLayout.CENTER);
			formPanelHead.setLayout(new MigLayout("", "[110px:110px:110px][220px:220px:220px][220px:220px:220px][220px:220px:220px]", "[][][17px]"));
			{
				JPanel panel = new JPanel();
				formPanelHead.add(panel, "cell 0 0,growx,aligny top");
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
				{
					btnUserImage = new JButton(" ");
					btnUserImage.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							chooseUserImage();
						}
					});
					btnUserImage.setPreferredSize(new Dimension(100, 100));
					btnUserImage.setMargin(new Insets(10, 14, 2, 14));
					btnUserImage.setBorder(null);
					panel.add(btnUserImage);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				formPanelHead.add(panel_1, "cell 1 0,grow");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("center:default:grow"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel lblJmno = new JLabel(Messages.getString("AbDialogAddContact.lblJmno.text")); //$NON-NLS-1$
					lblJmno.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(lblJmno, "2, 2");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text")); //$NON-NLS-1$
					panel_1.add(label, "2, 4, right, default");
				}
				{
					namePrefixTextField = new JTextField();
					namePrefixTextField.setColumns(10);
					panel_1.add(namePrefixTextField, "4, 4, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_1")); //$NON-NLS-1$
					panel_1.add(label, "2, 6, right, default");
				}
				{
					firstNameTextField = new JTextField();
					firstNameTextField.setColumns(10);
					panel_1.add(firstNameTextField, "4, 6, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_2")); //$NON-NLS-1$
					panel_1.add(label, "2, 8, right, default");
				}
				{
					lastnameTextField = new JTextField();
					lastnameTextField.setColumns(10);
					panel_1.add(lastnameTextField, "4, 8, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_3")); //$NON-NLS-1$
					panel_1.add(label, "2, 10, right, default");
				}
				{
					textField_3 = new JTextField();
					textField_3.setColumns(10);
					panel_1.add(textField_3, "4, 10, fill, default");
				}
			}
			{
				JPanel panel_1 = new JPanel();
				formPanelHead.add(panel_1, "cell 2 0,grow");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_4")); //$NON-NLS-1$
					label.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(label, "2, 2");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_5")); //$NON-NLS-1$
					panel_1.add(label, "2, 4, right, default");
				}
				{
					streetTextField = new JTextField();
					streetTextField.setColumns(10);
					panel_1.add(streetTextField, "4, 4");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_6")); //$NON-NLS-1$
					panel_1.add(label, "2, 6, right, default");
				}
				{
					cityTextField = new JTextField();
					cityTextField.setColumns(10);
					panel_1.add(cityTextField, "4, 6");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_7")); //$NON-NLS-1$
					panel_1.add(label, "2, 8, right, default");
				}
				{
					pscTextField = new JTextField();
					pscTextField.setColumns(10);
					panel_1.add(pscTextField, "4, 8");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_8")); //$NON-NLS-1$
					panel_1.add(label, "2, 10, right, default");
				}
				{
					countryTextField = new JTextField();
					countryTextField.setColumns(10);
					panel_1.add(countryTextField, "4, 10");
				}
			}
			{
				JPanel panel_1 = new JPanel();
				formPanelHead.add(panel_1, "cell 3 0,grow");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_9")); //$NON-NLS-1$
					label.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(label, "2, 2");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_10")); //$NON-NLS-1$
					panel_1.add(label, "2, 4, right, default");
				}
				{
					emailHomeTextField = new JTextField();
					emailHomeTextField.setColumns(10);
					panel_1.add(emailHomeTextField, "4, 4, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_11")); //$NON-NLS-1$
					panel_1.add(label, "2, 6, right, default");
				}
				{
					emailWorkTextField = new JTextField();
					emailWorkTextField.setColumns(10);
					panel_1.add(emailWorkTextField, "4, 6, fill, default");
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setPreferredSize(new Dimension(10, 80));
				formPanelHead.add(panel_1, "cell 1 1,grow");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_12")); //$NON-NLS-1$
					label.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(label, "2, 2");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_13")); //$NON-NLS-1$
					panel_1.add(label, "2, 4, right, default");
				}
				{
					phoneHomeTextField = new JTextField();
					phoneHomeTextField.setColumns(10);
					panel_1.add(phoneHomeTextField, "4, 4, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_14")); //$NON-NLS-1$
					panel_1.add(label, "2, 6, right, default");
				}
				{
					phoneWorkTextField = new JTextField();
					phoneWorkTextField.setColumns(10);
					panel_1.add(phoneWorkTextField, "4, 6, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_15")); //$NON-NLS-1$
					panel_1.add(label, "2, 8, right, default");
				}
				{
					cellPhoneTextField = new JTextField();
					cellPhoneTextField.setColumns(10);
					panel_1.add(cellPhoneTextField, "4, 8, fill, default");
				}
			}
			{
				JPanel panel_1 = new JPanel();
				formPanelHead.add(panel_1, "cell 2 1,grow");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,}));
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_16")); //$NON-NLS-1$
					label.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(label, "2, 2");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_17")); //$NON-NLS-1$
					panel_1.add(label, "2, 4, right, default");
				}
				{
					icqTextField = new JTextField();
					icqTextField.setColumns(10);
					panel_1.add(icqTextField, "4, 4, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_18")); //$NON-NLS-1$
					panel_1.add(label, "2, 6, right, default");
				}
				{
					skypeTextField = new JTextField();
					skypeTextField.setColumns(10);
					panel_1.add(skypeTextField, "4, 6, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_19")); //$NON-NLS-1$
					panel_1.add(label, "2, 8, right, default");
				}
				{
					gtalkTextField = new JTextField();
					gtalkTextField.setColumns(10);
					panel_1.add(gtalkTextField, "4, 8, fill, default");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_20")); //$NON-NLS-1$
					panel_1.add(label, "2, 10, right, default");
				}
				{
					jabberTextField = new JTextField();
					jabberTextField.setColumns(10);
					panel_1.add(jabberTextField, "4, 10, fill, default");
				}
			}
			{
				JPanel panel_1 = new JPanel();
				formPanelHead.add(panel_1, "cell 3 1,grow");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),}));
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_21")); //$NON-NLS-1$
					label.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(label, "2, 2");
				}
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_22")); //$NON-NLS-1$
					panel_1.add(label, "2, 4, right, top");
				}
				{
					birthdayDateChooser = new JDateChooser();
					panel_1.add(birthdayDateChooser, "4, 4, fill, top");
				}
			}
			{
				JPanel panel_1 = new JPanel();
				formPanelHead.add(panel_1, "cell 1 2,grow");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),}));
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_23")); //$NON-NLS-1$
					label.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(label, "2, 2");
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setPreferredSize(new Dimension(50, 70));
					panel_1.add(scrollPane, "4, 4, fill, fill");
					{
						groupsList = new JList();
						scrollPane.setViewportView(groupsList);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				formPanelHead.add(panel_1, "cell 2 2,grow");
				panel_1.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("center:default"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),}));
				{
					JLabel label = new JLabel(Messages.getString("AbDialogAddContact.label.text_24")); //$NON-NLS-1$
					label.setFont(new Font("Dialog", Font.BOLD, 14));
					panel_1.add(label, "2, 2");
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setMinimumSize(new Dimension(65, 150));
					scrollPane.setMaximumSize(new Dimension(65, 150));
					panel_1.add(scrollPane, "4, 4, fill, fill");
					{
						noteTextArea = new JTextArea();
						scrollPane.setViewportView(noteTextArea);
						noteTextArea.setRows(10);
						noteTextArea.setWrapStyleWord(true);
						noteTextArea.setLineWrap(true);
						noteTextArea.setColumns(20);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						save();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		
		initGroupList();		
	}
	
	
	/**
	 * Initialize groupList
	 */
	private void initGroupList() {
		
		for (AbGroup group : profile.getListOfGroups()) {
			groupListModel.addElement(group.getGroupName());
		}
		
		groupsList.setModel(groupListModel);
	}


	/**
	 * Save formular and create new user
	 */
	private void save() {
		
		if(validateForm()){
						
			AbPerson contact = new AbPerson(firstNameTextField.getText(), 
					lastnameTextField.getText());
			
			contact.setNamePrefix(namePrefixTextField.getText());
			contact.setNameSuffix(firstNameTextField.getText());
			contact.setStreet(streetTextField.getText());
			contact.setCity(cityTextField.getText());
			contact.setPsc(pscTextField.getText());
			contact.setCountry(countryTextField.getText());
			contact.setPhoneHome(phoneHomeTextField.getText());
			contact.setPhoneWork(phoneWorkTextField.getText());
			contact.setCellPhone(cellPhoneTextField.getText());
			contact.setEmailHome(emailHomeTextField.getText());
			contact.setEmailWork(emailWorkTextField.getText());
			contact.setIcq(icqTextField.getText());
			contact.setSkype(skypeTextField.getText());
			contact.setJabber(jabberTextField.getText());
			contact.setGtalk(gtalkTextField.getText());			
			contact.setBirthday(birthdayDateChooser.getDate());
									
			for (Object group : this.groupsList.getSelectedValues()) {
				contact.addGroup(group.toString());
			}
			
			contact.setBirthday(birthdayDateChooser.getDate());
			contact.setNote(noteTextArea.getText());
			contact.setUserImage(userImage);
			
			profile.addPerson(contact);
			
			// zatvor dialog
			setVisible(false);
			
			InitListenerCore.getListenerCore().fireListeners(
					new AbEvent(this), AbListener.PROFILE_CHANGED);
		}		
	}


	/**
	 * Validate formular
	 * 
	 * @return boolean
	 */
	private boolean validateForm() {
		
		// kontorla mena
		if(firstNameTextField.getText().isEmpty()){
			firstNameTextField.setBackground(VALIDATE_ERROR_COLOR);
			return false;		
		}
		if(lastnameTextField.getText().isEmpty()){
			lastnameTextField.setBackground(VALIDATE_ERROR_COLOR);
			return false;
		}
		
		// ak je nastavene psc, tak musi byt číslo
		if(!pscTextField.getText().isEmpty()){
			// je cislo a je 5 znakov dlhe
			if(!GenericValidator.isInt(pscTextField.getText())){
				pscTextField.setBackground(VALIDATE_ERROR_COLOR);
				return false;
			}
		}
		
		// ak su nastavene telefonne cisla, tak musia byt cisla
		if(!phoneHomeTextField.getText().isEmpty()){
			if(!GenericValidator.isInt(phoneHomeTextField.getText())){
				phoneHomeTextField.setBackground(VALIDATE_ERROR_COLOR);
				return false;
			}
		}
		
		if(!phoneWorkTextField.getText().isEmpty()){
			if(!GenericValidator.isInt(phoneWorkTextField.getText())){
				phoneWorkTextField.setBackground(VALIDATE_ERROR_COLOR);
				return false;
			}
		}
		
		if(!cellPhoneTextField.getText().isEmpty()){
			if(!GenericValidator.isInt(cellPhoneTextField.getText())){
				cellPhoneTextField.setBackground(VALIDATE_ERROR_COLOR);
				return false;
			}
		}
		
		// ak je nastaveny mail, tak musi mat spravny format
		if(!emailHomeTextField.getText().isEmpty()){
			if(GenericValidator.isEmail(emailHomeTextField.getText())){
				emailHomeTextField.setBackground(VALIDATE_ERROR_COLOR);
				return true;
			}
		}
		
		if(!emailWorkTextField.getText().isEmpty()){
			if(GenericValidator.isEmail(emailWorkTextField.getText())){
				emailWorkTextField.setBackground(VALIDATE_ERROR_COLOR);
				return true;
			}
		}
		
		// gtalk a jabber ma format emailu
		if(!gtalkTextField.getText().isEmpty()){
			if(!GenericValidator.isEmail(gtalkTextField.getText())){
				gtalkTextField.setBackground(VALIDATE_ERROR_COLOR);
				return false;
			}
		}
		
		
		if(!jabberTextField.getText().isEmpty()){
			if(!GenericValidator.isEmail(jabberTextField.getText())){
				jabberTextField.setBackground(VALIDATE_ERROR_COLOR);
				return false;
			}
		}
		
				
		// icq musi byt cislo
		if(!icqTextField.getText().isEmpty()){
			if(!GenericValidator.isInt(icqTextField.getText())){
				icqTextField.setBackground(VALIDATE_ERROR_COLOR);
				return false;
			}
		}
		
		// musi byt vybrana aspon jedna skupina
		if(groupsList.getSelectedIndices().length < 1){
			groupsList.setBackground(VALIDATE_ERROR_COLOR);
			return false;
		}
		
		// kontrola datumu
		/*if(birthdayDateChooser.getDate() != null){
			if(!GenericValidator.isDate(birthdayDateChooser.getDateFormatString(), 
					getLocale())){
				return false;
			}
		}*/
		
		return true;
	}
	
	/**
	 * User image dialog
	 */
	private void chooseUserImage(){

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new AbDialogAddContact.ImageFileFilter());

		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			// ziskame obrazok z dialogu
			ImageIcon userImageIcon = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());			
			Image img = userImageIcon.getImage();
			// zmensime resp. zvascime na pozadovanu velkost
			userImage = img.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
			// nastavime upraveny obrazok do btn
			btnUserImage.setIcon(new ImageIcon(userImage));
			btnUserImage.repaint();
		}	
	}
}
