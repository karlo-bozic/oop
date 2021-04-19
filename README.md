Control Class
This is the class the program runs first, this calls to the GUI class which then displays the GUI.

GUI Class
This class creates the interface that allows you to go through the program. Using the ArrayList from Driver class, the data gets put in their columns and get displayed as tables on another frame

Covid Class
There is one constructor which takes the array of strings from the database. There is only getters in this class

Driver Class
This is where the connection between the program and sql database happens. They connect by using JDBC. There is a ArrayList, as the results from the query come into the program, the data goes into the ArrayList using the getters from the Covid Class. This ArrayList will then be used in displaying the table. If there is any error during the connection or storing proccess, an error message will appear to let the user know.

============================
Quick Description of Program
============================
This program connects the sql database with the java using JDBC. The main purpose of this program is to ask the user if/what to filter the database by and sends the query to the database and using the results, it will display them in a table. This is all done through the GUI. The way the user can filter thorugh the table is from comboBox or textFields throughout the GUI, the program reads what was inputted and will run the query with those filters. This program also has options for more felixable queries, there is a Custom Query box, where you can enter your own sql query and that also gets display on the table. The table is displayed in another frame and each time you change the filter, the table will clear and only display the new data.
