package abook.gui.dialogs;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AbReports {
	
	// TODO translate to english
	
	/** Zepta se uzivatele, jestli nechce projekt ulozit.
    *
    * @return vysledekRozhodovani
    */
   public static int trebaUlozit() {
       // rozhodnuti mezi novym projektem a existujicim//
       JFrame frame = new JFrame();
       int result = JOptionPane.showConfirmDialog(frame, "Hodlate otevrit" +
           " jiny projekt/zalozit novy projekt.\nPrejete si aktualni ulozit");
       //System.out.println(JOptionPane.CANCEL_OPTION == result);
       return result;
   }

   /** Ujisti se, jestli chce uzivatel opravdu zavrit kartu.
    *
    * @return vysledekRozhodovani
    */
   public static int chceteZavrit() {
     // rozhodnuti mezi novym projektem a existujicim//
     JFrame frame = new JFrame();
     int result = JOptionPane.showConfirmDialog(frame, "Opravdu chcete zavrit" +
         " nasledujici kartu");
     //System.out.println(JOptionPane.CANCEL_OPTION == result);
     return result;
   }

   /** Obecna otazka na Yes, No, Cancel
    *
    * @param s
    * @return vysledekRozhodovani
    */
   public static int yesNoCancelTask(String s) {
     // obecny task, kde uzivatel zvoli, ano, ne, cancel //
     JFrame frame = new JFrame();
     int result = JOptionPane.showConfirmDialog(frame, s);
     //System.out.println(JOptionPane.CANCEL_OPTION == result);
     return result;
   }

   /**
    * Informuje uzivatele, ze zadana featura jeste neni podporovana.
    */
   public static void zatimNe() {
       JFrame parent = new JFrame();
       JOptionPane.showMessageDialog(parent, "Funkce zatim neni podporovana");
   }

   /**
    * Informuje, ze uzivatel zadal spatny soubor
    */
   public static void spatneZadanySoubor() {
       JFrame parent = new JFrame();
       JOptionPane.showMessageDialog(parent, "Zadany soubor neexistuje");
   }

   /** Obecne hlaseni
    *
    * @param s
    */
   public static void hlaseni(String s) {
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
        null, options, "Titan");
        
        if(result != null) {
        	for(int i = 0; i < options.length; i++) {
        		if(result.equals(options[i])) return i;
        	}
        }

        return -1;
   }

}
