/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cardiffmet.st20128572.timeline;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
//import javax.json.*;

/**
 *
 * @author John
 */
public class Data {
    
    public ArrayList<Event> events = new ArrayList<>();
    
    public ArrayList<Person> people = new ArrayList<>();
    //JsonReader gson = new GSON;
    //initPeople();
    
    public Data()
    {
        initPeople();
        initEvent();
    }
    
    public void addToArray(ArrayList<Person>arr, String newDescription, GregorianCalendar newDOB, String newName, String newRole)
    {
        people.add(new Person(newName, newDOB, newDescription, newRole));
        displayAll(people);
    }
    
    public void addEvent(ArrayList<Event>arr, String newTitle, GregorianCalendar newDate, String newDescription, List<Person> people) {
            events.add(new Event(newTitle, newDate, newDescription, people));
    }
    
    public ArrayList<Person> getPeople()
    {
        return people;
    }
    
    public void displayAll(ArrayList<Person>arr)
    {
        for(int i = 0; i<arr.size(); i++)
        {
            System.out.print(arr.get(i).getName());
            System.out.print(arr.get(i).getDOB());
            System.out.print(arr.get(i).getDescription());
            System.out.print(arr.get(i).getRole());
        }
    }
    
    public void displayAllEvents(ArrayList<Event>arr)
    {
            for(int i=0; i<arr.size(); i++)
            {
               System.out.print(arr.get(i).getTitle());
               System.out.print(arr.get(i).getDate());
               System.out.print(arr.get(i).getDescription());
            }
    }
    
    public String[][] populatePeople()
    {
        int rowSize = people.size();
        String data[][] = new String[rowSize][4];
        
        for(int i=0; i<people.size(); i++)
        {
            data[i][0] = people.get(i).getName();
            data[i][1] = people.get(i).getDOB();
            data[i][2] = people.get(i).getDescription();
            data[i][3] = people.get(i).getRole();
            
        }
        
        return data;
    }
    
    public String[] getPeopleCols()
    {
        String colNames[] = new String[4];
        colNames[0] = "Name";
        colNames[1] = "Date Of Birth";
        colNames[2] = "Description";
        colNames[3] = "Role";
        return colNames;
    }
    
   
    
    
    public String[][] populateEvents()
    {
        
        int rowSize = events.size();
        String data[][] = new String[rowSize][4];
        
        for(int i=0; i<events.size(); i++)
        {
            data[i][0] = events.get(i).getTitle();
            data[i][1] = events.get(i).getDate();
            data[i][2] = events.get(i).getDescription();
            //data[i][3] = events.get(i).getRole();
            
        }
        
        return data;
    }
    
    public ArrayList getEvents()
    {
        return events;
    }
    
    public String[] getEventsCols()
    {
        String colNames[] = new String[4];
        colNames[0] = "Title";
        colNames[1] = "Date";
        colNames[2] = "Description";
        colNames[3] = "People Involved";
        return colNames;
    }
    
    public Person[] getPeopleList()
    {
        Person[] list = new Person[people.size()];
        
        for(int i=0;i<people.size();i++)
        {
            list[i] = people.get(i);
        }
        return list;
    }
    
    //events.add(new Event())
    
    /*events.add(new Event("First episode aired", 
            new GregorianCalendar(1963, 11, 23), 
            "First Episode of Doctor Who aired on Saturday 23rd November 1963", 
            people.add(new Person("Sydney Newman", "Sydney was one of the creators of Doctor Who", "Creator")), people.add(new Person("William Hartnell", "William Hartnell played the Doctor from 1963 to 1966", "The Doctor")
    );
   */ 
   
   /*
   void addToArray(ArrayList<Object> array)
   {
       
   }
*/
    public void initPeople()
    {
        XMLReadWrite xml = new XMLReadWrite();
        
        people = xml.readPeopleXML();
    }
    
    public void initEvent()
    {
        XMLReadWrite xml = new XMLReadWrite();
        
        events = xml.readEventXML();        
    }
}
