package test;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.xml.stream.*;

/**
 * This class connects to the XML file and gets every runner details
 */
public class RunnerXMLFile implements MarathonRaceDAO
{
	private Path xmlFilePath = null;
	private File xmlFile = null;
	private ArrayList<Runner> runnersList = null;
	
	@Override
	public ArrayList<Runner> getRunners(String fileName) 
	{
		// Check if file has been read already, do not read it again.
		if(runnersList != null)
			return runnersList;
		
		xmlFilePath = Paths.get(fileName);//"FinalXMLData.xml");
		xmlFile = xmlFilePath.toFile();
		
		Runner r = null;
		runnersList = new ArrayList<Runner>();
		
		if (Files.exists(xmlFilePath)) // prevent the FileNotFoundException
		{
			// create the XMLInputFactory object
            XMLInputFactory inputFactory = XMLInputFactory.newFactory();
            
            try
            {
                // create a XMLStreamReader object
                FileReader fileReader = new FileReader(xmlFile);
                XMLStreamReader reader = inputFactory.createXMLStreamReader(fileReader);

                // read the products from the file
                while (reader.hasNext())
                {
                	int eventType = reader.getEventType();
                    switch (eventType)
                    {
                        case XMLStreamConstants.START_ELEMENT:
                            String elementName = reader.getLocalName();
                            if (elementName.equals("Runner"))
                            {
                                r = new Runner();
                                String name = reader.getAttributeValue(0);
                                r.setName(name);
                            }
                            if (elementName.equals("RunnersMoveIncrement"))
                            {
                                String speedText = reader.getElementText();
                            	double speed = Double.parseDouble(speedText); 
                                r.setSpeed(speed);
                            }
                            if (elementName.equals("RestPercentage"))
                            {
                                String restPercentageText = reader.getElementText();
                                double restPercentage = Double.parseDouble(restPercentageText);
                                r.setRestPercentage(restPercentage);
                            }
                            break;
                            
                        case XMLStreamConstants.END_ELEMENT:
                            elementName = reader.getLocalName();
                            if (elementName.equals("Runner"))
                            {
                            	runnersList.add(r);
                            }
                            break;
                        default:
                            break;
                    }
                    reader.next();
                }
            }
            catch (Exception e)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
		
		return runnersList;
	}

}
