package abook.gui.tabs;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

/**
 * Tab for groups.
 * 
 * @author Marek Mesar
 *
 */
public class AbTabGroups implements TableModelListener, AbITabComponent, AbListener {
	
	protected JScrollPane panel;
	protected final String name = "Groups";
	protected final String tooltip = "Contact groups card";
	
	protected final String DEFAULT_GROUP_NAME ="all";
	protected final String NAME_COLUMN_CAPTION ="Name";
	protected final String DESCRIPTION_COLUMN_CAPTION ="Description";
	
	protected JTable table;
	protected DefaultTableModel tableModel;
	
	protected String savedCellValue = "";
	
	/**
	 * Creates new tab for groups.
	 */
	public AbTabGroups() {
		
		InitListenerCore.getListenerCore().addListener(this);
		
		createTable();
		
		this.panel = new JScrollPane(table);
	}

	/**
	 * Creates table model and table.
	 */
	private void createTable() {
		
		tableModel = new DefaultTableModel();
		
		// make column titles //
		tableModel.addColumn(this.NAME_COLUMN_CAPTION);
		tableModel.addColumn(this.DESCRIPTION_COLUMN_CAPTION);
		
		table = new JTable(tableModel);
		table.setSelectionMode(0); // only one row can be selected at the same time //

		// setting custom cell editor for columns
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setCellEditor(new CustomTableCellEditor());
		col = table.getColumnModel().getColumn(1);
		col.setCellEditor(new CustomTableCellEditor());
		
		// listener to UPDATE cell event for group names //
		tableModel.addTableModelListener(this);
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		table.setRowSorter(sorter);
		
		table.getTableHeader().setReorderingAllowed(false);
		
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
		List<AbGroup> listOfGroups = InitProfile.getProfile().getListOfGroups();

		for(AbGroup gr : listOfGroups){	
			Object[] row = new Object[2];
			row[0] = gr.getGroupName();
			row[1] = gr.getDescription();
			
			tableModel.addRow(row);
		}
		
		table.repaint();	
    }
	

	@Override
	public void myEventOccurred(AbEvent evt, int type) {
		switch (type) {
		case 4: {	// something changed, actualize table //
			actualizeTab();
			break;
		}
		// Clicked on delete group button from toolbar //
		case 5: {
			String t_GrName = getSelectedGroupName();
			if(t_GrName != null && t_GrName.compareTo(DEFAULT_GROUP_NAME)!=0 )
			{
				// dialog //
				JFrame frame = new JFrame();
			    String message = "Do you want to DELETE "+ t_GrName+" group?";
			    int answer = JOptionPane.showConfirmDialog(frame, message, "Delete group", JOptionPane.YES_NO_OPTION , JOptionPane.WARNING_MESSAGE);
			    if (answer == JOptionPane.YES_OPTION) {
			    	// User clicked YES. //
			    	InitProfile.getProfile().removeGroup(t_GrName);
					InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.GROUPS_CHANGED);
			    }
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
		if(rowIndex != -1) {
			return (String) table.getModel().getValueAt(rowIndex, 0);
		} 
		else return null;
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.getType() == TableModelEvent.UPDATE) {

			switch (e.getColumn()) {
				case 0: {	// updating NAME column //
					// update profile group //
					InitProfile.getProfile().updateGroupName(savedCellValue, (String) table.getModel().getValueAt(e.getFirstRow(), 0));
					break;
				}
				case 1: { // updating DESCRIPTION column //
					InitProfile.getProfile().updateGroupDescription(
							(String) table.getModel().getValueAt(e.getFirstRow(), 0),
							(String) table.getModel().getValueAt(e.getFirstRow(), 1));
					break;
				}
				default:
					break;
			}
			
			// fire event to global listener //
			InitListenerCore.getListenerCore().fireListeners(new AbEvent(this), AbListener.GROUPS_CHANGED);		
		}
	}
	
	/**
	 * Custom table editor class for validating edit of group names
	 * 
	 * @author Marek Mesar
	 *
	 */
	@SuppressWarnings("serial")
    public class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	    // This is the component that will handle the editing of the cell value //
	    JComponent component = new JTextField();

	    /**
	     * Sets number of clicks needed to activate custom editor on table column.
	     */
	    @Override
		public boolean isCellEditable(EventObject e) {
	        if (e instanceof MouseEvent) {	            
	            return ((MouseEvent)e).getClickCount() >= 2;
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
	        if (isSelected) {
	        	if(vColIndex == 0){  
	        		// updating group NAME //
		        	
		        	String temp = (String) value;  	
		        	// filtering editing of universal group name //
		        	if(temp.compareTo(DEFAULT_GROUP_NAME) ==0)
		        		return null;
		        	else
		        		savedCellValue = temp;
	        	}		
	        }

	        // Configure the component with the specified value //
	        ((JTextField)component).setText((String)value);
	        // Return the configured component //
	        return component;
	    }

	    /**
	     *  This method is called when editing is completed.
	     */
	    @Override
		public Object getCellEditorValue() {
	        // return the new value to be stored in the cell. //
	        return ((JTextField)component).getText();
	    }
	    
	    /**
	     *  This method is called just before the cell value
	     *  is saved. If the value is not valid, false should be returned.
	     */ 
	    @Override
		public boolean stopCellEditing() {
	        String s = (String)getCellEditorValue();
	        
	        if(table.getSelectedColumn() == 0) // changing NAME column //
	        {
	        	 if (!isNameValid(s)) {
			            // Should display an error message at this point //
			        	Toolkit.getDefaultToolkit().beep();
			            return false;
			     }
	        }
	        return super.stopCellEditing();
	    }
	    
	    /**
	     * Validates edited group name
	     * @param s = new group name 
	     * @return TRUE if new group name is same as old one, or if new name is different from all existing group names used in profile. Else returns FALSE.		
	     */
	    private boolean isNameValid(String s) {
	    	if(s.compareTo(savedCellValue)!=0) {
		    	List<String> listOfGroups = InitProfile.getProfile().getListOfGroupNames();
		    	if(s.length()!=0 && !listOfGroups.contains(s))
		    		return true;
		    	else 
		    		return false;
	    	}
	    	return true;
	    }

	}

}
