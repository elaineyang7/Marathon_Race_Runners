package test;

import java.util.*;
import java.sql.*;

/**
 * 
 * This class connects to the Database and gets all runners detail
 *
 */
public class RunnerDB implements MarathonRaceDAO 
{
	private ArrayList<Runner> runnerList = null;
	
	/**
	 * 
	 * This method connects to the database and returns the connection object
	 * 
	 * @param fileName, name of the database
	 * @return connection object
	 */
	public static Connection connect(String fileName)
    {
        Connection connection = null;
        try
        {
            String dbDirectory = "Resources";
            System.setProperty("derby.system.home", dbDirectory);

            // set the db url, username, and password
            String url = "jdbc:derby:db/" + fileName;
            String user = "";
            String password = "";

            connection = DriverManager.getConnection(url, user, password);
            
            return connection;
        }
        catch (SQLException e)
        {
            System.err.println("Error loading database driver: " + e);
            return null;
        }
    }
	
	@Override
	public ArrayList<Runner> getRunners(String fileName) 
	{
		try
		{
			Connection connection = connect(fileName);
			runnerList = new ArrayList<>();
			
			// query the runner table
			String query = "SELECT Name, RunnersSpeed, RestPercentage "
						 + "FROM RunnersStats";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				String name = rs.getString("Name");
				double speed = rs.getDouble("RunnersSpeed");
				double restPercentage = rs.getDouble("RestPercentage");
				
				Runner r = new Runner(name, speed, restPercentage);
				runnerList.add(r);
			}
			rs.close();
			ps.close();
			connection.close();
			return runnerList;
			
		}
		catch (Exception e)
		{
			return null;
		}
		
	}
}
