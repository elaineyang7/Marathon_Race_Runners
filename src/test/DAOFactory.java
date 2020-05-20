package test;

/**
 * 
 * This method maps the MarathonRaceDAO interface to the appropriate data storage mechanism
 *
 */
public class DAOFactory 
{
    public static MarathonRaceDAO getMarathonRaceDAO(int choice)
    {
    	MarathonRaceDAO dao = null; 
		switch (choice) 
		{
		// Derby database
		case 1:
			dao = new RunnerDB();
			break;
			
		// XML file
		case 2:
			dao = new RunnerXMLFile();
			break;
			
		// Text file	
		case 3:
			dao = new RunnerTextFile();
			break;
			
		// Default two runners
		case 4:
		default:
			dao = new DefaultRunners();
		}
    		
        return dao;
    }
}
