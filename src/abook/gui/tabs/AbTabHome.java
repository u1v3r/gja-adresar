package abook.gui.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Tab with help.
 * 
 * @author jurij
 *
 */
public class AbTabHome implements AbITabComponent, DocumentListener {
	
	protected final String name = "Home";
	protected final String tooltip = "Home card";
	
	protected JScrollPane panel;
	protected JTextField searchArea;
	protected JTextPane textField;
	protected JPanel searchPanel;
	protected SimpleAttributeSet h1;
	protected SimpleAttributeSet h2;
	protected SimpleAttributeSet text;
	protected int actIndex;
	protected int index;
	protected int end;
	protected String content;
	protected String contentLowerCase;
	protected JCheckBox caseSensitiveBox;

	final static Color ZVYRAZNI = Color.YELLOW;
	final static Color NENALEZENO = Color.RED;
	final Color searchBackground;
	protected Highlighter highlighter;
	protected Highlighter.HighlightPainter painter;
	
	/**
	 * Creates content.
	 */
	public AbTabHome() {
		
		// initialize styles and variables //
	    initializeStyles();
	    actIndex = 0;

	    // create text field //
	    textField = new JTextPane();
	    textField.setEditable(false);

	    // crate search field //
	    searchArea = new JTextField();
	    searchArea.setMaximumSize(new Dimension(1000, 50));
	    searchArea.setToolTipText("tooltip");
	    searchArea.setText("Set search pattern...");

	    // create search panel with buttons and search area//
	    searchPanel = new JPanel();
	    searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
	    JPanel panelNorth = new JPanel();
	    panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));
	    caseSensitiveBox = new JCheckBox("CaseSensitive", false);
	    JButton nextButton = new JButton();
	    nextButton.setAction(new ActionNextFind());
	    nextButton.setText("dalsi");
	    panelNorth.add(searchArea);
	    panelNorth.add(caseSensitiveBox);
	    panelNorth.add(nextButton);
	    searchPanel.add(panelNorth, BorderLayout.NORTH);
	    searchPanel.add(textField, BorderLayout.SOUTH);

	    panel = new JScrollPane(searchPanel); //scroll bar

	    textField.setCharacterAttributes(h1, true);
	    textField.setText("Welcome in program aBook (GJA 2012)!\n");

	    addText("\n  This panel is used as guidebook of this program.\n", text);
	    addText("\nOrientation in program", h2);
	    addText("\n  Program contains of next parts:\n" +
	            "    > ToolBar, which is on the top of the program\n" +
	            "    > Sidebar, which contains files tree, list of groups and search field\n" +
	            "    > Status bar in the bottom of the program shows status of program\n", text);
	    addText("\n\nThis is help template ...\n" +
	            "  > there will be next advice\n" +
	            "  > (this text area is in scroll pane)\n" +
	            "  > ...", text);

	    // highlighting //
	    highlighter = new DefaultHighlighter();
	    painter = new DefaultHighlighter.DefaultHighlightPainter(ZVYRAZNI);
	    textField.setHighlighter(highlighter);
	    content = textField.getText();
	    contentLowerCase = content.toLowerCase();

	    searchBackground = searchArea.getBackground();
	    searchArea.getDocument().addDocumentListener(this);

	    InputMap im = searchArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap am = searchArea.getActionMap();
	    im.put(KeyStroke.getKeyStroke("ESCAPE"), "cancel-search");
	    am.put("cancel-search", new CancelFinding());
	}
	
	/**
	 * Method adds text with selected style to the text field.
     *
     * @param text
     * @param set
     */
    private void addText(String text, SimpleAttributeSet set) {
        try {
            StyledDocument doc = textField.getStyledDocument();
            doc.insertString(doc.getLength(), text , set);
        } catch (BadLocationException ex) {
        	// do nothing //
        }
    }

    /**
     * Method initializes styles for text of help.
     */
    private void initializeStyles() {

       // h1 //
       h1 = new SimpleAttributeSet();
       StyleConstants.setBold(h1, true);
       StyleConstants.setItalic(h1,true);
       StyleConstants.setFontSize(h1, 22);
       StyleConstants.setForeground(h1, new Color(109,115,232));

       // h2 //
       h2 = new SimpleAttributeSet();
       StyleConstants.setBold(h2, true);
       StyleConstants.setItalic(h2,true);
       StyleConstants.setFontSize(h2, 16);
       StyleConstants.setForeground(h2, new Color(109,232,129));

       // text //
       text = new SimpleAttributeSet();
       StyleConstants.setItalic(text,true);
       StyleConstants.setFontSize(text, 12);
       StyleConstants.setForeground(text, new Color(232,190,109));
    }

    /**
     * Method finds selected pattern in text field.
     */
    private void find() {
    	
        highlighter.removeAllHighlights();

        String vstup = searchArea.getText();
        if (vstup.length() <= 0) { return; }

        if(caseSensitiveBox.isSelected()) { index = content.indexOf(vstup, actIndex); }
        else { index = contentLowerCase.indexOf(vstup, actIndex); }

        // find selected pattern in content of text field //
        if (index >= 0) {
            try {
                end = index + vstup.length();
                highlighter.addHighlight(index, end, painter);
                textField.setCaretPosition(end);
                searchArea.setBackground(searchBackground);
            } catch (BadLocationException e) { e.printStackTrace(); }
        } else {
            actIndex = 0;
            end = 0;
            searchArea.setBackground(NENALEZENO);
        }
    }

    /**
     * Action set search area to default mode.
     */
    @SuppressWarnings("serial")
    class CancelFinding extends AbstractAction {
        public void actionPerformed(ActionEvent ev) {
            highlighter.removeAllHighlights();
            searchArea.setText("");
            searchArea.setBackground(searchBackground);
        }
    }

    /**
     * Method actualizes color of background.
     */
    public void changeColor() {
        //textovePole.setBackground(InitProfile.getIjaVariables().getBarvaPozadi());
        textField.repaint();
    }

    /**
     * Action finds next occurrence of pattern.
     */
    @SuppressWarnings("serial")
    class ActionNextFind extends AbstractAction
    {
        public void actionPerformed(ActionEvent e) {
            actIndex = end;
            find();
        }
    }

	@Override
	public JComponent getWidget() {
		return panel;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTooltip() {
		return tooltip;
	}

	@Override
    public void actualizeTab() {
		// do nothing //
    }
	
    @Override
    public void insertUpdate(DocumentEvent e) {
        find();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        find();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    	// do nothing //
    }

}
