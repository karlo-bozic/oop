package jdbcdemo;

import java.sql.*;

import javax.swing.JOptionPane;

public class Driver {
	static void display(String QUERY) {

		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "root");
				
			Statement myStmt = myConn.createStatement();
				
			ResultSet myRs = myStmt.executeQuery(QUERY);
				
			
			while (myRs.next()) {
				
				System.out.println(myRs.getString("OBJECTID") + ", " + myRs.getString("CountyName") + ", " + myRs.getString("TimeStamp")  + ", " + myRs.getString("ConfirmedCovidCases") + ", " + myRs.getString("PopulationProportionCovidCases"));
				//System.out.println(myRs.getString("ORIGID") + ", " + myRs.getString("CountyName"));
			}
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "Error, please retry");
			exc.printStackTrace();
		}
	}
}


