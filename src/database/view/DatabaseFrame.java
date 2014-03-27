package database.view;

import java.awt.Panel;

import javax.swing.JFrame;
import controller.AppController;


public class DatabaseFrame extends JFrame

{	
	private AppController baseController;
	private DatabasePanel myDatabasePanel;
	
	public DatabaseFrame(AppController baseController)
	{
		this.baseController = baseController;
		myDatabasePanel = new DatabasePanel(baseController);
		setupFrame();
	}
		
	private void setupFrame()
	{
		this.setContentPane(myDatabasePanel);
		this.setSize(400,400);
		this.setVisible(true);
	}
	
}
