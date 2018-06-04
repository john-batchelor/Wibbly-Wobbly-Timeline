/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cardiffmet.st20128572.timeline;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * This class encapsulates the properties of an event such as Title, Date, Description and People who were involved. The methods within this class allow read access values through getter methods, write methods with set methods and methods used to convert data into different objects such as String objects to GregorianCalendar objects.
 * @author John
 */
public class Event implements Comparable<Event> {
    
    String title; //title of the event
    GregorianCalendar date; //date of the event
    String description; //description of the event
    List<Person> people; //list of people involved in the event
    String dateString; //the date of the event as 
    String pName; //Stringified version of the Person object for the JTable
    /**
     * 
     * @param title String title of the event
     * @param date is the date of the event in Gregorian Calendar format (yyyy,mm,dd)
     * @param description - String description of the event
     * @param people  - The List of people involved in the event
     */
    
    public Event(String title, GregorianCalendar date, String description, List<Person> people)
    {
        this.title = title;
        this.date = date;
        this.description = description;
        this.people = people;
        dateString = getDate();
        pName = listPeople();
    }
    /**
     * Constructor assigns default values to the event when only the title is supplied. 
     * 
     * @param title - String title of the event
     */
    public Event(String title)
    {
        this(title, new GregorianCalendar(), "No Description Supplied", null); //create default if only name has been supplied
    }
    /**
     * Constructor that uses a String for the date parameter and converts it into a Gregorian Calendar formatted date.
     * @param title - String title of the event
     * @param date - String format of the date of the event - to be converted into Gregorian Calendar format.
     * @param description - String description of the event
     * @param people - List of Person object which represent people who were involved in the event
     * @throws ParseException If date cannot be converted to integer - throw Parse Exception
     */
    public Event(String title, String date, String description, List<Person> people) throws ParseException
    {
        this.title = title;
        this.date = fromString(date);
        this.description = description;
        this.people = people;
        dateString = getDate();
        pName = listPeople();
    }
    /**
     * Gets the title of the event
     * 
     * @return the title of the event in String format
     */
    public String getTitle()
    {
       return title;
    }
    /**
     * 
     * @return List of Person objects which represents people involved in the event
     */
    public List<Person> getPeople()
    {
        return people;
    }
    /**
     * 
     * @return date of the event in Gregorian Calendar format
     */
    public GregorianCalendar getGregorianDate()
    {
        return date;
    }
    /**
     * 
     * @return the description string of the event
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * Converts the Gregorian Calendar date into a String and returns it
     * 
     * @return the date of the event in string format
     */
    public String getDate()
    {
        Date d = date.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String dateS = df.format(d);
        return dateS;
    }
    /**
     * Changes the people List variable into a new List of Person objects for this event.
     * @param newPeople - the List of Person objects to replace the original list of Person objects
     */
    public void setPeople(List<Person> newPeople)
    {
        people = newPeople;
    }
    /**
     * 
     * @param n Another Event in which to compare this event to in order to sort them into chronological order
     * @return an integer between -1 and 1. (0 - if the event's date is equal to the Event n's date, -1 if the event's date is less that Event n's date and 1 if the event's date is greater that Event n's date)
     */
    @Override
    public int compareTo(Event n)
    {
	int compare = date.compareTo(n.date);
	
	return compare;
    }
    /**
     * Creates a String version of each Person object name variable for the GUI.
     * 
     * @return a concatenated String of each Person object's name separated by a comma.
     */
    public String listPeople()
    {
        String peopleStr = "";
        
        if(people != null)
        {
            for(int i = 0; i<people.size(); i++)
            {
                peopleStr = peopleStr + people.get(i).getName() + ", ";
            }
        }
        return peopleStr;
    }
    /**
     * 
     * @param e the Event object to display in an EventViewer GUI form.
     */
    public void displayEvent(Event e)
    {
        EventViewer ev = new EventViewer(e);
        ev.run();
    }
    
    
    /**
     * 
     * @param date - Date of the event in the String format for conversion into Gregorian Calendar format.
     * @return a GregorianCalendar object of the date parameter provided.
     * @throws ParseException - if the date parameter cannot be converted into a GregorianCalendar object.
     */
    public GregorianCalendar fromString(String date) throws ParseException
    {
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = convertMonth(parts[1]);
        int year = Integer.parseInt(parts[2]);
        
        return new GregorianCalendar(year, month, day);
    }
    /**
     * Converts a month string into an integer value based on that month's order in the calendar.
     * @param month the month string e.g. "Jan"
     * @return an integer value representing that month e.g. Jan = 0.
     */
    public int convertMonth(String month)
    {
        int newMonth;
        
        switch(month)
        {
            case "Jan": newMonth = 0;
            break;
            case "Feb": newMonth = 1;
            break;
            case "Mar": newMonth = 2;
            break;
            case "Apr": newMonth = 3;
            break;
            case "May": newMonth = 4;
            break;
            case "Jun": newMonth = 5;
            break;
            case "Jul": newMonth = 6;
            break;
            case "Aug": newMonth = 7;
            break;
            case "Sep": newMonth = 8;
            break;
            case "Oct": newMonth = 9;
            break;
            case "Nov": newMonth = 10;
            break;
            case "Dec": newMonth = 11;
            break;
            
            default: newMonth = 0;
        }
        
        return newMonth;
    }
}
