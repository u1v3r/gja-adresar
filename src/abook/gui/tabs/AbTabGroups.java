package abook.gui.tabs;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import abook.gui.AbToolBar;
import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbGroup;
import abook.profile.AbPerson;
import abook.profile.InitProfile;

public class AbTabGroups implements AbITabComponent, AbListener {
	
	protected JScrollPane panel;
	protected final String name = "Groups";
	protected final String tooltip = "Contact groups card";
	
	protected JTable table;
	protected DefaultTableModel tableModel;
	
	public AbTabGroups() {
		
		InitListenerCore.getListenerCore().addListener(this);
		
		createTable();
		
		this.panel = new JScrollPane(table);
	}

	private void createTable() {
		
		tableModel = new DefaultTableModel();
		
		// make column titles //
		tableModel.addColumn("Name");
		//tableModel.addColumn("Delete");
		
		table = new JTable(tableModel);
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		table.setRowSorter(sorter);
		
		actualizeTab();
		
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
    public void actualizeTab() {	    
		// clear table //
		int rowCount = tableModel.getRowCount();
		for(int i = 0; i < rowCount; i++) {
			tableModel.removeRow(0);
		}
		
		// add new rows //
		List<AbGroup> listOfGroups = InitProfile.getProfile().n_getListOfGroups();

		for(AbGroup gr : listOfGroups){	
			Object[] row = new Object[1];
			row[0] = gr.getGroupName();
			//row[1] = true;
			
			tableModel.addRow(row);
		}
		
		table.repaint();	
    }
	

	@Override
	public void myEventOccurred(AbEvent evt, int type) {
		
		// Refresh table when groups changed in AddGroupDialog
		if(type == 4)
		{
			actualizeTab();
		}
	}

}
