/*
This class connects the SQL database with the eclipse
It sends query to the database and also displays the results in the table
*/

package jdbcdemo;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Driver {

	static ArrayList<Covid> display(String QUERY) {

		ArrayList<Covid> covidList = new ArrayList<>();
		
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop", "root", "root");
				
			Statement myStmt = myConn.createStatement();

			ResultSet myRs = myStmt.executeQuery(QUERY);
			
			System.out.println("OBJECTID, County Name, Time Stamp, Confirmed Covid Cases, Population Proportion Covid Cases ");
						
			while (myRs.next()) {
				Covid covid = new Covid(myRs.getInt("OBJECTID"), myRs.getInt("ORIGID"), myRs.getString("TimeStamp"), myRs.getInt("ConfirmedCovidCases"), myRs.getInt("PopulationProportionCovidCases"));
				covidList.add(covid);
				
				System.out.println(myRs.getString("OBJECTID") + ", " + myRs.getString("ORIGID") + ", " + myRs.getString("TimeStamp")  + ", " + myRs.getString("ConfirmedCovidCases") + ", " + myRs.getString("PopulationProportionCovidCases"));
			}			
			
		}
		catch (Exception exc) {
			JOptionPane.showMessageDialog(null, "Error, please retry");
			exc.printStackTrace();
		}
		return covidList;
	}
	
	public void show() {
		ArrayList<Covid> list = display();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Object[] row = new Object[5];
		
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getOBJECTID();
			row[1]=list.get(i).getCountyName();
			row[2]=list.get(i).getTimeStamp();
			row[3]=list.get(i).getConfirmedCovidCases();
			row[4]=list.get(i).getPopulationProportionCovidCases();
			model.addRow(row);
		}
	}

}


