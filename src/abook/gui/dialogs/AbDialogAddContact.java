package abook.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.validator.GenericValidator;

import abook.others.AddContactFormFocusTravel;
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
 * 
 * @author Radovan Dvorský
 *
 */
@SuppressWarnings("serial")
public class AbDialogAddContact extends JDialog {
	
	
	private static final int IMAGE_WIDTH = 100;
	private static final int IMAGE_HEIGHT = 100;
	
	/**
	 * Filter pre file dialog
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
	private JTextField namePrefixTextField;
	private JTextField firstNameTextField;
	private JTextField lastnameTextField;
	private JTextField nameSuffixTextField;
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
	private JButton btnUserImage;
	private JTextArea noteTextArea;
	private JList groupsList;
	private Image userImage;
	private JDateChooser birthdayDateChooser;

	/**
	 * Create the dialog.
	 */
	public AbDialogAddContact() {
		
		this.profile = InitProfile.getProfile();
		this.groupListModel = new DefaultListModel();
		
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		setBounds(100, 100, 774, 542);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel formPanelContent = new JPanel();
			contentPanel.add(formPanelContent, BorderLayout.CENTER);
			formPanelContent.setLayout(new FormLayout(new ColumnSpec[] {
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(61dlu;default):grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(21dlu;default)"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("max(60dlu;default):grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("30dlu"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("29dlu"),
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("max(10dlu;default)"),
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),}));
			{
				JLabel lblNewLabel = new JLabel("Tel. čísla");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblNewLabel, "2, 4");
			}
			{
				JLabel lblEmail = new JLabel("E-Mail");
				lblEmail.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblEmail, "8, 4");
			}
			{
				JLabel lblIm = new JLabel("IM");
				lblIm.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblIm, "14, 4");
			}
			{
				JLabel lblDomov = new JLabel("Domov");
				formPanelContent.add(lblDomov, "2, 6, right, default");
			}
			{
				phoneHomeTextField = new JTextField();
				formPanelContent.add(phoneHomeTextField, "4, 6, left, default");
				phoneHomeTextField.setColumns(10);
			}
			{
				JLabel lblEmail_1 = new JLabel("Domov");
				formPanelContent.add(lblEmail_1, "8, 6, right, default");
			}
			{
				emailHomeTextField = new JTextField();
				formPanelContent.add(emailHomeTextField, "10, 6, left, default");
				emailHomeTextField.setColumns(10);
			}
			{
				JLabel lblIcq = new JLabel("ICQ");
				formPanelContent.add(lblIcq, "14, 6, right, default");
			}
			{
				icqTextField = new JTextField();
				formPanelContent.add(icqTextField, "16, 6, left, default");
				icqTextField.setColumns(10);
			}
			{
				JLabel lblPrca = new JLabel("Práca");
				formPanelContent.add(lblPrca, "2, 8, right, default");
			}
			{
				phoneWorkTextField = new JTextField();
				formPanelContent.add(phoneWorkTextField, "4, 8, left, default");
				phoneWorkTextField.setColumns(10);
			}
			{
				JLabel lblEmail_2 = new JLabel("Práca");
				formPanelContent.add(lblEmail_2, "8, 8, right, default");
			}
			{
				emailWorkTextField = new JTextField();
				formPanelContent.add(emailWorkTextField, "10, 8, left, default");
				emailWorkTextField.setColumns(10);
			}
			{
				JLabel lblSkype = new JLabel("Skype");
				formPanelContent.add(lblSkype, "14, 8, right, default");
			}
			{
				skypeTextField = new JTextField();
				formPanelContent.add(skypeTextField, "16, 8, left, default");
				skypeTextField.setColumns(10);
			}
			{
				JLabel lblMobil = new JLabel("Mobil");
				formPanelContent.add(lblMobil, "2, 10, right, default");
			}
			{
				cellPhoneTextField = new JTextField();
				formPanelContent.add(cellPhoneTextField, "4, 10, left, default");
				cellPhoneTextField.setColumns(10);
			}
			{
				JLabel lblJabber = new JLabel("Jabber");
				formPanelContent.add(lblJabber, "14, 10, right, default");
			}
			{
				jabberTextField = new JTextField();
				formPanelContent.add(jabberTextField, "16, 10, left, default");
				jabberTextField.setColumns(10);
			}
			{
				JLabel lblGtalk = new JLabel("GTalk");
				formPanelContent.add(lblGtalk, "14, 12, right, default");
			}
			{
				gtalkTextField = new JTextField();
				formPanelContent.add(gtalkTextField, "16, 12, left, default");
				gtalkTextField.setColumns(10);
			}
			{
				JLabel lblPersonal = new JLabel("Osobné");
				lblPersonal.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblPersonal, "2, 16");
			}
			{
				JLabel lblGroups = new JLabel("Skupiny");
				lblGroups.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblGroups, "8, 16");
			}
			{
				JLabel lblPoznmka = new JLabel("Poznámka");
				lblPoznmka.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblPoznmka, "14, 16");
			}
			{
				JLabel lblNarodeniny = new JLabel("Narodeniny");
				formPanelContent.add(lblNarodeniny, "2, 18, right, default");
			}
			{
				birthdayDateChooser = new JDateChooser();
				formPanelContent.add(birthdayDateChooser, "4, 18, fill, fill");
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				formPanelContent.add(scrollPane, "10, 20, fill, fill");
				{
					groupsList = new JList();
					groupsList.setVisibleRowCount(5);
					scrollPane.setViewportView(groupsList);
				}
			}
			{
				noteTextArea = new JTextArea();
				noteTextArea.setLineWrap(true);
				noteTextArea.setWrapStyleWord(true);
				noteTextArea.setColumns(20);
				formPanelContent.add(noteTextArea, "16, 20, fill, fill");
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel formPanelHead = new JPanel();
				panel.add(formPanelHead, BorderLayout.CENTER);
				FormLayout fl_formPanelHead = new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(16dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(59dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(31dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(28dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),},
					new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("22dlu"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,});
				formPanelHead.setLayout(fl_formPanelHead);
				{
					JLabel lblJmnoKontaktu = new JLabel("Jméno kontaktu");
					lblJmnoKontaktu.setFont(new Font("Dialog", Font.BOLD, 14));
					formPanelHead.add(lblJmnoKontaktu, "4, 2");
				}
				{
					JLabel lblAddress = new JLabel("Adresa");
					lblAddress.setFont(new Font("Dialog", Font.BOLD, 14));
					formPanelHead.add(lblAddress, "10, 2");
				}
				{
					JLabel lblPrefix = new JLabel("Titul");
					formPanelHead.add(lblPrefix, "4, 4, right, default");
				}
				{
					namePrefixTextField = new JTextField();
					formPanelHead.add(namePrefixTextField, "6, 4, left, default");
					namePrefixTextField.setColumns(10);				
				}
				{
					JLabel lblStreet = new JLabel("Ulice");
					formPanelHead.add(lblStreet, "10, 4, right, default");
				}
				{
					streetTextField = new JTextField();
					formPanelHead.add(streetTextField, "12, 4, left, default");
					streetTextField.setColumns(10);
				}
				{
					JLabel lblFirstName = new JLabel("Jméno");
					formPanelHead.add(lblFirstName, "4, 6, right, default");
				}
				{
					firstNameTextField = new JTextField();
					formPanelHead.add(firstNameTextField, "6, 6, left, default");
					firstNameTextField.setColumns(10);
				}
				{
					JLabel lblCity = new JLabel("Město");
					formPanelHead.add(lblCity, "10, 6, right, default");
				}
				{
					cityTextField = new JTextField();
					formPanelHead.add(cityTextField, "12, 6, left, default");
					cityTextField.setColumns(10);
				}
				{
					JLabel lblLastName = new JLabel("Příjmení");
					formPanelHead.add(lblLastName, "4, 8, right, default");
				}
				{
					lastnameTextField = new JTextField();
					formPanelHead.add(lastnameTextField, "6, 8, left, default");
					lastnameTextField.setColumns(10);
				}
				{
					JLabel lblPSC = new JLabel("PSČ");
					formPanelHead.add(lblPSC, "10, 8, right, default");
				}
				{
					pscTextField = new JTextField();
					formPanelHead.add(pscTextField, "12, 8, left, default");
					pscTextField.setColumns(10);
				}
				{
					JLabel lblNameSuffix = new JLabel("Titul za");
					formPanelHead.add(lblNameSuffix, "4, 10, right, default");
				}
				{
					nameSuffixTextField = new JTextField();
					formPanelHead.add(nameSuffixTextField, "6, 10, left, default");
					nameSuffixTextField.setColumns(10);
				}
				{
					JLabel lblCountry = new JLabel("Krajina");
					formPanelHead.add(lblCountry, "10, 10, right, default");
				}
				{
					countryTextField = new JTextField();
					formPanelHead.add(countryTextField, "12, 10, left, default");
					countryTextField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
				{
					btnUserImage = new JButton(" ");
					btnUserImage.setMargin(new Insets(10, 14, 2, 14));
					panel_1.add(btnUserImage);
					btnUserImage.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
					btnUserImage.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							chooseUserImage();
						}
					});
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
		
		List<Component> focusList = new ArrayList<Component>();
		focusList.add(namePrefixTextField);
		focusList.add(firstNameTextField);
		focusList.add(lastnameTextField);
		focusList.add(nameSuffixTextField);
		focusList.add(streetTextField);
		focusList.add(cityTextField);
		focusList.add(pscTextField);
		focusList.add(countryTextField);
		focusList.add(phoneHomeTextField);
		focusList.add(phoneWorkTextField);
		focusList.add(cellPhoneTextField);
		focusList.add(emailHomeTextField);
		focusList.add(emailWorkTextField);
		focusList.add(icqTextField);
		focusList.add(skypeTextField);
		focusList.add(jabberTextField);
		focusList.add(gtalkTextField);
		focusList.add(noteTextArea);
		
		setFocusTraversalPolicy(new AddContactFormFocusTravel(focusList));
		
		
	}
	
	
	/**
	 * Inicializuje Jlist pre vyber skupin
	 */
	private void initGroupList() {
		
		groupsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		for (AbGroup group : profile.getListOfGroups()) {
			groupListModel.addElement(group.getGroupName());
		}		
		
		groupsList.setModel(groupListModel);
	}


	/**
	 * Uloží hondnoty zadané do formulára
	 */
	private void save() {
		
		if(validateForm()){
						
			AbPerson contact = new AbPerson(firstNameTextField.getText(), 
					lastnameTextField.getText());
			
			contact.setNamePrefix(namePrefixTextField.getText());
			contact.setNameSuffix(nameSuffixTextField.getText());
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
			
			//TODO se to zkomplikovalo ... uz se nevede seznam integeru (indexu) vybranych skupin, ale primo seznam stringu (jmen skupin)
			/*for (Integer groups : this.groupsList.getSelectedIndices()) {
				contact.addGroup(groups);
			}*/
			contact.setBirthday(birthdayDateChooser.getDate());
			contact.setNote(noteTextArea.getText());
			contact.setUserImage(userImage);
			
			profile.addPerson(contact);
			
			// zatvor dialog
			setVisible(false);
		}		
	}


	/**
	 * Skontroluje správnosť hodnôt zadaných do formulára
	 * 
	 * @return boolean
	 */
	private boolean validateForm() {
		
		// kontorla mena
		if(firstNameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty()){
			return false;
		}
		
		// ak je nastavene psc, tak musi byt číslo
		if(!pscTextField.getText().isEmpty()){
			// je cislo a je 5 znakov dlhe
			if(!GenericValidator.isInt(pscTextField.getText()) ||
					!GenericValidator.isInRange(Integer.valueOf(pscTextField.getText()), 5, 5)){
				return false;
			}
		}
		
		// ak su nastavene telefonne cisla, tak musia byt cisla
		if(!phoneHomeTextField.getText().isEmpty()){
			if(!GenericValidator.isInt(phoneHomeTextField.getText())){
				return false;
			}
		}
		
		if(!phoneWorkTextField.getText().isEmpty()){
			if(!GenericValidator.isInt(phoneWorkTextField.getText())){
				return false;
			}
		}
		
		if(!cellPhoneTextField.getText().isEmpty()){
			if(!GenericValidator.isInt(cellPhoneTextField.getText())){
				return false;
			}
		}
		
		// ak je nastaveny mail, tak musi mat spravny format
		if(!emailHomeTextField.getText().isEmpty()){
			if(GenericValidator.isEmail(emailHomeTextField.getText())){
				return true;
			}
		}
		
		if(!emailWorkTextField.getText().isEmpty()){
			if(GenericValidator.isEmail(emailWorkTextField.getText())){
				return true;
			}
		}
		
		// gtalk a jabber ma format emailu
		if(!gtalkTextField.getText().isEmpty()){
			if(!GenericValidator.isEmail(gtalkTextField.getText())){
				return false;
			}
		}
		
		
		if(!jabberTextField.getText().isEmpty()){
			if(!GenericValidator.isEmail(jabberTextField.getText())){
				return false;
			}
		}
		
				
		// icq musi byt cislo
		if(!icqTextField.getText().isEmpty()){
			if(!GenericValidator.isInt(icqTextField.getText())){
				return false;
			}
		}
		
		// kontrola datumu
		if(birthdayDateChooser.getDate() != null){
			if(!GenericValidator.isDate(birthdayDateChooser.getDateFormatString(), 
					getLocale())){
				return false;
			}
		}
		
		return true;
	}
	

	/**
	 * Výber fotky užívateľa
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
