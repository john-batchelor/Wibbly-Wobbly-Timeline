
package uk.ac.cardiffmet.st20128572.timeline; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class XMLReadWrite {
	
    /**
     * Gets all of the data within the People XML file and returns it as an ArrayList
     * @return ArrayList of each Person in the XML file 
     */
	public ArrayList readPeopleXML(){
                
                ArrayList<Person> people = new ArrayList<>();
            
		SAXBuilder builder = new SAXBuilder();
		try {
			
			// Parses the file supplied into a JDOM document
			
			Document readDoc = builder.build(new File("data/people.xml"));

			
		
			Element root = readDoc.getRootElement();
			
			// Cycles through all of the children in file and creates an Person object of them and adds them to the people array
			
			for (Element curEle : root.getChildren("person")) 
                        { 
                          people.add(new Person(curEle.getChildText("name"), 
                                  fromString(curEle.getChildText("dob")), 
                                  curEle.getChildText("description"),
                                  curEle.getChildText("role")));
			}	
		} 
		
		catch (JDOMException e) {
			e.printStackTrace();
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
                return people;
	}
        
        public GregorianCalendar fromString(String date)
        {
            String[] parts = date.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = convertMonth(parts[1])-1;
            int year = Integer.parseInt(parts[2]);
        
            return new GregorianCalendar(year, month, day);
        }
        
	
	public void writePeopleXML(String newDescription, String newDOB, String newName, String newRole) throws FileNotFoundException, JDOMException, IOException
        {
	
            Document doc = null;
            Element root = null;

            File xmlFile = new File("data/people.xml");
            if(xmlFile.exists())
            {
                FileInputStream fis = new FileInputStream(xmlFile);
                
                SAXBuilder sb = new SAXBuilder();
                
                doc = sb.build(fis);
                
                root = doc.getRootElement();
            }
            else
            {
                doc = new Document();
                root = new Element("people");
            }
        
            Element person = new Element("person");
            Element name = new Element("name");
            Element description = new Element("description");
            Element role = new Element("role");
            Element dob = new Element("dob");
            
		
		
		name.addContent(new Text(newName));
		dob.addContent(new Text(newDOB)); //this line
		description.addContent(new Text(newDescription));
                role.addContent(new Text(newRole));
		
		
		// Adds name and network to the show tag
		
		person.addContent(name);
		person.addContent(dob);
                person.addContent(description);
                person.addContent(role);
		
		// Adds the show tag and all of its children to the root
		
		root.addContent(person);
		
		// Add a new show element like above
		
		// Uses indenting with pretty format
		
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		
		
                // Create a new file and write XML to it	
		xmlOutput.output(doc, new FileOutputStream(new File("data/people.xml")));
		
	}
        
        public ArrayList readEventXML(){
                
                ArrayList<Event> events = new ArrayList<>();
            
		SAXBuilder builder = new SAXBuilder();
		try {
			
			// Parses the file supplied into a JDOM document
			Document readDoc = builder.build(new File("data/events.xml"));
		
			Element root = readDoc.getRootElement();
			
			// Cycles through all of the children in show and prints them
			
			for (Element curEle : root.getChildren("event")) 
                        {
                            ArrayList<Person> poi = new ArrayList<>();
                            //List<Element> pElements = curEle.getChild("people");
                            Element people = curEle.getChild("people");
                            
                            //System.out.println(people.getChildren("person"));
                            
                            if(people!= null)
                            {
                                List list = people.getChildren("person");
                                for(int i = 0; i<list.size(); i++)
                                {
                                    Element node = (Element) list.get(i);

                                    String data = node.getText().trim();

                                    poi.add(new Person(data));
                                }
                            }
                            //String date = curEle.getChildText("date");
                            
                            
                          events.add(new Event(curEle.getChildText("title"), 
                                  fromString(curEle.getChildText("date")), 
                                  curEle.getChildText("description"),
                                  poi));
			}	
		} 
		
		catch (JDOMException e) {
			e.printStackTrace();
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
                return events;
	}
        
        public void writeEventXML(String newDescription, String newDOB, String newName, List<Person> newPeople) throws FileNotFoundException, JDOMException, IOException
        {
	
            Document doc = null;
            Element root = null;

            File xmlFile = new File("data/events.xml");
            if(xmlFile.exists())
            {
                FileInputStream fis = new FileInputStream(xmlFile);
                
                SAXBuilder sb = new SAXBuilder();
                
                doc = sb.build(fis);
                
                root = doc.getRootElement();
            }
            else
            {
                doc = new Document();
                root = new Element("events");
            }
        
            Element event = new Element("event");
            Element title = new Element("title");
            Element description = new Element("description");
            Element date = new Element("date");
            Element poi = new Element("people");
                        
		
		title.addContent(new Text(newName));
		date.addContent(new Text(newDOB.toString())); //this line
		description.addContent(new Text(newDescription));
                
		if(newPeople != null)
                {
                    for(int i=0; i<newPeople.size(); i++)
                    {
                        Element person = new Element("person");
                        String data = newPeople.get(i).getName();
                        person.addContent(new Text(data));
                        poi.addContent(person);
                    }
                }
		
		// Adds name and network to the show tag
		
		event.addContent(title);
		event.addContent(date);
                event.addContent(description);
                event.addContent(poi);
		
                
                
		// Adds the show tag and all of its children to the root
		
		root.addContent(event);
		
		
		// Uses indenting with pretty format
		
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		
		
                // Create a new file and write XML to it	
		xmlOutput.output(doc, new FileOutputStream(new File("data/events.xml")));
                
		
	}
	
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
                default: newMonth = 1;
            }
        
        return newMonth;
    }
}
