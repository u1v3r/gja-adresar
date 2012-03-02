package abook.gui.tabs;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import abook.profile.AbPerson;
import abook.profile.InitProfile;

public class AbTabDatabase implements AbIGuiTabComponent {
	
	protected boolean open;
	protected JScrollPane panel;
	protected final String name = "Database";
	protected final String tooltip = "Table of all contacts";
	protected JTable table;
	protected DefaultTableModel tableModel;
	
	public AbTabDatabase() {
		
		this.open = false;
		
		createTable();
		
		this.panel = new JScrollPane(table);
	}

	private void createTable() {
		
		tableModel = new DefaultTableModel();
		
		// make column titles //
		tableModel.addColumn("Name");
		tableModel.addColumn("Surname");
		tableModel.addColumn("Location");
		tableModel.addColumn("Groups");
		
		// make rows //
		List<AbPerson> listOfPersons = InitProfile.getProfile().getListOfAbPersons();
		List<String> listOfGroups = InitProfile.getProfile().getListOfGroups();
		for(AbPerson person : listOfPersons) {
			
			String[] row = new String[4];
			row[0] = person.getFirstName();
			row[1] = person.getLastName();
			row[2] = person.getCity();
			row[3] = "";
			
			for(Integer group : person.getListOfGroupIndex()) {
				if(!row[3].isEmpty()) row[3] += ", ";
				row[3] += listOfGroups.get(group);
			}
			
			tableModel.addRow(row);
		}
		
		table = new JTable(tableModel);
		
	}

	@Override
	public JComponent getWidget() {
		return panel;
	}

	@Override
	public boolean isOpen() {
		return open;
	}
	
	@Override
	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTooltip() {
		return tooltip;
	}

}
