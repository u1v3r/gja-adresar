package abook.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

import abook.others.AddContactFormFocusTravel;
import abook.profile.AbPerson;
import abook.profile.AbProfile;
import abook.profile.InitProfile;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * 
 * @author Radovan Dvorský
 *
 */
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
	private JTextField birthdayTextField;
	private JTextField gtalkTextField;
	private JButton btnUserImage;
	private JTextArea noteTextArea;
	private JList goupsList;
	private Image userImage;

	/**
	 * Create the dialog.
	 */
	public AbDialogAddContact() {
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
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
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
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					RowSpec.decode("default:grow"),}));
			{
				JLabel lblNewLabel = new JLabel("Tel. čísla");
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblNewLabel, "2, 2");
			}
			{
				JLabel lblEmail = new JLabel("E-Mail");
				lblEmail.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblEmail, "8, 2");
			}
			{
				JLabel lblIm = new JLabel("IM");
				lblIm.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblIm, "14, 2");
			}
			{
				JLabel lblDomov = new JLabel("Domov");
				formPanelContent.add(lblDomov, "2, 4, right, default");
			}
			{
				phoneHomeTextField = new JTextField();
				formPanelContent.add(phoneHomeTextField, "4, 4, fill, default");
				phoneHomeTextField.setColumns(10);
			}
			{
				JLabel lblEmail_1 = new JLabel("Domov");
				formPanelContent.add(lblEmail_1, "8, 4, right, default");
			}
			{
				emailHomeTextField = new JTextField();
				formPanelContent.add(emailHomeTextField, "10, 4, fill, default");
				emailHomeTextField.setColumns(10);
			}
			{
				JLabel lblIcq = new JLabel("ICQ");
				formPanelContent.add(lblIcq, "14, 4, right, default");
			}
			{
				icqTextField = new JTextField();
				formPanelContent.add(icqTextField, "16, 4, fill, default");
				icqTextField.setColumns(10);
			}
			{
				JLabel lblPrca = new JLabel("Práca");
				formPanelContent.add(lblPrca, "2, 6, right, default");
			}
			{
				phoneWorkTextField = new JTextField();
				formPanelContent.add(phoneWorkTextField, "4, 6, fill, default");
				phoneWorkTextField.setColumns(10);
			}
			{
				JLabel lblEmail_2 = new JLabel("Práca");
				formPanelContent.add(lblEmail_2, "8, 6, right, default");
			}
			{
				emailWorkTextField = new JTextField();
				formPanelContent.add(emailWorkTextField, "10, 6, fill, default");
				emailWorkTextField.setColumns(10);
			}
			{
				JLabel lblSkype = new JLabel("Skype");
				formPanelContent.add(lblSkype, "14, 6, right, default");
			}
			{
				skypeTextField = new JTextField();
				formPanelContent.add(skypeTextField, "16, 6, fill, default");
				skypeTextField.setColumns(10);
			}
			{
				JLabel lblMobil = new JLabel("Mobil");
				formPanelContent.add(lblMobil, "2, 8, right, default");
			}
			{
				cellPhoneTextField = new JTextField();
				formPanelContent.add(cellPhoneTextField, "4, 8, fill, default");
				cellPhoneTextField.setColumns(10);
			}
			{
				JLabel lblJabber = new JLabel("Jabber");
				formPanelContent.add(lblJabber, "14, 8, right, default");
			}
			{
				jabberTextField = new JTextField();
				formPanelContent.add(jabberTextField, "16, 8, fill, default");
				jabberTextField.setColumns(10);
			}
			{
				JLabel lblGtalk = new JLabel("GTalk");
				formPanelContent.add(lblGtalk, "14, 10, right, default");
			}
			{
				gtalkTextField = new JTextField();
				formPanelContent.add(gtalkTextField, "16, 10, fill, default");
				gtalkTextField.setColumns(10);
			}
			{
				JLabel lblPersonal = new JLabel("Osobné");
				lblPersonal.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblPersonal, "2, 14");
			}
			{
				JLabel lblGroups = new JLabel("Skupiny");
				lblGroups.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblGroups, "8, 14");
			}
			{
				JLabel lblPoznmka = new JLabel("Poznámka");
				lblPoznmka.setFont(new Font("Dialog", Font.BOLD, 14));
				formPanelContent.add(lblPoznmka, "14, 14");
			}
			{
				JLabel lblNarodeniny = new JLabel("Narodeniny");
				formPanelContent.add(lblNarodeniny, "2, 16, right, default");
			}
			{
				birthdayTextField = new JTextField();
				formPanelContent.add(birthdayTextField, "4, 16, fill, default");
				birthdayTextField.setColumns(10);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				formPanelContent.add(scrollPane, "10, 18, fill, fill");
				{
					goupsList = new JList();
					goupsList.setVisibleRowCount(5);
					scrollPane.setViewportView(goupsList);
				}
			}
			{
				noteTextArea = new JTextArea();
				noteTextArea.setLineWrap(true);
				noteTextArea.setWrapStyleWord(true);
				noteTextArea.setColumns(20);
				formPanelContent.add(noteTextArea, "16, 18, fill, fill");
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
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
					formPanelHead.add(lblJmnoKontaktu, "2, 2");
				}
				{
					JLabel lblAddress = new JLabel("Adresa");
					lblAddress.setFont(new Font("Dialog", Font.BOLD, 14));
					formPanelHead.add(lblAddress, "8, 2");
				}
				{
					JLabel lblPrefix = new JLabel("Titul");
					formPanelHead.add(lblPrefix, "2, 4, right, default");
				}
				{
					namePrefixTextField = new JTextField();
					formPanelHead.add(namePrefixTextField, "4, 4, fill, default");
					namePrefixTextField.setColumns(10);
				}
				{
					JLabel lblStreet = new JLabel("Ulice");
					formPanelHead.add(lblStreet, "8, 4, right, default");
				}
				{
					streetTextField = new JTextField();
					formPanelHead.add(streetTextField, "10, 4, fill, default");
					streetTextField.setColumns(10);
				}
				{
					JLabel lblFirstName = new JLabel("Jméno");
					formPanelHead.add(lblFirstName, "2, 6, right, default");
				}
				{
					firstNameTextField = new JTextField();
					formPanelHead.add(firstNameTextField, "4, 6, fill, default");
					firstNameTextField.setColumns(10);
				}
				{
					JLabel lblCity = new JLabel("Město");
					formPanelHead.add(lblCity, "8, 6, right, default");
				}
				{
					cityTextField = new JTextField();
					formPanelHead.add(cityTextField, "10, 6, fill, default");
					cityTextField.setColumns(10);
				}
				{
					JLabel lblLastName = new JLabel("Příjmení");
					formPanelHead.add(lblLastName, "2, 8, right, default");
				}
				{
					lastnameTextField = new JTextField();
					formPanelHead.add(lastnameTextField, "4, 8, fill, default");
					lastnameTextField.setColumns(10);
				}
				{
					JLabel lblPSC = new JLabel("PSČ");
					formPanelHead.add(lblPSC, "8, 8, right, default");
				}
				{
					pscTextField = new JTextField();
					formPanelHead.add(pscTextField, "10, 8, fill, default");
					pscTextField.setColumns(10);					
				}
				{
					JLabel lblNameSuffix = new JLabel("Titul za");
					formPanelHead.add(lblNameSuffix, "2, 10, right, default");
				}
				{
					nameSuffixTextField = new JTextField();
					formPanelHead.add(nameSuffixTextField, "4, 10, fill, default");
					nameSuffixTextField.setColumns(10);
				}
				{
					JLabel lblCountry = new JLabel("Krajina");
					formPanelHead.add(lblCountry, "8, 10, right, default");
				}
				{
					countryTextField = new JTextField();
					formPanelHead.add(countryTextField, "10, 10, fill, default");
					countryTextField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					btnUserImage = new JButton(" ");
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
		
		Component[] focusList = new Component[19];
		focusList[0] = namePrefixTextField;
		focusList[1] = firstNameTextField;
		focusList[2] = lastnameTextField;
		focusList[3] = nameSuffixTextField;
		focusList[4] = streetTextField;
		focusList[5] = cityTextField;
		focusList[6] = pscTextField;
		focusList[7] = countryTextField;
		focusList[8] = phoneHomeTextField;
		focusList[9] = phoneWorkTextField;
		focusList[10] = cellPhoneTextField;
		focusList[11] = emailHomeTextField;
		focusList[12] = emailWorkTextField;
		focusList[13] = icqTextField;
		focusList[14] = skypeTextField;
		focusList[15] = jabberTextField;
		focusList[16] = gtalkTextField;
		focusList[17] = birthdayTextField;
		focusList[18] = noteTextArea;
		
		setFocusTraversalPolicy(new AddContactFormFocusTravel(focusList));
		
		
	}
	
	
	/**
	 * Uloží hondnoty zadané do formulára
	 */
	private void save() {
		
		if(validateForm()){
			
			AbProfile profile = InitProfile.getProfile();
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
			/*
			 * TODO dorobit pridanie datumu narodenin cez java.date
			 */
			//contact.setBirthday()
			
			/*
			 * TODO dorobit pridanie skupiny 
			 */
			
			contact.setNote(noteTextArea.getText());
			contact.setUserImage(userImage);
			
			profile.addPerson(contact);
			
			// zatvor dialog
			setVisible(false);
		}		
	}


	/**
	 * Skontroluje správnosť a formát povinných hodnôt zadaných hodnôt do formulára
	 * 
	 * TODO Vytoviť novú triedu Validate, ktora bude obsahovať 
	 * metody na validaciu formulara 
	 * 
	 * @return
	 */
	private boolean validateForm() {
		
		// kontorla mena
		if(firstNameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty()){
			return false;
		}
		
		// ak je nastavene psc, tak musi byt číslo
		if(!pscTextField.getText().isEmpty()){
			try{
				Integer.valueOf(pscTextField.getText());
			} catch (NumberFormatException e){
				return false;
			}
		}
		
		// ak su nastavene telefonne cisla, tak musia byt cisla
		if(!phoneHomeTextField.getText().isEmpty()){
			try{
				Integer.valueOf(phoneHomeTextField.getText());
			} catch (NumberFormatException e){
				return false;
			}
		}
		
		if(!phoneWorkTextField.getText().isEmpty()){
			try{
				Integer.valueOf(phoneWorkTextField.getText());
			} catch (NumberFormatException e){
				return false;
			}
		}
		
		if(!cellPhoneTextField.getText().isEmpty()){
			try{
				Integer.valueOf(cellPhoneTextField.getText());
			} catch (NumberFormatException e){
				return false;
			}
		}
		
		/*
		 * TODO Treba pridat validaciu emailov
		 */
		
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
