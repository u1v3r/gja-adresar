package abook.gui.tabs;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import abook.profile.AbPerson;
import abook.profile.InitProfile;

/**
 * Tab with table of database.
 * 
 * @author xjanda17
 *
 */
public class AbTabDatabase implements AbITabComponent {
	
	protected JScrollPane panel;
	protected final String name = "Database";
	protected final String tooltip = "Table of all contacts";
	protected JTable table;
	protected DefaultTableModel tableModel;
	
	/**
	 * Creates new tab.
	 */
	public AbTabDatabase() {
		
		createTable();
		
		this.panel = new JScrollPane(table);;
	}

	/**
	 * Creates new table with database.
	 */
	private void createTable() {
		
		tableModel = new DefaultTableModel();
		
		// make column titles //
		tableModel.addColumn("Name");
		tableModel.addColumn("Surname");
		tableModel.addColumn("Location");
		tableModel.addColumn("Groups");
		
		table = new JTable(tableModel);
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		table.setRowSorter(sorter);
		
		actualizeTab();
		
	}
	
	@Override
	public void actualizeTab() {
		
		// clear table //
		int rowCount = tableModel.getRowCount();
		for(int i = 0; i < rowCount; i++) {
			tableModel.removeRow(0);
		}
		
		// add new rows //
		List<AbPerson> listOfPersons = InitProfile.getProfile().getListOfAbPersons();
		List<String> listOfSelectedGroups = InitProfile.getProfile().getListOfSelectedGroups();
		String pattern = InitProfile.getProfile().getSearchText();
		boolean selected;
		for(AbPerson person : listOfPersons) {
			
			// search filter //
			if(new String(person.getFirstName() + " " + person.getLastName()).toLowerCase().indexOf(pattern) < 0) continue;
			
			// group filter //
			selected = false;
			for(String group : person.getListOfGroups()) {
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
			
			for(String group : person.getListOfGroups()) {
				if(!row[3].isEmpty()) row[3] += ", ";
				row[3] += group;
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

}
