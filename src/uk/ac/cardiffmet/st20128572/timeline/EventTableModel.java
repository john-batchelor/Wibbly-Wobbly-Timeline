/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cardiffmet.st20128572.timeline;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author John
 */
public class EventTableModel extends AbstractTableModel
{
    private String[] columnNames = {"Title", "Date", "Description", "People Involved"};
    
    Data d = new Data();
    
    private final ArrayList<Event> data = d.getEvents();
    
    public String getColumnName(int col)
    {
        return columnNames[col];
    }
    
    public int getRowCount()
    {
        return data.size();   
    }
    
    public int getColumnCount()
    {
        return columnNames.length;
    }
    
    
    public Object getValueAt(int row, int col)
    {
                                  
        final String[] fieldNames = 
        {
            "title",        //Name of the event
            "dateString",   //Stringified version of the GregorianCalendar date value 
            "description",  //Description of the event
            "pName"         //The name attribute of the Person object involved 
        };
        
        Event e = data.get(row);
       
        try
        {
            Field F = e.getClass().getDeclaredField(fieldNames[col]);
            return F.get(e);
        }
        
        catch (NoSuchFieldException | IllegalAccessException x)
        {
            return null;
        }
    }
    
    public boolean isCellEditable(int row, int col)
    {
        return true;
    }
    
    public String dateToString(GregorianCalendar date)
    {
        Date d = date.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String dateS = df.format(d);
        return dateS;
    }
    
    /*public void setValueAt(Object value, int row, int col)
    {
        final String[] fieldNames = {"title", "date", "description", "people"};
        
        fieldNames[row][col] = value;
    }*/
    
}
