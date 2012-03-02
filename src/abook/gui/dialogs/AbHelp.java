package abook.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * @author jurij
 */
@SuppressWarnings("serial")
public class AbHelp extends JFrame {

    protected JTextPane textovePole;
    protected SimpleAttributeSet hlavniNadpis;
    protected SimpleAttributeSet vedlejsiNadpis;
    protected SimpleAttributeSet text;
    protected JPanel panel;

    public AbHelp() {
        super("GJA 2012 - o programu");
        setLayout(new BorderLayout());

        Point stred = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int sirkaOkna = 680;
        int vyskaOkna = 360;
        setBounds((stred.x - sirkaOkna / 2), (stred.y - vyskaOkna / 2), sirkaOkna, vyskaOkna);

        // textove pole //
        textovePole = new JTextPane();
        textovePole.setEditable(false);

        // zainicializuju si styly //
        initializeStyles();
        textovePole.setCharacterAttributes(hlavniNadpis, true);
        textovePole.setText("Projekt pro předmět GJA 2012 - VUT FIT Brno\n");

        addText("\nAutori:\n", vedlejsiNadpis);
        addText(" > Radovan Dvorský ... xdvors08\n", text);
        addText(" > Jiří Hynek ... xhynek05\n", text);
        addText(" > Jiří Janda ... xjanda17\n", text);
        addText(" > Marek Mešár - xmesar00\n", text);

        addText("\nVzhled:\n", vedlejsiNadpis);
        addText(" > JTattoo swing: www.jtattoo.net\n", text);
        addText(" > Ikony: .....\n", text);

        addText("\nWebsite:\n", vedlejsiNadpis);
        addText(" > www.zeruazprasknu.cz\n", text);


        panel = new JPanel(new BorderLayout());
        //panel.setBackground(Color.white);
        panel.setOpaque(false);
        //textovePole.setBackground(Color.white);
        textovePole.setOpaque(false);
        panel.add(textovePole,BorderLayout.CENTER);
        JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/icons/duck.png")));
        //label.setBackground(Color.white);
        panel.setOpaque(false);
        panel.add(label,BorderLayout.EAST);

        add(panel,BorderLayout.CENTER);
        
        setVisible(true);

    }

    /** Prida text se zadanym stylem do textovehoPole
     *
     * @param text
     * @param set
     */
    private void addText(String text, SimpleAttributeSet set) {
        try {
                Document doc = textovePole.getStyledDocument();
                doc.insertString(doc.getLength(), text , set);
        } catch (BadLocationException ex) {
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Uz fakt nepobiram, co je tohle za vyjimku :/ ;).");
        }
    }

    /**
     * Zainicializuje styly
     */
    private void initializeStyles() {

       // h1 //
       hlavniNadpis = new SimpleAttributeSet();
       StyleConstants.setBold(hlavniNadpis, true);
       StyleConstants.setItalic(hlavniNadpis,true);
       StyleConstants.setFontSize(hlavniNadpis, 22);
       StyleConstants.setForeground(hlavniNadpis, new Color(109,115,232));
       StyleConstants.setAlignment(hlavniNadpis, StyleConstants.ALIGN_CENTER);

       // h2 //
       vedlejsiNadpis = new SimpleAttributeSet();
       StyleConstants.setBold(vedlejsiNadpis, true);
       StyleConstants.setItalic(vedlejsiNadpis,true);
       StyleConstants.setFontSize(vedlejsiNadpis, 16);
       StyleConstants.setForeground(vedlejsiNadpis, Color.green);

       // text //
       text = new SimpleAttributeSet();
       StyleConstants.setItalic(text,true);
       StyleConstants.setFontSize(text, 12);
       StyleConstants.setForeground(text, Color.orange);

    }

}