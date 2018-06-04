/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cardiffmet.st20128572.timeline;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
/**
 *
 * @author John
 */
public class PersonRenderer extends DefaultListCellRenderer implements ListCellRenderer<Object>
{
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        Person p = (Person) value;
        setText(p.getName());
        
        putClientProperty("description", p.getDescription());
        putClientProperty("dateOfBirth", p.getDOB());
        
        if(isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        setEnabled(true);
        
        return this;
    }
}
