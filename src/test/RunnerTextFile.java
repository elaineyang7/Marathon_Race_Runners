package test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class connects to the Text file and gets all runners detail
 */
public class RunnerTextFile implements MarathonRaceDAO
{
	private Path textFilePath = null;
	private File textFile = null;
	private ArrayList<Runner> runnersList = null;
	
	
	@Override
	public ArrayList<Runner> getRunners(String fileName) 
	{
		// if the products file has already been read, don't read it again
		if (runnersList != null)
			return runnersList;
		
		textFilePath = Paths.get(fileName);//"FinalTextData.txt");
		textFile = textFilePath.toFile();
		
		runnersList = new ArrayList<>();
		
		if(Files.exists(textFilePath)) // prevent the FileNotFoundException
		{
			
			try (BufferedReader in = new BufferedReader(
									 new FileReader(textFile)))
			{
				// read all runners stored in the file
                // into the array list
				String line = null;
				while ((line = in.readLine()) != null)
				{
					StringTokenizer t = new StringTokenizer(line, " ");
					
					String name = t.nextToken();
					double speed = Double.parseDouble(t.nextToken());
					double restPercentage = Double.parseDouble(t.nextToken());
					
					Runner r = new Runner();
					
					r.setName(name);
					r.setSpeed(speed);
					r.setRestPercentage(restPercentage);
					
					// add runner to the ArrayList
					runnersList.add(r);
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
