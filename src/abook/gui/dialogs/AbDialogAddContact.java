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
import net.miginfocom.swing.MigLayout;

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
	private Image userImage;
	private JTextField namePrefixTextField;
	private JTextField nameSuffixTextField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;

	/**
	 * Create the dialog.
	 */
	public AbDialogAddContact() {
		
		this.profile = InitProfile.getProfile();
		this.groupListModel = new DefaultListModel();
		
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		setBounds(100, 100, 928, 732);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel formPanelHead = new JPanel();
				panel.add(formPanelHead, BorderLayout.CENTER);
				formPanelHead.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow][17px,grow]"));
				{
					JPanel panel_1 = new JPanel();
					formPanelHead.add(panel_1, "cell 0 0,grow");
					panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
					{
						JButton button = new JButton(" ");
						button.setBorder(null);
						button.setPreferredSize(new Dimension(100, 100));
						button.setMargin(new Insets(10, 14, 2, 14));
						panel_1.add(button);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					formPanelHead.add(panel_1, "cell 1 0,grow");
					panel_1.setLayout(new FormLayout(new ColumnSpec[] {
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
							FormFactory.DEFAULT_ROWSPEC,}));
					{
						JLabel label = new JLabel("Jméno kontaktu");
						label.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(label, "2, 2");
					}
					{
						JLabel label = new JLabel("Titul");
						panel_1.add(label, "2, 4, right, default");
					}
					{
						namePrefixTextField = new JTextField();
						namePrefixTextField.setColumns(10);
						panel_1.add(namePrefixTextField, "4, 4, fill, default");
					}
					{
						JLabel label = new JLabel("Jméno");
						panel_1.add(label, "2, 6, right, default");
					}
					{
						nameSuffixTextField = new JTextField();
						nameSuffixTextField.setColumns(10);
						panel_1.add(nameSuffixTextField, "4, 6, fill, default");
					}
					{
						JLabel label = new JLabel("Příjmení");
						panel_1.add(label, "2, 8, right, default");
					}
					{
						textField_2 = new JTextField();
						textField_2.setColumns(10);
						panel_1.add(textField_2, "4, 8, fill, default");
					}
					{
						JLabel label = new JLabel("Titul za");
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
							FormFactory.DEFAULT_ROWSPEC,}));
					{
						JLabel label = new JLabel("Adresa");
						label.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(label, "2, 2");
					}
					{
						JLabel label = new JLabel("Ulice");
						panel_1.add(label, "2, 4, right, default");
					}
					{
						textField_4 = new JTextField();
						textField_4.setColumns(10);
						panel_1.add(textField_4, "4, 4, fill, default");
					}
					{
						JLabel label = new JLabel("Město");
						panel_1.add(label, "2, 6, right, default");
					}
					{
						textField_5 = new JTextField();
						textField_5.setColumns(10);
						panel_1.add(textField_5, "4, 6, fill, default");
					}
					{
						JLabel label = new JLabel("PSČ");
						panel_1.add(label, "2, 8, right, default");
					}
					{
						textField_6 = new JTextField();
						textField_6.setColumns(10);
						panel_1.add(textField_6, "4, 8, fill, default");
					}
					{
						JLabel label = new JLabel("Krajina");
						panel_1.add(label, "2, 10, right, default");
					}
					{
						textField_7 = new JTextField();
						textField_7.setColumns(10);
						panel_1.add(textField_7, "4, 10, fill, default");
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel_1.setPreferredSize(new Dimension(10, 80));
					formPanelHead.add(panel_1, "cell 0 1,grow");
					panel_1.setLayout(new FormLayout(new ColumnSpec[] {
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
							FormFactory.DEFAULT_ROWSPEC,}));
					{
						JLabel label = new JLabel("Tel. čísla");
						label.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(label, "2, 2");
					}
					{
						JLabel label = new JLabel("Domov");
						panel_1.add(label, "2, 4, right, default");
					}
					{
						textField = new JTextField();
						textField.setColumns(10);
						panel_1.add(textField, "4, 4, fill, default");
					}
					{
						JLabel label = new JLabel("Práca");
						panel_1.add(label, "2, 6, right, default");
					}
					{
						textField_1 = new JTextField();
						textField_1.setColumns(10);
						panel_1.add(textField_1, "4, 6, fill, default");
					}
					{
						JLabel label = new JLabel("Mobil");
						panel_1.add(label, "2, 8, right, default");
					}
					{
						textField_8 = new JTextField();
						textField_8.setColumns(10);
						panel_1.add(textField_8, "4, 8, fill, default");
					}
				}
				{
					JPanel panel_1 = new JPanel();
					formPanelHead.add(panel_1, "cell 1 1,grow");
					panel_1.setLayout(new FormLayout(new ColumnSpec[] {
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
							FormFactory.DEFAULT_ROWSPEC,}));
					{
						JLabel label = new JLabel("E-Mail");
						label.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(label, "2, 2");
					}
					{
						JLabel label = new JLabel("Domov");
						panel_1.add(label, "2, 4, right, default");
					}
					{
						textField_9 = new JTextField();
						textField_9.setColumns(10);
						panel_1.add(textField_9, "4, 4, fill, default");
					}
					{
						JLabel label = new JLabel("Práca");
						panel_1.add(label, "2, 6, right, default");
					}
					{
						textField_10 = new JTextField();
						textField_10.setColumns(10);
						panel_1.add(textField_10, "4, 6, fill, default");
					}
				}
				{
					JPanel panel_1 = new JPanel();
					formPanelHead.add(panel_1, "cell 2 1,grow");
					panel_1.setLayout(new FormLayout(new ColumnSpec[] {
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
							FormFactory.DEFAULT_ROWSPEC,}));
					{
						JLabel label = new JLabel("IM");
						label.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(label, "2, 2");
					}
					{
						JLabel label = new JLabel("ICQ");
						panel_1.add(label, "2, 4, right, default");
					}
					{
						textField_11 = new JTextField();
						textField_11.setColumns(10);
						panel_1.add(textField_11, "4, 4, fill, default");
					}
					{
						JLabel label = new JLabel("Skype");
						panel_1.add(label, "2, 6, right, default");
					}
					{
						textField_12 = new JTextField();
						textField_12.setColumns(10);
						panel_1.add(textField_12, "4, 6, fill, default");
					}
					{
						JLabel label = new JLabel("GTalk");
						panel_1.add(label, "2, 8, right, default");
					}
					{
						textField_14 = new JTextField();
						textField_14.setColumns(10);
						panel_1.add(textField_14, "4, 8, fill, default");
					}
					{
						JLabel label = new JLabel("Jabber");
						panel_1.add(label, "2, 10, right, default");
					}
					{
						textField_13 = new JTextField();
						textField_13.setColumns(10);
						panel_1.add(textField_13, "4, 10, fill, default");
					}
				}
				{
					JPanel panel_1 = new JPanel();
					formPanelHead.add(panel_1, "cell 0 2,grow");
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
						JLabel label = new JLabel("Osobné");
						label.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(label, "2, 2");
					}
					{
						JLabel label = new JLabel("Narodeniny");
						panel_1.add(label, "2, 4");
					}
					{
						JDateChooser dateChooser = new JDateChooser();
						panel_1.add(dateChooser, "4, 4, fill, fill");
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
						JLabel label = new JLabel("Skupiny");
						label.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(label, "2, 2");
					}
					{
						JScrollPane scrollPane = new JScrollPane();
						panel_1.add(scrollPane, "4, 4, fill, fill");
					}
				}
				{
					JPanel panel_1 = new JPanel();
					formPanelHead.add(panel_1, "cell 2 2,grow");
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
						JLabel label = new JLabel("Poznámka");
						label.setFont(new Font("Dialog", Font.BOLD, 14));
						panel_1.add(label, "2, 2");
					}
					{
						JTextArea textArea = new JTextArea();
						textArea.setWrapStyleWord(true);
						textArea.setLineWrap(true);
						textArea.setColumns(20);
						panel_1.add(textArea, "4, 4, fill, fill");
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
		
		List<Component> focusList = new ArrayList<Component>();
		
		setFocusTraversalPolicy(new AddContactFormFocusTravel(focusList));
		
		
	}
	
	
	/**
	 * Inicializuje Jlist pre vyber skupin
	 */
	private void initGroupList() {
		
		for (AbGroup group : profile.getListOfGroups()) {
			groupListModel.addElement(group.getGroupName());
		}
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
}
