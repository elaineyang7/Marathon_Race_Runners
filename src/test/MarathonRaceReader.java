package test;

import java.util.ArrayList;

/**
 * This interface has an abstract method 
 */
public interface MarathonRaceReader 
{
	/**
	 * abstract method which takes filename as input and returns a list of runner object.
	 * 
	 * @param fileName
	 * @return list of runners
	 */
	public ArrayList<Runner> getRunners(String fileName);
}
