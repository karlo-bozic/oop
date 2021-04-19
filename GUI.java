/*
This class creates the GUI for the assignment
It performs the actions depending on what the users do/input

*/

package jdbcdemo;


import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


//Gui class that implements from ActionListener
public class GUI extends JFrame implements ActionListener {

	JPanel displayAllPanel, filterLabelPanel, filterPanel, customLabelPanel, textPanel, customPanel;
	JButton displayAll, filterBy, customQuery;
	JTextField dateFilter;
	TextArea queryText;
	JLabel filterLabel, customLabel;
	JComboBox<String> countyName;
	JTable table;
	DefaultTableModel model = new DefaultTableModel();
	String Query;

	//This string is for the combobox
    String[] counties = {"Select All", "Carlow", "Cavan", "Clare", "Cork", "Donegal", "Dublin", "Galway", "Kerry", "Kildare", "Kilkenny",
    		"Laois", "Leitrim", "Limerick", "Longford", "Louth", "Mayo", "Meath", "Monaghan", "Offaly", "Roscommon", "Sligo",
    		"Tipperary", "Waterford", "Westmeath", "Wexford", "Wicklow"};
    	
	GUI (String title)
	{
		super(title);
		setSize(400,450); //sets the size for the GUI window
		setLayout(new FlowLayout()); 
		JPanel panel = new JPanel();
		JFrame jf = new JFrame();

		displayAll = new JButton("Show entire table");
		filterBy = new JButton("Filter By");
		customQuery = new JButton("Enter the query");
		
		filterLabel = new JLabel(" Filter ");
		customLabel = new JLabel("Custom Query");
		
		dateFilter = new JTextField("",7);
		dateFilter.setToolTipText("dd/mm/yyyy");
		
		queryText = new TextArea(3,45);
		
		//Creates the table with the given columns
		table = new JTable(model);
		model.addColumn("ID"); 
		model.addColumn("County Name");
		model.addColumn("Date"); 
		model.addColumn("Covid Cases"); 
		model.addColumn("PopulationProportionCovidCases"); 
		
		countyName = new JComboBox<String>(counties);
		
		displayAll.addActionListener(this);
		filterBy.addActionListener(this);
		customQuery.addActionListener(this);
		
		displayAllPanel = new JPanel(new FlowLayout(1,10,10));
		filterLabelPanel = new JPanel(new FlowLayout(1,100,30));
		filterPanel = new JPanel(new FlowLayout(1,50,10));
		customLabelPanel = new JPanel(new FlowLayout(1,20,30));
		textPanel = new JPanel(new FlowLayout(1,20,10));
		customPanel = new JPanel(new FlowLayout(1,20,10));
		
		displayAllPanel.add(displayAll);
		add(displayAllPanel);
		
		filterLabelPanel.add(filterLabel);
		add(filterLabelPanel);
		
		filterPanel.add(countyName);
		filterPanel.add(dateFilter);
		filterPanel.add(filterBy);
		add(filterPanel);

		customLabelPanel.add(customLabel);
		add(customLabelPanel);
		
		textPanel.add(queryText);
		add(textPanel);
		
		//customPanel
		customPanel.add(customQuery);
		add(customPanel);
		
		//Adds a new frame for the table
		JScrollPane scrollBar=new JScrollPane(panel); //Allows the table to have a scroll bar
		jf.add(scrollBar); 
        jf.setSize(500, 500);
        jf.setVisible(true);    
        panel.add(table);

        table.setBounds(10, 36, 527, 214); //Sets the bounds to the table so that the column name will appear
        panel.add(new JScrollPane(table));
        
		setVisible(true);
	}
	
	@Override
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == displayAll) //When the Display All button gets pressed
		{
			Query = ("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID ORDER BY OBJECTID"); //Sends the query to the database
		}
		else if (e.getSource() == filterBy) //When the Filter By button gets pressed
		{
	        String county = (String)countyName.getSelectedItem(); //Takes in what the user picked from combobox
	        String date = dateFilter.getText();   //Takes in what the user enetred for date in textfield

	        if (county == "Select All"  && date.trim().equals("")) //When user chooses select all (all counties) and no date was entered
	        {
				Query = ("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID ORDER BY OBJECTID");
	        }
	        else if(county == "Select All") //When user chooses select all (all counties) and a date was entered
	        {
		        Query = ("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID WHERE covid.TimeStamp='"+date+ "' ORDER BY OBJECTID");
	        }
	        else if (date.trim().equals("")) //When county name was chosen and no date was eneterd
	        {
	        	Query = ("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID WHERE counties.CountyName='"+county+"' ORDER BY OBJECTID");
	        }
	        else //When the county name and the date was chosen
	        {
	        	Query = ("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID WHERE counties.CountyName='"+county+"' AND covid.TimeStamp='"+date+"' ORDER BY OBJECTID");
	        }
		}
		else if (e.getSource() == customQuery) //When the Custom Query button gets pressed
		{
			
			if (queryText.getText() == null || queryText.getText().trim().equals("")) //When nothing is entered in the textarea
			{
				JOptionPane.showMessageDialog(this, "Nothing was entered");
			}
			else
			{
				Query  = queryText.getText(); //Sends the query that was written to the database
			}
		}
		
		
		ArrayList<Covid> list = Driver.display(Query);
		Object[] row = new Object[5]; //Sets the rows to 5
		model.setRowCount(0); //Clears the table
		
		//For loop gets data from ArrayList and places them into the row of the tables
		for(int i = 0; i < list.size(); i++ ) {
			row[0] = list.get(i).getOBJECTID();
			row[1] = list.get(i).getCountyName();
			row[2] = list.get(i).getTimeStamp();
			row[3] = list.get(i).getConfirmedCovidCases();
			row[4] = list.get(i).getPopulationProportionCovidCases();
			model.addRow(row); //Adds the row to table
		}
	}

}
