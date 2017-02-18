package model;
import model.PatronCollection;
import model.BookCollection;
import event.Event;
import impresario.IView;
import java.util.Properties;
import java.util.Vector;

/**
 * Author:      Kevin Hayden
 * Date:        January 31st, 2017
 * Class:       CSC429 - Object-Oriented Software Development
 * Professor:   Dr. Sandeep Mitra
 */
public class PatronCollection extends EntityBase
{
    private static final String myTableName = "Patron";
    private Vector<Patron> patrons;
    private String updateStatusMessage = "";

    // Constructor.
    public PatronCollection ()
    {
        super(myTableName);
        Vector<Patron> patronCollection = new Vector<>();
    }
	
    public Vector runQuery(String query)
    {
		try
		{
			Vector allDataRetrieved = getSelectQueryResult(query);
			if(allDataRetrieved != null)
			{
				patrons = new Vector<Patron>();
				for(int index = 0; index < allDataRetrieved.size();  index++)
				{
					Properties data = (Properties)allDataRetrieved.elementAt(index);
					Patron patron = new Patron(data);
					if(patron != null)
					{
						addPatron(patron);
					}
				}	
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e);
		}
		return patrons;
    }

    public Vector findPatronsOlderThan(String date)
    {
        String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth < " + date + ")";
        return runQuery(query);
    }

    public Vector findPatronsYoungerThan(String date)
    {
        String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth > " + date + ")";
        return runQuery(query);
    }

    public Vector findPatronsAtZipCode(String zip)
    {
        String query = "SELECT * FROM " + myTableName + " WHERE zip LIKE '%" + zip + "%'";
        return runQuery(query);
    }

    public Vector findPatronsAtStateCode(String state)
    {
        String query = "SELECT * FROM " + myTableName + " WHERE stateCode LIKE '%" + state + "%'";
        return runQuery(query);
    }

    public Vector findPatronsAtCity(String city)
    {
        String query = "SELECT * FROM " + myTableName + " WHERE city LIKE '%" + city + "%'";
        return runQuery(query);
    }

    public Vector findPatronsWithNameLike(String name)
    {
        String query = "SELECT * FROM " + myTableName + " WHERE name LIKE '%" + name + "%'";
        return runQuery(query);
    }
	/**
	public static void printPatronsOlderThanQuery(String date)
	{
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsOlderThan(date);
		
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	
	public static void printPatronsYoungerThanQuery(String date)
	{
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsYoungerThan(date);
		
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	
	public static void printPatronsAtZipCodeQuery(String zip)
	{
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsAtZipCode(zip);
		
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	
	public static void printPatronsAtStateCodeQuery(String state)
	{
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsAtStateCode(state);
		
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	
	public static void printPatronsAtCityQuery(String city)
	{
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsAtCity(city);
		
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	
	public static void printPatronsWithNameQuery(String name)
	{
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsWithNameLike(name);
		
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}**/
	
	public void addPatron(Patron patron)
	{
		patrons.add(patron);
	}

    public void stateChangeRequest (String key, Object value)
    {
        myRegistry.updateSubscribers(key, this);
    }

    protected void initializeSchema(String tableName)
    {
        if (mySchema == null)
        {
            mySchema = getSchemaInfo(tableName);
        }
    }

    public Object getState(String key)
    {
        if (key.equals("UpdateStatusMessage") == true) {
            return updateStatusMessage;
        }
        return persistentState.getProperty(key);
    }
}
