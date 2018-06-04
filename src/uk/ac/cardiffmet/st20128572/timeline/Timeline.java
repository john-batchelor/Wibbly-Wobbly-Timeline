
package uk.ac.cardiffmet.st20128572.timeline;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.BoxLayout;



/**
 * Within in this class a visual representation of events within a time line are sorted into chronological order, and displayed on screen using a simple 
 * horizontal line between the first event and the last event. This class extends a JPanel and therefore is integrated into a larger JFrame with other 
 * JPanels present.
 */

public class Timeline extends JPanel
{

    Data d = new Data(); //create a new instance of the Data class to access the event data
    
    ArrayList<Event> data; //create a local variable of the data from the data class by reference
    
    /**
     * 
     */
    public Timeline()
    {
        data = d.getEvents(); // populate local variable with data from the getEvents from the Data class
        Collections.sort(data); //sort the events in chronological order
    }
    
    /**
     * Calculates the full width of the panel based on the number of events. 
     * @return full width of the panel
     */
    public int getFullWidth()
    {
        int width = data.size() * 150;
        
        return width;
    }
   
    /**
     * 
     * @param g Graphics object
     */
    @Override
	public void paintComponent(Graphics g) 
        {
           super.paintComponent(g);
           Graphics2D g2 = (Graphics2D) g;
       
                
                int amountOfSpace = 150; //margin space between one event and another
                
                int start = 10; //initially the line begins at 0 (X axis)
		int end = amountOfSpace; //initially the end of the line ends at 150, each iteration increases the line by 150 pixels
                int height = getHeight() / 2; //specifies where the entire timeline will sit in the JPanel on the Y Axis.
                
		String prevYear = ""; //initially set prevYear to an empty string and update with each iteration
                
                for(int i = 0; i<data.size(); i++)
                {
                    
                    
                    String date = data.get(i).getDate(); //the date of the event
                    String str[] = date.split("-"); //split up the date of the event into the str array of day [0], month [1], year [2]
                    String year = str[2]; //assign the year variable from the 3rd element str array to get the year.
                    String title = data.get(i).getTitle(); //get the title of this element
                    
                    
                    //j.get(i).addMouseListener(new MouseListener({
                        
                    
                    
                    g2.drawLine(start, height, end, height); //draw a horizontal line on the timeline
                    
                    if(!prevYear.equals(year)) //check if the prevYear value is not equal to the year value
                    {
                        //if so then write the year onto the screen, if not then don't write year to screen
                        g2.drawString(year, start, height + 20);
                    }
                    
                    //Draw a vertical line to indicate a connection to an event
                    g2.drawLine(start, height, start, height - 30);
                    
                    //if the title is greater than 25 characters and therefore is too long and risks overlapping the next item
                    if(title.length() > 25)  
                    {
                      title = title.substring(0, 21) + "..."; //cut the title down to 25 characters   
                    }
                    
                    //display the title at the start co-ordinate on the X axis and predefined height on the Y axis minus 50 pixels to place it above the line 
                    //g2.drawString(title, start, height - 50);  
                    
                    //for the next iteration start the next portion of the line at the end of this line.
                    start = end;
                    
                    //check whether this element is the last element in the array
                    if(i != data.size()-2) 
                    {
                        //if it isn't then increase the end of the line for the next iteration
                        end = end + amountOfSpace;
                        
                        //save the year of this element to check whether the next iteration needs to print the year
                        prevYear = year;
                    }
            }
                
           setPreferredSize(new Dimension(getFullWidth(), 150));
           revalidate();
           
        }           
}

