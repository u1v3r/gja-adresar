package abook.gui.table;

import javax.swing.table.DefaultTableModel;

/**
 * Own table model. It is possible to add methods there we will need.
 * 
 * @author jurij
 *
 */
@SuppressWarnings("serial")
public class AbTableModel extends DefaultTableModel {
	
	protected String columnNames[];
	protected int columnCount;
	protected int rowCount;
	
	public AbTableModel() {
		super();
	}
}
