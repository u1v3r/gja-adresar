package abook.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abook.AbIGuiComponent;

public class AbStatus implements AbIGuiComponent {
	
	protected JPanel panel;
	protected JTextField text;
	
	/**
     * Constructor makes status panel
     */
    AbStatus() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        //stavovaLista.setBackground(Color.DARK_GRAY);

        text = new JTextField();
        //stavTabu.setSize(200, 30);
        text.setEditable(false);
        text.setText("Misto pro vypis stavu: ");
        //stavTabu.setBackground(Color.YELLOW);
        panel.add(text, BorderLayout.CENTER);
    }

    /** Vrati stavovou listu pro dalsi pouziti.
     *
     * @return statvovaLista
     */
    public Component getWidget() {
        return panel;
    }

}
