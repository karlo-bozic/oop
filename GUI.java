package jdbcdemo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener, MouseListener {
	
	JPanel upper, mid1, mid2, bot1, bot2;
	JButton button1, button2, button3;
	JTextField text1, text2;
	JLabel label, label2, label3;
	JComboBox<String> comboBox;
	//static JLabel label2;
	
    String[] counties = {"Select All", "Carlow", "Cavan", "Clare", "Cork", "Donegal", "Dublin", "Galway", "Kerry", "Kildare", "Kilkenny",
    		"Laois", "Leitrim", "Limerick", "Longford", "Louth", "Mayo", "Meath", "Monaghan", "Offaly", "Roscommon", "Sligo",
    		"Tipperary", "Waterford", "Westmeath", "Wexford", "Wicklow"};

	
	GUI (String title)
	{
		super(title);
		setSize(400,400);
		setLayout(new FlowLayout());
		
		button1 = new JButton("Show entire table");
		button2 = new JButton("Filter By");
		button3 = new JButton("Enter the query");
		
		label = new JLabel(" Filter ");
		label2 = new JLabel("Custom Query");
		
		text1 = new JTextField("",7);
		text1.setToolTipText("dd/mm/yyyy");
		
		text2 = new JTextField("",15);
		text2.setToolTipText("Enter own Query");
		
		comboBox = new JComboBox<String>(counties);
		

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		upper = new JPanel(new FlowLayout(1,10,10));
		mid1 = new JPanel(new FlowLayout(1,100,30));
		mid2 = new JPanel(new FlowLayout(1,50,10));
		bot1 = new JPanel(new FlowLayout(1,20,30));
		bot2 = new JPanel(new FlowLayout(1,20,10));
		

		upper.add(button1);
		add(upper);
		
		mid1.add(label);
		add(mid1);
		mid2.add(comboBox);
		mid2.add(text1);
		mid2.add(button2);
		add(mid2);
			
		bot1.add(label2);
		add(bot1);
		bot2.add(text2);
		bot2.add(button3);
		add(bot2);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == button1)
		{
			Driver.display("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID ORDER BY OBJECTID");
		}
		else if (e.getSource() == button2)
		{
	        String county=(String)comboBox.getSelectedItem();
	        String date = text1.getText();

	        if (county == "Select All"  && date.trim().equals(""))
	        {
				Driver.display("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID ORDER BY OBJECTID");		        JOptionPane.showMessageDialog(this, "Select All with no date");
	        }
	        else if(county == "Select All")
	        {
		        JOptionPane.showMessageDialog(this, "Select All with a set date");
				Driver.display("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID WHERE covid.TimeStamp='"+date+ "' ORDER BY OBJECTID");
	        }
	        else if (date.trim().equals(""))
	        {
		        Driver.display("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID WHERE counties.CountyName='"+county+"' ORDER BY OBJECTID");

		        JOptionPane.showMessageDialog(this, "County with no date");
	        }
	        else
	        {
		        Driver.display("SELECT * FROM covid INNER JOIN counties ON covid.ORIGID=counties.ORIGID WHERE counties.CountyName='"+county+"' ORDER BY OBJECTID");
	        }
		}
		else if (e.getSource() == button3)
		{
			
			if (text2.getText() == null || text2.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Nothing was entered");
			}
			else
			{
				String input = text2.getText();
				Driver.display(input);
			}
		}
	}

}
