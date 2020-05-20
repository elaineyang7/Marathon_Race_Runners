package test;

/**
 * 
 * This class runs thread for each runner object and simulates the race
 * 
 */
public class ThreadRunner extends Thread
{
	private String name;
	private double speed;
	private double restPercentage;
	boolean executed = false;
	private MarathonRaceApp app;
	boolean firstThread;
	
	/**
	 * 
	 * constructor set app object, runner's name, speed, and rest percentage
	 * 
	 * @param app, an object of the application class
	 * @param name
	 * @param speed
	 * @param restPercentage
	 */
	public ThreadRunner(MarathonRaceApp app, String name, double speed, double restPercentage) 
	{
		this.app = app;
		this.name = name;
		this.speed = speed;
		this.restPercentage = restPercentage;
	}
	
	/**
	 * @return name, runner's name of thread
	 */
    public String getRunnerName()
    {
        return name;
    }  
    
    @Override
	public void run()
	{
		int distanceCovered = 0;
		
		try
		{
			// loops until the runner reaches the total race distance
			while (distanceCovered < 1000) 
			{
				int randomNumber = (int) (Math.random() * 100 + 1);

				if (randomNumber <= restPercentage) 
				{
					Thread.sleep(200);
				}
				else
				{
					distanceCovered += speed; 
					System.out.println(name + " : " + distanceCovered);
				}
				Thread.sleep(100);
			} 	

			// the first thread is true and all other threads are false.
			firstThread = app.isFirst(); 
			
			// winner thread do this
			if (firstThread == true) 
			{
				System.out.println(name + " : I finished!");
				System.out.println("");
				
				// winner shows The race is over!
				System.out.println("The race is over! The " + name + " is the winner. \n");
				app.interruptThreads(name);
				
				return;
			}
			// other loser threads sleep for a while
			else 
			{
				Thread.sleep(200); 
			}
		}
		catch (InterruptedException e) 
		{}
		
		System.out.println(name + " : You beat me fair and square."); 
	}
   
}
