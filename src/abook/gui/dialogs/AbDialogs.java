package abook.gui.dialogs;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class with static dialog methods.
 * 
 * @author jurij
 *
 */
public class AbDialogs {
	
	/**
	 * Yes/no dialog
	 */
	public static int YesNoCancel(String question) {
		JFrame frame = new JFrame();
		int result = JOptionPane.showConfirmDialog(frame, question);
		return result;
	}
	
	
	/**
	 * Default report
	 *
	 * @param s
	 */
	public static void report(String s) {
		JFrame parent = new JFrame();
		JOptionPane.showMessageDialog(parent, s);
	}
	
	/**
	 * Dialog for selection.
	 * 
	 * @param options
	 * @param question
	 * @return index
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
	 * Default input dialog.
	 *
	 * @param question
	 * @return result
	 */
	public static String input(String question) {
		JFrame frame = new JFrame();

        String result;
        result = (String) JOptionPane.showInputDialog(frame, question, "Input", JOptionPane.QUESTION_MESSAGE);

        return result;
    }
}
