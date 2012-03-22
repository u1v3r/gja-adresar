package abook.gui.tabs;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbGroup;
import abook.profile.InitProfile;

public class AbTabGroups implements TableModelListener, AbITabComponent, AbListener {
	
	protected JScrollPane panel;
	protected final String name = "Groups";
	protected final String tooltip = "Contact groups card";
	
	protected JTable table;
	protected DefaultTableModel tableModel;
	
	protected String savedCellValue = "";
	

	public AbTabGroups() {
		
		InitListenerCore.getListenerCore().addListener(this);
		
		createTable();
		
		this.panel = new JScrollPane(table);
	}

	/**
	 * creates table model and table
	 */
	private void createTable() {
		
		tableModel = new DefaultTableModel();
		
		// make column titles //
		tableModel.addColumn("Name");
		//tableModel.addColumn("Delete");
		
		table = new JTable(tableModel);
		table.setSelectionMode(0); // only one row can be selected at the same time
		
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setCellEditor(new CustomTableCellEditor());
		
		// listener to UPDATE cell event for group names
		tableModel.addTableModelListener(this);
		
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
		switch (type) {
		case 4:
		{
			actualizeTab();
			break;
		}
		case 5:
		{
			if(getSelectedGroupName() != null )
			{
				InitProfile.getProfile().n_removeGroup(getSelectedGroupName());
				InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.GROUPS_CHANGED);	
			}
			break;
		}
		default:
			break;
		}
	}
	
	/**
	 * Gets name of group currently selected in table. 
	 * @return String of group name; null if no row is selected.
	 */
	private String getSelectedGroupName()
	{
		int rowIndex = table.getSelectedRow();
		if(rowIndex != -1)
		{
			return (String) table.getValueAt(rowIndex, 0);
		}else return null;
	}

	
	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.getType() == TableModelEvent.UPDATE)
		{
			// update profile group
			InitProfile.getProfile().n_updateGroupName(savedCellValue, (String) table.getValueAt(e.getFirstRow(), e.getColumn()));
			
			// fire event to global listener
			InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.GROUPS_CHANGED);
		}
	}

	
	// Custom table editor class for validating edit of group names
	public class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	    // This is the component that will handle the editing of the cell value
	    JComponent component = new JTextField();

	    /**
	     * Sets number of clicks needed to activate custom editor on table column.
	     */
	    @Override
		public boolean isCellEditable(EventObject e)
	    {
	        if (e instanceof MouseEvent)
	        {	            
	            return ((MouseEvent)e).getClickCount() >= 3;
	        }
	        return true;
	    }
	    
	    /**
	     * This method is called when a cell value is edited
	     */
	    @Override
		public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int rowIndex, int vColIndex)
	    {
	        if (isSelected)
	        {
	            // cell is selected, store old value
	        	savedCellValue = (String) value;
	        }

	        // Configure the component with the specified value
	        ((JTextField)component).setText((String)value);
	        // Return the configured component
	        return component;
	    }

	    /**
	     *  This method is called when editing is completed.
	     */
	    @Override
		public Object getCellEditorValue()
	    {
	        // return the new value to be stored in the cell.
	        return ((JTextField)component).getText();
	    }
	    
	    /**
	     *  This method is called just before the cell value
	     *  is saved. If the value is not valid, false should be returned.
	     */ 
	    @Override
		public boolean stopCellEditing() {
	        String s = (String)getCellEditorValue();
        	
	        if (!isValid(s)) {
	            // Should display an error message at this point
	        	Toolkit.getDefaultToolkit().beep();
	            return false;
	        }
	        return super.stopCellEditing();
	    }
	    
	    /**
	     * Validates edited group name
	     * @param s = new group name 
	     * @return TRUE if new group name is same as old one, or if new name is different from all existing group names used in profile. Else returns FALSE.		
	     */
	    private boolean isValid(String s)
	    {
	    	if(s.compareTo(savedCellValue)!=0)
	    	{
		    	List<String> listOfGroups = InitProfile.getProfile().n_getListOfGroupNames();
		    	
		    	if(s.length()!=0 && !listOfGroups.contains(s))
		    		return true;
		    	else 
		    		return false;
	    	}
	    	return true;
	    }

	}

}
