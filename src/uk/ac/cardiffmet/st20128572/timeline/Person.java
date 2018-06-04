/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cardiffmet.st20128572.timeline;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author John
 */

public class Person
{
    String name;
    String description;
    String role;
    GregorianCalendar date;
    String dateStr;
    
    /**
     * 
     * @param name The Full Name of the person (String)
     */
    
    public Person(String name)
    {
        this(name, new GregorianCalendar(1970, 00, 01), "No description supplied", "Unknown");
    }
    
    /**
     * 
     * @param name The full name of the person (String)
     * @param date The date of birth of the person (in Gregorian Calendar format - yy,mm,dd)
     * @param description A description of the person.
     * @param role The role this person played within the show
     */
    public Person(String name, GregorianCalendar date, String description, String role)
    {
        this.name = name;
        this.description = description;
        this.role = role;
        this.date = date;
        dateStr = getDOB();
    }
    /**
     * 
     * @return String version of person's date of birth  
     */
    public String getDOB()
    {
        Date d = date.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String dateS = df.format(d);
        return dateS;
    }
    /**
     * 
     * @return Gregorian Calendar version of the person's date of birth
     */
    public GregorianCalendar getGregorianDOB()
    {
        return date;
    }
    /**
     * 
     * @return The name of the person
     */
    public String getName()
    {
        return name;
    }
    /**
     * Changes the name of the person
     * @param name Current name of the person
     * @param newName New name of the person
     */
    public void setName(String name, String newName)
    {
        name = newName;
    }
    /**
     * 
     * @return The description of the person
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * 
     * @param description Current description of the person
     * @param newDesc New description of the person
     */
    public void setDescription(String description, String newDesc)
    {
        description = newDesc;
    }
    /**
     * 
     * @return The role this person played in the show.
     */
    public String getRole()
    {
        return role;
    }
    /**
     * Displays the person in a Bio GUI form.
     * @param p This person  
     */
    public static void displayBio(Person p)
    {
        Bio bio = new Bio(p);
        bio.run();
    }

}

