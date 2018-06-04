/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cardiffmet.st20128572.timeline;

import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author John
 */
public class PeopleTableModel extends AbstractTableModel {
    
    private String[] columnNames = {"Name", "Date Of Birth", "Description", "Role"};
    
    Data d = new Data();
    
    private final ArrayList<Person> data = d.getPeople();
    
    /**
     * 
     * @param col the element number of the column e.g. name = 0;
     * @return the name of the column
     */
    public String getColumnName(int col)
    {
        return columnNames[col];
    }
    /**
     * 
     * @return the number of rows in the table
     */
    public int getRowCount()
    {
        return data.size();   
    }
    /**
     * 
     * @return the number of columns within the table
     */
    public int getColumnCount()
    {
        return columnNames.length;
    }
    /**
     * 
     * @param row the row that the data is at
     * @param col the column that the data is at
     * @return an Object of the value of at row x and col x
     */
    public Object getValueAt(int row, int col)
    {
        final String[] fieldNames = {"name", "dateStr", "description", "role"};
        
        Person p = data.get(row);
        
        try
        {
            Field F = p.getClass().getDeclaredField(fieldNames[col]);
            
            return F.get(p);
        }
        
        catch (NoSuchFieldException | IllegalAccessException x)
        {
            return null;
        }
    }
    
    /**
     * 
     * @param row the row number of the cell is at 
     * @param col the column number of the cell is at
     * @return true if the cell is editable or false if not
     */
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
}

