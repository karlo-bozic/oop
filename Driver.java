/*
This class connects the SQL database with the eclipse
It sends query to the database and also displays the results in the table
*/

package jdbcdemo;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class Driver {

	static ArrayList<Covid> display(String QUERY) {
		//Creates a ArrayList that will hold data from the database and this will be used to create a table
		ArrayList<Covid> covidList = new ArrayList<>();
		
		try {
			//Connects to the sql database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "root");
				
			//This creates a statement so that the query can be sent
			Statement myStmt = myConn.createStatement();

			//This sends out the given query to the database and receives the results
			ResultSet myRs = myStmt.executeQuery(QUERY);
									
			while (myRs.next()) {
				Covid covid = new Covid(myRs.getInt("OBJECTID"), myRs.getString("CountyName"), myRs.getString("TimeStamp"), myRs.getInt("ConfirmedCovidCases"), myRs.getInt("PopulationProportionCovidCases"));
				covidList.add(covid);
			}			
			
		}
		catch (Exception exc) {
			//Displays that there was an error
			JOptionPane.showMessageDialog(null, "Error, please retry");
		}
		//Returns the ArrayList
		return covidList;
	}

}


