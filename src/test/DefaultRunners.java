package test;

import java.util.*;

/**
 * 
 * return the details of default runners
 *
 */
public class DefaultRunners implements MarathonRaceDAO
{
	private ArrayList<Runner> runnersList = new ArrayList<>();
	
	@Override
	public ArrayList<Runner> getRunners(String fileName) 
	{
		return null;
	}
	
	/**
	 * 
	 * @return runnersList, list of two runners
	 */
	public ArrayList<Runner> getRunners() 
	{
		// Set Runner 1 Tortoise and add Runner object to ArrayList
		Runner r1 = new Runner();
		r1.setName("Tortoise");
		r1.setSpeed(10);
		r1.setRestPercentage(0);
		
		runnersList.add(r1);
		
		// Set Runner 2 Hare and add Runner object to ArrayList
		Runner r2 = new Runner();
		r2.setName("Hare");
		r2.setSpeed(100);
		r2.setRestPercentage(90);
		
		runnersList.add(r2);
		
		return runnersList;	
	}
}
