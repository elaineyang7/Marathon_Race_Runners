package test;

import java.util.*;

/**
 * 
 * This the application’s main class
 *
 */
public class MarathonRaceApp 
{
	private static Scanner sc = new Scanner(System.in);
	private ArrayList<Runner> runnerList = null;
	private static MarathonRaceDAO dao = null;
	ArrayList<Thread> threadList = null;
	static boolean executed = false;
	
	public static void main(String args[])
	{
		MarathonRaceApp app = new MarathonRaceApp();
		
		int choice = 0;
		
		while (choice != 5)
		{
			executed = false;
			
			System.out.println("Welcome to the Marathon Race Runner Program.");
			System.out.println("");
			
			// Presents data source option for the user to select
			System.out.println("Select your data source:");
			System.out.println("");
			
			System.out.println("1. Derby database \n"
					+ "2. XML File \n"
					+ "3. Text File \n"
					+ "4. Default two runners \n"
					+ "5. Exit \n");
			
			// Get valid user input
			choice = Validator.getIntWithinRange(sc, "Enter your choice: ", 1, 5);
			System.out.println();
			
			dao = DAOFactory.getMarathonRaceDAO(choice);
			
			app.getDataName(choice);
		}
		
	}
	
	/**
	 *  asks for file name and creates threads
	 *  
	 *  @param choice, the number that user enter
	 */
	public void getDataName(int choice)
	{
		String fileName = "";
		MarathonRaceApp app = new MarathonRaceApp();
		
		switch (choice)
		{
			case 1: fileName = Validator.getRequiredString("Enter Database name: ", sc);
					app.createThreads(fileName);
					break;
					
			case 2: fileName = Validator.getRequiredString("Enter XML file name: ", sc);
					app.createThreads(fileName);
					break;
						
			case 3:	fileName = Validator.getRequiredString("Enter Text file name: ", sc);
					app.createThreads(fileName);
					break;
					
			case 4: app.createThreads("");
					break;
					
			case 5: System.out.println("Thank you for using my Marathon Race Program"); 
					System.exit(0);
					
			default: break;
		}
	}
	
	/**
	 *  creates thread for each runner
	 *  
	 *  @param fileName, the file name of the database
	 */
	public void createThreads(String fileName)
	{
		// Get all the runners from the data source
		if (fileName.equals(""))
		{
			DefaultRunners defaultRunners = (DefaultRunners) dao;
			runnerList = defaultRunners.getRunners();
		}
		else
			runnerList = dao.getRunners(fileName);
		
		if (runnerList == null) 
		{
			System.err.println("Error! Unable to get runners. Please re-enter valid data source.\n");
		}
		else 
		{
			threadList = new ArrayList<>();
			ThreadRunner thread = null;
					
			System.out.println("Get set...Go!");
			
			// start thread for each runner
			for (int i = 0; i < runnerList.size(); i++)
			{
				String name = runnerList.get(i).getName();
				double speed = runnerList.get(i).getSpeed();
				double restPercentage = runnerList.get(i).getRestPercentage();
				
				// Create thread for each Runner
				thread = new ThreadRunner(this, name, speed, restPercentage);
				thread.start(); 
				threadList.add(thread);
			}
	
			// one of the threads can call when it finishes the race
			finished(); 
			
			System.out.println();
			
			pressEnterToContinue();
		}
	}
	
	/**
	 *  the first finishes thread will access to here
	 */
	public synchronized boolean isFirst()
	{
		if (executed == false)
		{
			// only winner thread can set executed to true
			executed = true; 
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 *  interrupts the other threads once the winner thread is found
	 *  
	 *  @param threadName
	 */
	public void interruptThreads(String threadName)
	{
		for (int i = 0; i < threadList.size(); i++)
		{
			if (!threadName.equals(((ThreadRunner) threadList.get(i)).getRunnerName()))
			{
				// interrupts other threads when one thread completes
				threadList.get(i).interrupt(); 
			}
		}
	}
	
	/**
	 *  one of the threads can call when it finishes the race
	 */
	private void finished()
	{
		for (int i = 0; i < threadList.size(); i++)
		{
			try 
			{
				threadList.get(i).join();
				
			}
			catch (InterruptedException e)	
			{}
		}
	}
	
	 
	/**
	 *  press enter key to continue
	 */
	private void pressEnterToContinue()
	 { 
	        System.out.println("Press enter to continue . . .");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
}
