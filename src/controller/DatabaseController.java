package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import database.model.Person;
import database.view.DatabasePanel;

import javax.swing.JOptionPane;


/**
 * creates database controller
 * @author plar4927
 *
 */
/**
 * creates variables for controller
 */
public class DatabaseController 

{
 private String connectionString;
 private Connection databaseConnection;
 private AppController baseController;
 
 /**
  * constructor for controller. instantiates my variables
  */
 public DatabaseController(AppController baseController)
 {
	 connectionString = "jdbc:mysql://localhost/graveyard?user=root";
	 
	 checkDriver();
	 setUpConnection();
 }
 
 /**
  * checks that the MySQL database driver is loaded with the project.
  * Shuts the program down if it fails.
  */
 private void checkDriver()
 {
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
	 }
	 catch(Exception currentException)
	 {
		System.err.println("Check that the driver is loaded");
		System.exit(1);
	 }
 }
 
 /**
  *  sets up connection to database using connection string created in constructor
  */
 public void setUpConnection()
 {
	 try
	 {
		 databaseConnection = DriverManager.getConnection(connectionString);
	 }
	 catch(SQLException sqlError)
	 {
		 displaySQLErrors(sqlError);
	 }
	 
 }
 
 /**\
  * displays SQL errors in JOptionPanes for connection issues.
  * @param currentException
  */
 public void displaySQLErrors(SQLException currentException)
 {
	 JOptionPane.showMessageDialog(null, "The SQLException is: " + currentException.getMessage());
	 JOptionPane.showMessageDialog(null, "The SQL state is: " + currentException.getSQLState());
	 JOptionPane.showMessageDialog(null, "The SQL error code is: " + currentException.getErrorCode());
	 
 }
 
 /**
  * closes connection to database to limit corruption of data and security breaches
  */
 public void closeConnection()
 {
	 try
	 {
		 databaseConnection.close();
	 }
	 catch(SQLException currentSQLError)
	 {
		 displaySQLErrors(currentSQLError);
	 }
 }
 
 /**
  * sets the class level connection string with the supplied string object.
  * @param connectionString
  */
 public void setConnectionString( String connectionString)
 {
	 this.connectionString = connectionString;
 }
 
 /**
  * Builds a Java connectionString for a MySQL database with the supplied fields for server path, database, username, and password to access the database.
  * @param serverPath The path to the server. this can be an IP address or a URL string.
  * @param database the name of the database you are connecting to. Remember that it is case sensitive.
  * @param userName the username for the database access. If that user does not have permission the connection will fail.
  * @param password the password is clear text for the connection. If it does not hash to match the password for the user, the connection will fail.
  */ 
 public void buildConnectionString(String serverPath, String database, String userName, String password)
 {
	 connectionString = "jdbc:mysql:// " + serverPath + "/" + database + "?user=" + userName + "&password=" + password;
 }
 
 /**
  * creates a new connection without the name of the database in it.
  */
 private void clearConnection()
 {
	 closeConnection();
	 setUpConnection();
 }
 
 /**
  * creates database using String database name in my SQL
  * @param databaseName
  */
 public void createDatabase(String databaseName)
 {
	
	 try
	 {
		 Statement createDatabaseStatement = databaseConnection.createStatement();
		 
		 int result = createDatabaseStatement.executeUpdate("CREATE DATABASE " + databaseName + ";");
		 createDatabaseStatement.close();
	 }
	 catch(SQLException currentSQLError)
	 {
		 displaySQLErrors(currentSQLError);
	 }
	 closeConnection();
 }
 
 /**
  * cannot create just a database also need to create a table with rows and columns within database.
  * @param database
  * @param tableName
 // public void createTable(String database, String tableName)
 {
	 int queryIndex = connectionString.indexOf("?");
	 String connectionStart = connectionString.substring(0,queryIndex);
	 String connectionEnd = connectionString.substring(queryIndex);
	 connectionString = connectionStart + database + connectionEnd;
	 
	 setupConnection();
	 
	 try
	 {
		 Statement createTableStatement = databaseConnection.createStatement();
		 String mySQLStatement = "CREATE TABLE  `"+ database +"`.`"+ tableName +"` (`test_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
		                                                           "`test_name` VARCHAR( 50 ) NOT NULL)" +
				                                                   "ENGINE = INNODB;";
		 int result = createTableStatement.executeUpdate(mySQLStatement);
		 createTableStatement.close();
	 }
	 catch(SQLException currentSQLError)
	 {
		 displaySQLErrors(currentSQLError);
	 }
 }
 
 /**
  * Creates a person table on the supplied database. Only creates the table if it does not already exist.
  * Defines table structure to have and ID, name, birth, death, children, marital status, and age.
  * If there is a problem it will call the displaySQLErrors method.
  * @param database
  */
 public void createTable(String database, String tableName)

 {
	 clearConnection();
	 int queryIndex = connectionString.indexOf("?");
	 String connectionStart = connectionString.substring(0, queryIndex);
	 String connectionEnd = connectionString.substring(queryIndex);
	 connectionString = connectionStart + database + connectionEnd;
	 
	 setUpConnection();
	 
	 try
	 {
		 Statement createTableStatement = databaseConnection.createStatement();
		 String mySQLStatement = "CREATE TABLE `" + database + "`.`" + tableName + 
				 "` (" +
				 "`test_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
				  "`test_name` VARCHAR(50) NOT NULL" + 
				 ") " +
				 "ENGINE = INNODB;";
		 
		 int result = createTableStatement.executeUpdate(mySQLStatement);
		 createTableStatement.close(); 
	 }
	 catch(SQLException currentSQLError)
	 {
		displaySQLErrors(currentSQLError); 
	 }
	 
 }
 
 /**
  * creates tables within database with multiple fields
  * @param database
  */
 public void createPeopleTable(String database)
 {
	 clearConnection();
	 int queryIndex = connectionString.indexOf("?");
	 String connectionStart = connectionString.substring(0,queryIndex);
	 String connectionEnd = connectionString.substring(queryIndex);
	 connectionString = connectionStart + database + connectionEnd;
	 
	 setUpConnection();
	 try
	 {
		 Statement createTableStatement = databaseConnection.createStatement();
		 String createMyPersonTable = "CREATE TABLE IF NOT EXISTS `" + database + "`.`people`" +
				 							"("+
				 								"`person_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"+
				 								"`person_name` VARCHAR (50) NOT NULL,"+
				 								"'person_birth_date` VARCHAR(30), "+
				 								"`person_death_date` VARCHAR (30), "+
				 								"`person_is_married` BOOL,"+
				 								"`person_has_children` BOOL,"+
				 								"`person_age` INT"+
				 							") ENGINE = INNODB;";	
		 int result = createTableStatement.executeUpdate(createMyPersonTable);
		 createTableStatement.close();
	 }
	 catch(SQLException currentSQLError)
	 {
		 displaySQLErrors(currentSQLError);
	 }
 }
 
 /**
  * uses user input in panel to delete the database you name in Xampp
  * @param databaseName
  */
 public void deleteDatabase(String databaseName)
 {
	 clearConnection();
	 try
	 {
		 Statement deleteDatabaseStatement = databaseConnection.createStatement();
		 
		 int result = deleteDatabaseStatement.executeUpdate("DROP DATABASE " + databaseName + ";");
		 deleteDatabaseStatement.close();
	 }
	 catch(SQLException currentSQLError)
	 {
		 displaySQLErrors(currentSQLError);
	 }
 }
 
 /**
  * inserts person into the database utilizing the used fields within the table.
  * @param currentPerson
  */
 public void insertPersonIntoDatabase(Person currentPerson)
 {
	 try
	 {
		 Statement insertPersonStatement = databaseConnection.createStatement();
		 int databaseIsMarried, databaseHasChildren;
		 if(currentPerson.isMarried())
		 {
			 databaseIsMarried = 1;
		 }
		 else
		 {
			 databaseIsMarried = 0;
			
		 }
		 if(currentPerson.isHasChildren())
		 {
			 databaseHasChildren = 1;
			 
		 }
		 else
		 {
			 databaseHasChildren = 0;
		 }
		 
		 String insertString = "INSERT INTO `graveyard`.`people` " +
		 		"(`person_name`, `person_death_date`, `person_birth_date`, `person_is_married`, `person_has_children`, `person_age`) " +
				 "VALUES " +
		 		"('" + currentPerson.getName() + "','"  + currentPerson.getDeathDate() + "','" + currentPerson.getBirthDate() +
		 		"','" + databaseIsMarried + "`, `" + databaseHasChildren + "`,`" + currentPerson.isMarried() + "','" + currentPerson.isHasChildren() + "','" + currentPerson.getAge() + "');";
		 
		 int result = insertPersonStatement.executeUpdate(insertString);
		 JOptionPane.showMessageDialog(null, "Successfully inserted" + result + "rows");
	 }
	 catch(SQLException currentSQlError)
	 {
		 
	 }
	 
 }
 
 /**
  * updates the person within the table, fixing name, married, children, ect.
  * @param oldName
  * @param newName
  */
 public void updatePersonInTable(String oldName, String newName)
 {
	 try
	 {
		 Statement updateStatement = databaseConnection.createStatement();
		 String updateString = "UPDATE `graveyard`.`people` "+
				 				"SET `person_name` = `" + newName + "`" + 
				 				 "WHERE `person_name` = `" + oldName + "`"; 
		 
		 int result = updateStatement.executeUpdate(updateString);
		 JOptionPane.showMessageDialog(baseController.getMyAppFrame(), "Successfully updated" + result + "row(s)");
		 updateStatement.close();
	 }
	 catch(SQLException currentSQLError)
	 {
		 displaySQLErrors(currentSQLError);
	 }
 }
 
 /**
  * connects to external server with set IP address and creates a database on that server.
  */
 public void connectToExternalServer()
 {
	 
	 buildConnectionString("10.228.6.204", "", "ctec", "student");
	 setUpConnection();	 
	 createDatabase("Palmer");
 }
}
