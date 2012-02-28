package abook.gui;

import java.awt.Component;

import javax.swing.JTable;

import abook.AbIGuiComponent;

public class AbTable implements AbIGuiComponent {
	
	JTable table;
	AbTableModel model;
	
	public AbTable() {
		
		model = new AbTableModel();
		table = new JTable(model);
		model.addColumn("jmeno");
		model.addColumn("prijmeni");
	}

	public AbTableModel getTableModel() {
		return model;
	}
	
	@Override
	public Component getWidget() {
		return table;
	}

}
