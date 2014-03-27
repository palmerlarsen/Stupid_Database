package controller;
import database.model.GraveMarker;

import java.awt.LayoutManager;
import java.util.ArrayList;
import database.view.DatabaseFrame;
import database.model.Person;

/**
 * 
 * @author plar4927
 *
 */
public class AppController 
{

	private DatabaseController myDataController;
	private DatabaseFrame myAppFrame;
	private ArrayList<GraveMarker> myGraveMarkers;
	private ArrayList<Person> myGraveyardPeople;
	
	public AppController()
	{
		myDataController = new DatabaseController(this);
		myGraveMarkers = new ArrayList<GraveMarker>();
		myGraveyardPeople = new ArrayList<Person>();
	}
	
	public DatabaseController getMyDataController()

	{
		return myDataController;
	}
	
	public ArrayList<GraveMarker> getMyGraveMarkers()

	{
		return myGraveMarkers;
	}
	
	public DatabaseFrame getMyAppFrame()
	{
		return myAppFrame;
	}	

	public ArrayList<Person> getMyGraveyardPeople()
	{
		return myGraveyardPeople;
	}

	public void start()
	{
		myAppFrame = new DatabaseFrame(this);
	}
	
}
