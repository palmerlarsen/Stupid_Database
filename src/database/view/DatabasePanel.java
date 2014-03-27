package database.view;
import controller.AppController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import database.model.Person;

public class DatabasePanel extends JPanel
{
	
	private AppController baseController;
	private JButton createDatabaseButton;
	private JButton deleteDatabaseButton;
	private JButton insertPersonButton;
	private JButton createTableButton;
	private JButton updateButton;
	private JButton connectToExternalButton;
	public JTextField databaseNameField;
	public JTextField tableNameField;
	public JTextField ageField;
	public JTextField nameField;
	public JTextField deathDateField;
	public JTextField birthDateField;
	public JTextArea Area1;
	private SpringLayout baseLayout;
	
	public DatabasePanel(AppController baseController)
	{
		this.baseController = baseController;
			
		createDatabaseButton = new JButton("Create database");		
		deleteDatabaseButton = new JButton("Delete database");
		insertPersonButton = new JButton("Insert Person into database");
		createTableButton = new JButton("Insert Table into database");
		updateButton = new JButton("Update person");
		connectToExternalButton = new JButton("Connect to External database");
		databaseNameField = new JTextField("Name of database");
		tableNameField = new JTextField("Name of Table");
		ageField = new JTextField("Person's Age");
		nameField = new JTextField("Person's Name");
		deathDateField = new JTextField("Person's Deathdate");
		birthDateField = new JTextField("Person's Birthdate");
		Area1 = new JTextArea("Area1");	
		baseLayout = new SpringLayout();
		
		
		
		setupPanel();
		setupLayout();
		setupListeners();
		
	}	
	
		private void setupPanel()
		{
			this.add(createDatabaseButton);
			this.add(deleteDatabaseButton);
			this.add(insertPersonButton);
			this.add(createTableButton);
			this.add(updateButton);
			this.add(databaseNameField);
			this.add(connectToExternalButton);
			this.add(tableNameField);
			this.add(ageField);
			this.add(nameField);
			this.add(deathDateField);
			this.add(birthDateField);
			this.setLayout(baseLayout);
			this.add(Area1);
			
			this.setBackground(Color.BLUE);
			this.setSize(500,500);
			this.setBackground( new Color(130,100,50));
		}
		
		private boolean checkInteger(String current)
		{
			boolean isInteger = false;
			
			try
			{
				Integer.parseInt(current);
				isInteger = true;
			}
			catch(NumberFormatException currentException)
			{
				JOptionPane.showMessageDialog(this,"This number cannot be changed to a string");
			}
			
			return isInteger;
		}
		
		private Person createPersonFromInput()
		{
			Person deadPerson = null;
			int deadPersonAge = 0;
					
			if(checkInteger(ageField.getText()))
			{
				deadPersonAge = Integer.parseInt(ageField.getText());
			}
			
			String name = nameField.getText();
			String deathDate = deathDateField.getText();
			
			deadPerson = new Person(name, deathDate);
			deadPerson.setAge(deadPersonAge);
			
			return deadPerson;
		}
		
		private void setupLayout()
		{
			
			baseLayout.putConstraint(SpringLayout.NORTH, connectToExternalButton, 6, SpringLayout.SOUTH, updateButton);
			baseLayout.putConstraint(SpringLayout.WEST, connectToExternalButton, 0, SpringLayout.WEST, createTableButton);
			baseLayout.putConstraint(SpringLayout.NORTH, updateButton, 8, SpringLayout.SOUTH, createTableButton);
			baseLayout.putConstraint(SpringLayout.WEST, updateButton, 0, SpringLayout.WEST, tableNameField);
			baseLayout.putConstraint(SpringLayout.NORTH, createTableButton, 19, SpringLayout.SOUTH, Area1);
			baseLayout.putConstraint(SpringLayout.EAST, createTableButton, 0, SpringLayout.EAST, insertPersonButton);
			baseLayout.putConstraint(SpringLayout.WEST, ageField, 0, SpringLayout.WEST, databaseNameField);
			baseLayout.putConstraint(SpringLayout.SOUTH, ageField, -6, SpringLayout.NORTH, birthDateField);
			baseLayout.putConstraint(SpringLayout.EAST, ageField, 0, SpringLayout.EAST, nameField);
			baseLayout.putConstraint(SpringLayout.NORTH, nameField, 5, SpringLayout.SOUTH, insertPersonButton);
			baseLayout.putConstraint(SpringLayout.WEST, nameField, 92, SpringLayout.WEST, this);
			baseLayout.putConstraint(SpringLayout.EAST, nameField, -299, SpringLayout.EAST, this);
			baseLayout.putConstraint(SpringLayout.WEST, birthDateField, 0, SpringLayout.WEST, databaseNameField);
			baseLayout.putConstraint(SpringLayout.SOUTH, birthDateField, -6, SpringLayout.NORTH, deathDateField);
			baseLayout.putConstraint(SpringLayout.EAST, birthDateField, -26, SpringLayout.EAST, insertPersonButton);
			baseLayout.putConstraint(SpringLayout.WEST, deathDateField, 0, SpringLayout.WEST, databaseNameField);
			baseLayout.putConstraint(SpringLayout.SOUTH, deathDateField, -6, SpringLayout.NORTH, Area1);
			baseLayout.putConstraint(SpringLayout.EAST, deathDateField, -26, SpringLayout.EAST, insertPersonButton);
			baseLayout.putConstraint(SpringLayout.NORTH, Area1, 246, SpringLayout.NORTH, this);
			baseLayout.putConstraint(SpringLayout.WEST, tableNameField, 10, SpringLayout.WEST, databaseNameField);
			baseLayout.putConstraint(SpringLayout.SOUTH, tableNameField, -6, SpringLayout.NORTH, insertPersonButton);
			baseLayout.putConstraint(SpringLayout.WEST, Area1, 0, SpringLayout.WEST, databaseNameField);
			baseLayout.putConstraint(SpringLayout.EAST, Area1, -26, SpringLayout.EAST, insertPersonButton);
			baseLayout.putConstraint(SpringLayout.NORTH, createDatabaseButton, 30, SpringLayout.NORTH, this);
			baseLayout.putConstraint(SpringLayout.NORTH, databaseNameField, 12, SpringLayout.SOUTH, createDatabaseButton);
			baseLayout.putConstraint(SpringLayout.WEST, databaseNameField, 92, SpringLayout.WEST, this);
			baseLayout.putConstraint(SpringLayout.NORTH, insertPersonButton, 60, SpringLayout.SOUTH, createDatabaseButton);
			baseLayout.putConstraint(SpringLayout.WEST, insertPersonButton, 60, SpringLayout.WEST, this);
			baseLayout.putConstraint(SpringLayout.NORTH, deleteDatabaseButton, 0, SpringLayout.NORTH, createDatabaseButton);
			baseLayout.putConstraint(SpringLayout.WEST, deleteDatabaseButton, 6, SpringLayout.EAST, createDatabaseButton);
			baseLayout.putConstraint(SpringLayout.WEST, createDatabaseButton, 22, SpringLayout.WEST, this);
		}
		
		private void setupListeners()
		{
			databaseNameField.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					databaseNameField.setText("");
				}
				
			});

			ageField.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					ageField.setText("");
				}
				
			});
			
			nameField.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					nameField.setText("");
				}
				
			});
			
			deathDateField.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					deathDateField.setText("");
				}
				
			});
			
			birthDateField.addMouseListener(new MouseAdapter() 

			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					birthDateField.setText("");
				}
				
			});
			
			connectToExternalButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
					baseController.getMyDataController().connectToExternalServer();
				}
			});
			
			createDatabaseButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						baseController.getMyDataController().createDatabase(databaseNameField.getText());
						//baseController.getMyDataController().createPeopleTable(tableNameField.getText());
					}
				});
			
			createTableButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						baseController.getMyDataController().createPeopleTable(tableNameField.getText());
					}
				});
			
			deleteDatabaseButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{

					baseController.getMyDataController().deleteDatabase(databaseNameField.getText());
					//baseController.getMyDataController().deleteDatabase(tableNameField.getText());
				}
			});
			
			updateButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
					
					baseController.getMyDataController().updatePersonInTable(nameField.getText(), deathDateField.getText());
				}
			});
			
			insertPersonButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent click)
				{
					
					Person currentPerson = new Person();
					currentPerson.setName(nameField.getText());
					currentPerson.setDeathDate(deathDateField.getText());
					currentPerson.setAge(Integer.parseInt(ageField.getText()));
					
					baseController.getMyGraveyardPeople().add(currentPerson);
					baseController.getMyDataController().insertPersonIntoDatabase(currentPerson);
					
				}
				
			});
		
		}
		
}

