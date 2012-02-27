package abook.others;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class AddButtonToTabBar extends JFrame {
  private JTabbedPane tp;

  private JLabel lblStatus;

  private int tabCounter = 0;

  public AddButtonToTabBar() {
    super("Browser");
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JMenuBar mb = new JMenuBar();
    JMenu mFile = new JMenu("File");
    JMenuItem mi = new JMenuItem("Add Tab");
    ActionListener addTabl = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addTab();
      }
    };
    mi.addActionListener(addTabl);
    mFile.add(mi);
    mb.add(mFile);
    setJMenuBar(mb);

    JPanel pnlURL = new JPanel();
    tp = new JTabbedPane();
    addTab();
    getContentPane().add(tp, BorderLayout.CENTER);

    lblStatus = new JLabel(" ");
    getContentPane().add(lblStatus, BorderLayout.SOUTH);

    setSize(300, 300);
    setVisible(true);
  }

  void addTab() {
    JEditorPane ep = new JEditorPane();
    ep.setEditable(false);
    tp.addTab(null, new JScrollPane(ep));

    JButton tabCloseButton = new JButton("Close");
    tabCloseButton.setActionCommand("" + tabCounter);

    ActionListener al;
    al = new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JButton btn = (JButton) ae.getSource();
        String s1 = btn.getActionCommand();
        for (int i = 1; i < tp.getTabCount(); i++) {
          JPanel pnl = (JPanel) tp.getTabComponentAt(i);
          btn = (JButton) pnl.getComponent(0);
          String s2 = btn.getActionCommand();
          if (s1.equals(s2)) {
            tp.removeTabAt(i);
            break;
          }
        }
      }
    };
    tabCloseButton.addActionListener(al);

    if (tabCounter != 0) {
      JPanel pnl = new JPanel();
      pnl.setOpaque(false);
      pnl.add(tabCloseButton);
      tp.setTabComponentAt(tp.getTabCount() - 1, pnl);
      tp.setSelectedIndex(tp.getTabCount() - 1);
    }

    tabCounter++;
  }

  public static void main(String[] args) {
    new AddButtonToTabBar();

  }
}