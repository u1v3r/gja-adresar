package abook.gui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.others.SortedListModel;
import abook.profile.AbPerson;
import abook.profile.InitProfile;


/**
 * 
 * @author Radovan Dvorský
 *
 */
@SuppressWarnings("serial")
public class AbTabContactDetails extends JPanel implements AbITabComponent{


	private final class ContactDetailsListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
				
			// pri kliknuti su vyslane dva eventy, treba zachytit len jeden
			if(e.getValueIsAdjusting()){
				return;
			}
			
			if(usersList.getSelectedIndex() != -1){
				fillContactDetails(contactsList.get(usersList.getSelectedIndex()));
			}
		}

	}

	private JButton userImagebutton;
	private JLabel birthdayLabel;
	private JLabel countryLabel;
	private JLabel pscLabel;
	private JLabel cityLabel;
	private JLabel streetLabel;
	private JLabel emailWorkLabel;
	private JLabel emailHomeLabel;
	private JLabel cellPhoneLabel;
	private JLabel phoneWorkLabel;
	private JLabel phoneHomeLabel;
	private JLabel emailHeadLabel;
	private JLabel contactNameHeadLabel;
	private JLabel icqLabel;
	private JLabel skypeLabel;
	private JLabel jabberLabel;
	private JLabel gtalkLabel;
	private JList usersList;
	
	private SortedListModel usersListModel;
	private List<AbPerson> contactsList;

			
	/**
	 * Create the panel.
	 */
	public AbTabContactDetails() {
		setPreferredSize(new Dimension(750, 600));		
			
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.WEST);
		
		usersList = new JList();
		usersList.setFixedCellWidth(210);
		usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersList.addListSelectionListener(new ContactDetailsListener());
		scrollPane.setViewportView(usersList);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.WEST);
		
		userImagebutton = new JButton("");
		userImagebutton.setPreferredSize(new Dimension(100, 100));		
		panel_2.add(userImagebutton);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new MigLayout("", "[]", "[][][]"));
		
		contactNameHeadLabel = new JLabel("");
		contactNameHeadLabel.setFont(new Font("DejaVu Sans", contactNameHeadLabel.getFont().getStyle() | Font.BOLD, 20));
		panel_3.add(contactNameHeadLabel, "cell 0 1");
		
		emailHeadLabel = new JLabel("");
		panel_3.add(emailHeadLabel, "cell 0 2");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(105, 105, 105));
		scrollPane_1.setViewportView(panel_4);
		panel_4.setLayout(new MigLayout("", "[208.00,grow]", "[grow][grow][grow][grow]"));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(245, 245, 245));
		panel_4.add(panel_5, "cell 0 0,grow");
		panel_5.setLayout(new MigLayout("", "[][]", "[][][][]"));
		
		JLabel label_4 = new JLabel("Tel. čísla");
		label_4.setForeground(new Color(0, 0, 0));
		panel_5.add(label_4, "cell 0 0");
		
		JLabel label_5 = new JLabel("Domov:");
		label_5.setForeground(new Color(0, 0, 0));
		label_5.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(label_5, "cell 0 1");
		
		phoneHomeLabel = new JLabel("");
		phoneHomeLabel.setForeground(new Color(0, 0, 0));
		phoneHomeLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(phoneHomeLabel, "cell 1 1");
		
		JLabel label_6 = new JLabel("Práca:");
		label_6.setForeground(new Color(0, 0, 0));
		label_6.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(label_6, "cell 0 2");
		
		phoneWorkLabel = new JLabel("");
		phoneWorkLabel.setForeground(new Color(0, 0, 0));
		phoneWorkLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(phoneWorkLabel, "cell 1 2");
		
		JLabel label_1 = new JLabel("Mobil:");
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(label_1, "cell 0 3");
		
		cellPhoneLabel = new JLabel("");
		cellPhoneLabel.setForeground(new Color(0, 0, 0));
		cellPhoneLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(cellPhoneLabel, "cell 1 3");
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(245, 245, 245));
		panel_4.add(panel_6, "cell 0 1,grow");
		panel_6.setLayout(new MigLayout("", "[][140.00][][][]", "[][][][][]"));
		
		JLabel lblInternet = new JLabel("Internet");
		lblInternet.setForeground(new Color(0, 0, 0));
		panel_6.add(lblInternet, "cell 0 0");
		
		JLabel lblEmailDomov = new JLabel("E-Mail domov:");
		lblEmailDomov.setForeground(new Color(0, 0, 0));
		lblEmailDomov.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblEmailDomov, "cell 0 1");
		
		emailHomeLabel = new JLabel("");
		emailHomeLabel.setForeground(new Color(0, 0, 0));
		emailHomeLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(emailHomeLabel, "cell 1 1");
		
		JLabel lblIcq = new JLabel("ICQ");
		lblIcq.setForeground(Color.BLACK);
		lblIcq.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblIcq, "cell 3 1");
		
		icqLabel = new JLabel("");
		icqLabel.setForeground(Color.BLACK);
		icqLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(icqLabel, "cell 4 1");
		
		JLabel lblEmailPrca = new JLabel("E-Mail práca:");
		lblEmailPrca.setForeground(new Color(0, 0, 0));
		lblEmailPrca.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblEmailPrca, "cell 0 2");
		
		emailWorkLabel = new JLabel("");
		emailWorkLabel.setForeground(new Color(0, 0, 0));
		emailWorkLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(emailWorkLabel, "cell 1 2");
		
		JLabel lblSkype = new JLabel("Skype");
		lblSkype.setForeground(Color.BLACK);
		lblSkype.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblSkype, "cell 3 2");
		
		skypeLabel = new JLabel("");
		skypeLabel.setForeground(Color.BLACK);
		skypeLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(skypeLabel, "cell 4 2");
		
		JLabel imLabel = new JLabel("");
		panel_6.add(imLabel, "cell 1 3");
		
		JLabel lblJabber = new JLabel("Jabber");
		lblJabber.setForeground(Color.BLACK);
		lblJabber.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblJabber, "cell 3 3");
		
		jabberLabel = new JLabel("");
		jabberLabel.setForeground(Color.BLACK);
		jabberLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(jabberLabel, "cell 4 3");
		
		JLabel lblGtalk = new JLabel("Gtalk");
		lblGtalk.setForeground(Color.BLACK);
		lblGtalk.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblGtalk, "cell 3 4");
		
		gtalkLabel = new JLabel("");
		gtalkLabel.setForeground(Color.BLACK);
		gtalkLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(gtalkLabel, "cell 4 4");
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(245, 245, 245));
		panel_4.add(panel_7, "cell 0 2,grow");
		panel_7.setLayout(new MigLayout("", "[][]", "[][][][][]"));
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setForeground(new Color(0, 0, 0));
		panel_7.add(lblAdresa, "cell 0 0");
		
		JLabel lblUlica = new JLabel("Ulica:");
		lblUlica.setForeground(new Color(0, 0, 0));
		lblUlica.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblUlica, "cell 0 1");
		
		streetLabel = new JLabel("");
		streetLabel.setForeground(new Color(0, 0, 0));
		streetLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(streetLabel, "cell 1 1");
		
		JLabel lblMesto = new JLabel("Mesto:");
		lblMesto.setForeground(new Color(0, 0, 0));
		lblMesto.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblMesto, "cell 0 2");
		
		cityLabel = new JLabel("");
		cityLabel.setForeground(new Color(0, 0, 0));
		cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cityLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(cityLabel, "cell 1 2");
		
		JLabel lblPs = new JLabel("PSČ:");
		lblPs.setForeground(new Color(0, 0, 0));
		lblPs.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblPs, "cell 0 3");
		
		pscLabel = new JLabel("");
		pscLabel.setForeground(new Color(0, 0, 0));
		pscLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		pscLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(pscLabel, "cell 1 3");
		
		JLabel lblKrajina = new JLabel("Krajina:");
		lblKrajina.setForeground(new Color(0, 0, 0));
		lblKrajina.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblKrajina, "cell 0 4");
		
		countryLabel = new JLabel("");
		countryLabel.setForeground(new Color(0, 0, 0));
		countryLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(countryLabel, "cell 1 4");
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(245, 245, 245));
		panel_4.add(panel_8, "cell 0 3,grow");
		panel_8.setLayout(new MigLayout("", "[][]", "[][]"));
		
		JLabel lblOsobn = new JLabel("Osobné");
		lblOsobn.setForeground(new Color(0, 0, 0));
		panel_8.add(lblOsobn, "cell 0 0");
		
		JLabel lblNarodeniny = new JLabel("Narodeniny:");
		lblNarodeniny.setForeground(new Color(0, 0, 0));
		lblNarodeniny.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_8.add(lblNarodeniny, "cell 0 1");
		
		birthdayLabel = new JLabel("");
		birthdayLabel.setForeground(new Color(0, 0, 0));
		birthdayLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_8.add(birthdayLabel, "cell 1 1");
		
		
		actualizeTab();
	}

	public void fillContactDetails(AbPerson person) {
		
		if(person.getUserImage() != null){
			userImagebutton.setIcon(new ImageIcon(person.getUserImage()));
		}
		
		contactNameHeadLabel.setText(person.getFullname());
		emailHeadLabel.setText(person.getEmailHome());
		phoneHomeLabel.setText(person.getPhoneHome());
		phoneWorkLabel.setText(person.getPhoneWork());
		cellPhoneLabel.setText(person.getCellPhone());
		emailHomeLabel.setText(person.getEmailHome());
		emailWorkLabel.setText(person.getEmailWork());
		icqLabel.setText(person.getIcq());
		skypeLabel.setText(person.getSkype());
		jabberLabel.setText(person.getGtalk());
		streetLabel.setText(person.getStreet());
		cityLabel.setText(person.getCity());
		pscLabel.setText(person.getPsc());
		countryLabel.setText(person.getCountry());		
		
		if(person.getBirthday() != null){
			SimpleDateFormat format = new SimpleDateFormat(AbPerson.DATE_FORMAT);
			birthdayLabel.setText(format.format(person.getBirthday()));
		}		
	}

	@Override
	public JComponent getWidget() {
		return this;
	}

	@Override
	public String getTooltip() {
		return "Podrobný poľad na kontakty";
	}
	
	@Override
	public String getName() {
		return "Detail";
	}

	@Override
    public void actualizeTab() {
		
		usersListModel = new SortedListModel();		
		
		contactsList = InitProfile.getProfile().getListOfAbPersons();
		contactsList.clear();
		for (AbPerson person : contactsList) {
			if(person.getFullname() != null){
				usersListModel.addElement(person.getFullname());	
			}
		}
			
		usersList.setModel(usersListModel);		
		
		usersList.repaint();
    }
}
