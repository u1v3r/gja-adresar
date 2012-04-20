package abook.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.InitProfile;

/**
 * Bottom panel with status.
 * 
 * @author jurij
 *
 */
public class AbStatus implements AbIGuiComponent, AbListener {
	
	protected JPanel panel;
	protected JTextField text;
	
	/**
     * Constructor makes status panel.
     */
    AbStatus() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        //stavovaLista.setBackground(Color.DARK_GRAY);

        text = new JTextField();
        //stavTabu.setSize(200, 30);
        text.setEditable(false);
        //stavTabu.setBackground(Color.YELLOW);
        panel.add(text, BorderLayout.CENTER);
        actualizeText();
        
        InitListenerCore.getListenerCore().addListener(this);
    }
    
    /**
     * Method actualizes status text.
     */
    private void actualizeText() {
    	text.setText("Profile: " + InitProfile.getProfile().getUserName() + ",       Workspace: " + InitProfile.getWorkspace());
    }

    @Override
    public JComponent getWidget() {
        return panel;
    }

	@Override
    public void myEventOccurred(AbEvent evt, int type) {
	    
		if(type == AbListener.PROFILE_CHANGED) {
			actualizeText();
		}
	    
    }

}
