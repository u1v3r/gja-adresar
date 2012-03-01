package abook.gui.table;

import javax.swing.JComponent;
import javax.swing.JTable;

import abook.AbIGuiComponent;

public class AbTable implements AbIGuiComponent {
	
	JTable table;
	AbTableModel model;
	
	public AbTable() {
		
		model = new AbTableModel();
		table = new JTable(model);
		model.addColumn("Name");
		model.addColumn("Surname");
		model.addColumn("City");
		model.addColumn("Groups");
	}

	public AbTableModel getTableModel() {
		return model;
	}
	
	@Override
	public JComponent getWidget() {
		return table;
	}

}
