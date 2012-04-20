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
 * Creates dialog with informations about program.
 *
 * @author xjanda17
 */
@SuppressWarnings("serial")
public class AbHelp extends JFrame {

    protected JTextPane textField;
    protected SimpleAttributeSet mainTitle;
    protected SimpleAttributeSet subTitle;
    protected SimpleAttributeSet text;
    protected JPanel panel;

    /**
     * Creates new dialog.
     */
    public AbHelp() {
        super("GJA 2012 - about program");
        setLayout(new BorderLayout());

        // show in the middle of screen //
        Point middle = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int frameWidth = 680;
        int frameHeight = 360;
        setBounds((middle.x - frameWidth / 2), (middle.y - frameHeight / 2), frameWidth, frameHeight);

        // text field //
        textField = new JTextPane();
        textField.setEditable(false);

        // initialize styles //
        initializeStyles();
        textField.setCharacterAttributes(mainTitle, true);
        textField.setText("Project for GJA 2012 course - BUT FIT\n");

        // add text //
        addText("\nAuthors:\n", subTitle);
        addText(" > Radovan Dvorský ... xdvors08\n", text);
        addText(" > Jiří Hynek ... xhynek05\n", text);
        addText(" > Jiří Janda ... xjanda17\n", text);
        addText(" > Marek Mešár - xmesar00\n", text);

        addText("\nLook and feel:\n", subTitle);
        addText(" > JTattoo swing: www.jtattoo.net\n", text);
        addText(" > Icons: .....\n", text);

        addText("\nWebsite:\n", subTitle);
        addText(" > www.zeruazprasknu.cz\n", text);

        // add content to parent frames //
        panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        textField.setOpaque(false);
        panel.add(textField,BorderLayout.CENTER);
        JLabel label = new JLabel(new ImageIcon(this.getClass().getResource("/icons/duck.png")));
        panel.setOpaque(false);
        panel.add(label,BorderLayout.EAST);

        add(panel,BorderLayout.CENTER);
        
        setVisible(true);

    }

    /**
     * Adds text with selected style to text field.
     *
     * @param text
     * @param set
     */
    private void addText(String text, SimpleAttributeSet set) {
        try {
                Document doc = textField.getStyledDocument();
                doc.insertString(doc.getLength(), text , set);
        } catch (BadLocationException ex) {
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Uz fakt nepobiram, co je tohle za vyjimku :/ ;).");
        }
    }

    /**
     * Initializes styles.
     */
    private void initializeStyles() {

       // h1 //
       mainTitle = new SimpleAttributeSet();
       StyleConstants.setBold(mainTitle, true);
       StyleConstants.setItalic(mainTitle,true);
       StyleConstants.setFontSize(mainTitle, 22);
       StyleConstants.setForeground(mainTitle, new Color(109,115,232));
       StyleConstants.setAlignment(mainTitle, StyleConstants.ALIGN_CENTER);

       // h2 //
       subTitle = new SimpleAttributeSet();
       StyleConstants.setBold(subTitle, true);
       StyleConstants.setItalic(subTitle,true);
       StyleConstants.setFontSize(subTitle, 16);
       StyleConstants.setForeground(subTitle, Color.green);

       // text //
       text = new SimpleAttributeSet();
       StyleConstants.setItalic(text,true);
       StyleConstants.setFontSize(text, 12);
       StyleConstants.setForeground(text, Color.orange);

    }

}