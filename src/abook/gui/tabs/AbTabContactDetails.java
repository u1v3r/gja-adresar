package abook.gui.tabs;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class AbTabContactDetails extends JPanel implements AbITabComponent{

	/**
	 * Create the panel.
	 */
	public AbTabContactDetails() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.WEST);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.WEST);
		
		JButton button = new JButton("");
		button.setPreferredSize(new Dimension(100, 100));
		panel_2.add(button);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new MigLayout("", "[]", "[][][]"));
		
		JLabel contactNameLabel = new JLabel("Radovan Dvorský");
		contactNameLabel.setFont(new Font("Bitstream Vera Sans", contactNameLabel.getFont().getStyle() | Font.BOLD, 20));
		panel_3.add(contactNameLabel, "cell 0 1");
		
		JLabel emailHeadLabel = new JLabel("rdvorsky@mail.com");
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
		
		JLabel label = new JLabel("0314654789");
		label.setForeground(new Color(0, 0, 0));
		label.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(label, "cell 1 1");
		
		JLabel label_6 = new JLabel("Práca:");
		label_6.setForeground(new Color(0, 0, 0));
		label_6.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(label_6, "cell 0 2");
		
		JLabel label_2 = new JLabel("0314654789");
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(label_2, "cell 1 2");
		
		JLabel label_1 = new JLabel("Mobil:");
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(label_1, "cell 0 3");
		
		JLabel label_3 = new JLabel("0314654789");
		label_3.setForeground(new Color(0, 0, 0));
		label_3.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_5.add(label_3, "cell 1 3");
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(245, 245, 245));
		panel_4.add(panel_6, "cell 0 1,grow");
		panel_6.setLayout(new MigLayout("", "[][]", "[][][][]"));
		
		JLabel lblInternet = new JLabel("Internet");
		lblInternet.setForeground(new Color(0, 0, 0));
		panel_6.add(lblInternet, "cell 0 0");
		
		JLabel lblEmailDomov = new JLabel("E-Mail domov:");
		lblEmailDomov.setForeground(new Color(0, 0, 0));
		lblEmailDomov.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblEmailDomov, "cell 0 1");
		
		JLabel lblRdvorskygmailcom = new JLabel("rdvorsky@gmail.com");
		lblRdvorskygmailcom.setForeground(new Color(0, 0, 0));
		lblRdvorskygmailcom.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblRdvorskygmailcom, "cell 1 1");
		
		JLabel lblEmailPrca = new JLabel("E-Mail práca:");
		lblEmailPrca.setForeground(new Color(0, 0, 0));
		lblEmailPrca.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblEmailPrca, "cell 0 2");
		
		JLabel label_7 = new JLabel("rdvorsky@gmail.com");
		label_7.setForeground(new Color(0, 0, 0));
		label_7.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(label_7, "cell 1 2");
		
		JLabel lblChat = new JLabel("Chat:");
		lblChat.setForeground(new Color(0, 0, 0));
		lblChat.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_6.add(lblChat, "cell 0 3");
		
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
		
		JLabel lblNejakUlica = new JLabel("Nejaká ulica 123");
		lblNejakUlica.setForeground(new Color(0, 0, 0));
		lblNejakUlica.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblNejakUlica, "cell 1 1");
		
		JLabel lblMesto = new JLabel("Mesto:");
		lblMesto.setForeground(new Color(0, 0, 0));
		lblMesto.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblMesto, "cell 0 2");
		
		JLabel lblPraha = new JLabel("Praha");
		lblPraha.setForeground(new Color(0, 0, 0));
		lblPraha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPraha.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblPraha, "cell 1 2");
		
		JLabel lblPs = new JLabel("PSČ:");
		lblPs.setForeground(new Color(0, 0, 0));
		lblPs.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblPs, "cell 0 3");
		
		JLabel label_10 = new JLabel("45045");
		label_10.setForeground(new Color(0, 0, 0));
		label_10.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_10.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(label_10, "cell 1 3");
		
		JLabel lblKrajina = new JLabel("Krajina:");
		lblKrajina.setForeground(new Color(0, 0, 0));
		lblKrajina.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblKrajina, "cell 0 4");
		
		JLabel lblSlovensko = new JLabel("Slovensko");
		lblSlovensko.setForeground(new Color(0, 0, 0));
		lblSlovensko.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_7.add(lblSlovensko, "cell 1 4");
		
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
		
		JLabel label_9 = new JLabel("21.2.2001");
		label_9.setForeground(new Color(0, 0, 0));
		label_9.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_8.add(label_9, "cell 1 1");

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

}
