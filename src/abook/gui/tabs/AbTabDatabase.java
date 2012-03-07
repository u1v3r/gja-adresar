package abook.gui.tabs;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbPerson;
import abook.profile.InitProfile;

public class AbTabDatabase implements AbITabComponent, AbListener {
	
	protected JScrollPane panel;
	protected final String name = "Database";
	protected final String tooltip = "Table of all contacts";
	protected JTable table;
	protected DefaultTableModel tableModel;
	
	public AbTabDatabase() {
		
		createTable();
		
		this.panel = new JScrollPane(table);
		
		InitListenerCore.getListenerCore().addListener(this);
	}

	private void createTable() {
		
		tableModel = new DefaultTableModel();
		
		// make column titles //
		tableModel.addColumn("Name");
		tableModel.addColumn("Surname");
		tableModel.addColumn("Location");
		tableModel.addColumn("Groups");
		
		table = new JTable(tableModel);
		
		actualizeTable();
		
	}
	
	private void actualizeTable() {
		
		// clear table //
		int rowCount = tableModel.getRowCount();
		for(int i = 0; i < rowCount; i++) {
			tableModel.removeRow(0);
		}
		
		// add new rows //
		List<AbPerson> listOfPersons = InitProfile.getProfile().getListOfAbPersons();
		List<String> listOfGroups = InitProfile.getProfile().getListOfGroups();
		List<Integer> listOfSelectedGroups = InitProfile.getProfile().getListOfSelectedGroups();
		boolean selected;
		for(AbPerson person : listOfPersons) {
			
			// group filter //
			selected = false;
			for(Integer group : person.getListOfGroupIndex()) {
				if(listOfSelectedGroups.contains(group)) {
					selected = true;
					break;
				}
			}
			
			if(!selected) continue;
			
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
		
		table.repaint();
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
    public void myEventOccurred(AbEvent evt, int type) {
	    
		if(type == GROUP_SELECTION_CHANGED) {
			
			System.out.println("actualize");
			
			actualizeTable();
		}
    }

}
