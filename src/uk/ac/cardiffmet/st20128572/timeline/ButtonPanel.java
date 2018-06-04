/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cardiffmet.st20128572.timeline;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author John
 */
public class ButtonPanel extends JPanel
{
    Data d = new Data(); //create an instance of Data
    ArrayList<Event> data = d.getEvents(); //assign the events array to local array 
    ArrayList<JButton> btns; //create ArrayList of store buttons
    
    /**
     * Create a new Button panel - displays all the events as buttons.
     */
    public ButtonPanel()
    {
        Collections.sort(data); //sort the events into chronological order
        addBtns(); //add buttons to the panel
        setVisible(true); //make the panel visible
        setPreferredSize(new Dimension(getFullWidth(),75)); //set preferred size
        setBackground(Color.WHITE); //set the background colour to white
    }
    /**
     * Populates the buttons array by taking the name of each element and creates a button of that element.
     * @return Buttons array after population.
     */
    public ArrayList<JButton> getBtns()
        {
            ArrayList<JButton> btns = new ArrayList<>();
            for(int i =0;i<data.size(); i++)
            {
                String title = data.get(i).getTitle();
                
                btns.add(new JButton(title));
            }
            return btns;
        }
        
        /**
         * 
         */
        public void addBtns()
        {
            
            JPanel panel = this;
            int amountOfSpace = 150; //margin space between one event and another
            ArrayList<JButton> j = getBtns();
            
            int start = 10; //initially the line begins at 0 (X axis)
            int end = amountOfSpace; //button y location is 150 to begin with
           
                
            for(int i =0;i<data.size(); i++)
                {
                    //Set properties of current button
                    j.get(i).setSize(140,50); //set the size of the button
                    j.get(i).setLocation(start, 15); //set its location on screen
                    j.get(i).setVisible(true); //make the button visible
                    
                    //Store hidden values to the button
                    j.get(i).putClientProperty("id", i); //id is the index where the event sits in the array
                    j.get(i).putClientProperty("description", data.get(i).getDescription()); //description of the event
                    j.get(i).putClientProperty("date", data.get(i).getDate()); //date of the event
                    j.get(i).putClientProperty("people", data.get(i).getPeople()); //people involved in the event
                    
                    j.get(i).setToolTipText(data.get(i).getTitle()); //set tooltip to the title of the event
                    j.get(i).addActionListener(new ActionListener() //add action listener to current button
                    { 
                        public void actionPerformed(ActionEvent e) 
                        { 
                            JButton button = (JButton) e.getSource();
                            String date = button.getClientProperty("date").toString(); //convert hidden value date from object to string
                            String description = button.getClientProperty("description").toString(); //convert hidden value description from object to string
                            int index = Integer.parseInt(button.getClientProperty("id").toString()); //convert hidden value id from object to int
                            List people = data.get(index).getPeople(); //use the id value to access the people array
                            
                            Event ev; //create new event variable
                            try
                            {
                                ev = new Event(button.getText(), date, description, people); //create new Event based on the buttons hidden values
                                EventViewer view = new EventViewer(ev); //create new EventViewer window of the event
                                view.run(); //display the EventViewer window
                            }
                            catch (ParseException ex) //if the index cannot be converted to an integer then throw print error to console
                            {
                                Logger.getLogger(ButtonPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                          } 
                    });
                    
                    panel.add(j.get(i)); //add button to the panel
                    
                     start = end; //increment the start value to the end
            
                     end = end + amountOfSpace; //increase the end value by 150
                }
        }
        
    public int getFullWidth() //get full width
    {
        /* Refactored
        int width = 0;
        for(int i =0; i<data.size();i++)
        {
           width = width + 150; 
        }
        System.out.println(width);
        */
        
        int width = data.size()*150;
        return width;
    }
}

