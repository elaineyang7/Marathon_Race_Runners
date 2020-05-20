package test;

/**
 * In Runner class stores runner's name, speed and rest percentage
 */
public class Runner 
{
	private String name;
	private double speed;
	private double restPercentage;
	
	/**
	 * constructor
	 */
	public Runner()
    {
		name = null;
		speed = 0.0;
		restPercentage = 0.0;
    }
	
	/**
	 * 
	 * @param name
	 * @param speed
	 * @param restPercentage
	 */
	public Runner(String name, double speed, double restPercentage)
	{
		this.name = name;
		this.speed = speed;
		this.restPercentage = restPercentage;
	}
	
	/**
	 * 
	 * @param sets the runner name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * 
	 * @return name, name of the runner
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 
	 * @param sets the runner speed
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	
	/**
	 * 
	 * @return speed, speed of the runner
	 */
	public double getSpeed()
	{
		return speed;
	}
	
	/**
	 * 
	 * @param sets the runner rest percentage
	 */
	public void setRestPercentage(double restPercentage)
	{
		this.restPercentage = restPercentage;
	}
	
	/**
	 * 
	 * @return restPercentage, the runner rest percentage
	 */
	public double getRestPercentage()
	{
		return restPercentage;
	}
}