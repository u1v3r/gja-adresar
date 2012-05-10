package abook.gui.tabs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.google.gdata.data.dublincore.Date;

import abook.gui.dialogs.AbDialogAddContact;
import abook.listeners.AbEvent;
import abook.listeners.AbListener;
import abook.listeners.InitListenerCore;
import abook.profile.AbPerson;
import abook.profile.InitProfile;

/**
 * Tab with birthday events.
 * 
 * @author bakulla
 *
 */
public class AbTabEvents implements AbListener, AbITabComponent {
	
	protected JScrollPane panel;
	protected final String name = "Birthdays";
	protected final String tooltip = "Birthday card";

	protected JTable table;
	protected MyTableModel tableModel;
	
	protected final String NAME_COLUMN_CAPTION ="Name";
	protected final String SURENAME_COLUMN_CAPTION = "Surename";
	protected final String BIRTHDAY_COLUMN_CAPTION ="Birthday";
	protected final String NEXT_COLUMN_CAPTION ="Days to next birthday";
	protected final String AGE_COLUMN_CAPTION ="Age";

	
	/**
	 * Creates new event tab.
	 */
	public AbTabEvents() {
		
		InitListenerCore.getListenerCore().addListener(this);
		
		createLayout();
		
		this.panel = new JScrollPane(table);
	}

	/**
	 * Creates table layout
	 */
	private void createLayout()
	{
			
			tableModel = new MyTableModel();
			
			// make column titles //
			tableModel.addColumn("ID");
			tableModel.addColumn(this.NAME_COLUMN_CAPTION);
			tableModel.addColumn(this.SURENAME_COLUMN_CAPTION);
			tableModel.addColumn(this.BIRTHDAY_COLUMN_CAPTION);
			tableModel.addColumn(this.AGE_COLUMN_CAPTION);
			tableModel.addColumn(this.NEXT_COLUMN_CAPTION);
			
			table = new JTable(tableModel);
			
			table.removeColumn(table.getColumn("ID")); // hide "ID" column
			table.setSelectionMode(0); // only one row can be selected at the same time //	
			table.addMouseListener(new MyMouseListener());
			
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
		List<AbPerson> listOfContacts = InitProfile.getProfile().getListOfAbPersons();

		SimpleDateFormat bdate_formatter;
		bdate_formatter = new SimpleDateFormat("d'.' MMM yyyy", Locale.getDefault());
		
		for(AbPerson per : listOfContacts){
			
			if(per.getBirthday() != null)
			{
				Object[] row = new Object[6];
				row[0] = per.getId();
				row[1] = per.getFirstName();
				row[2] = per.getLastName();
				row[3] = bdate_formatter.format(per.getBirthday());
				
				// Create a calendar object with today's date
				Calendar today = Calendar.getInstance();
				Calendar birth = Calendar.getInstance();
				// check correctness of birth date
				if(per.getBirthday().before(today.getTime()))
					birth.setTime(per.getBirthday());
				
				// age
				// this row assign first, we will mess up years for previous row later
				row[4] = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
				
				// counting days to next birthday party
				birth.set(Calendar.YEAR, today.get(Calendar.YEAR));
				int difference = birth.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
				if(difference < 0)				
				{	
					// we have already missed the date	
					// ...counting to next year birthday
					birth.add(Calendar.YEAR, 1); 
					difference = birth.get(Calendar.DAY_OF_YEAR) + (today.getMaximum(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR));
				}
				row[5] = difference; 
				
				tableModel.addRow(row);

			}
		}
		
		table.repaint();
    }

	@Override
	public void myEventOccurred(AbEvent evt, int type) {
		if(type == 3)
		{
			actualizeTab();
		}
	}  
	
	/**
	 *  Custom table model class for specifiing editable and sorting properties of specified collumns. 
	 *
	 * @author bakulla
	 *
	 */
	@SuppressWarnings("serial")
	class MyTableModel extends DefaultTableModel {  
		  
	    public MyTableModel() {  
	       //  super(rowData, columnNames);  
	      }  
	     
		@Override  
	      public Class<?> getColumnClass(int col) {  
	        if (col == 4 || col == 5)       // columns accepts only Integer values  
	            return Integer.class;  
        	else
        		return String.class;  //other columns accept String values  
	    }  
	  
	    @Override  
	      public boolean isCellEditable(int row, int col) {
	            return false;    // all columns are not editable  
	      }
	    
	}

	/**
	 * Custom MouseListener class for firing edit dialog event on double click on rows.
	 * @author bakulla
	 *
	 */
	class MyMouseListener implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				
				//get selected AbPerson
				int id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				
				System.out.println("row" + table.getSelectedRow());
				System.out.println(id);
				
				// Calling Edit person dialog
				new AbDialogAddContact(InitProfile.getProfile().getContact(id)).setVisible(true);
			}
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {		
		}
	
		@Override
		public void mouseExited(MouseEvent e) {	
		}
	
		@Override
		public void mousePressed(MouseEvent e) {			
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {		
		}
	}
}
