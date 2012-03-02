package abook.gui.dialogs;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AbDialogs {
	
	/**
	 * Yes/no dialog
	 */
	public static int YesNoCancel(String question) {
		JFrame frame = new JFrame();
		int result = JOptionPane.showConfirmDialog(frame, question);
		return result;
	}
	
	
	/** Default report
	 *
	 * @param s
	 */
	public static void report(String s) {
		JFrame parent = new JFrame();
		JOptionPane.showMessageDialog(parent, s);
	}
	
	/**
	 * 
	 * @param options
	 * @param question
	 * @return
	 */
	public static int select(String[] options, String question) {
		JFrame frame = new JFrame();

        String result = new String();
        result = (String) JOptionPane.showInputDialog(frame, question,
                "Input", JOptionPane.QUESTION_MESSAGE,
        null, options, "Home");
        
        if(result != null) {
        	for(int i = 0; i < options.length; i++) {
        		if(result.equals(options[i])) return i;
        	}
        }
        
        return -1;
    }
	
	/**
	 *
	 * @param question
	 * @return
	 */
	public static String input(String question) {
		JFrame frame = new JFrame();

        String result;
        result = (String) JOptionPane.showInputDialog(frame, question, "Input", JOptionPane.QUESTION_MESSAGE);

        return result;
    }
}
