package model;
import exception.InvalidPrimaryKeyException;
import model.EntityBase;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;
import java.util.Vector;


public class Patron extends EntityBase
{
    private static final String myTableName = "Patron";
    protected Properties dependencies;

    // GUI stuff
    private String updateStatusMessage = "";

    // Constructor
    public Patron(String patronId) throws InvalidPrimaryKeyException
    {
        super(myTableName);
        setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (patronId = "
                + patronId + ")";
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);
        // You must get one account at least
        if(allDataRetrieved != null)
        {
            int size = allDataRetrieved.size();
            // There should be EXACTLY one patron. Any more is an error.
            if(size != 1)
            {
                throw new InvalidPrimaryKeyException("Multiple accounts matching id : "
                        + patronId + " found.");
            }
            else
            {
                // Copy all the retrieved data into persistent state.
                Properties retrievedPatronData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();
                Enumeration allKeys = retrievedPatronData.propertyNames();
                while(allKeys.hasMoreElements() == true)
                {
                    String nextKey = (String)allKeys.nextElement();
                    String nextValue = retrievedPatronData.getProperty(nextKey);
                    if(nextValue != null)
                    {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }
            }
        }
        else
        {
            throw new InvalidPrimaryKeyException("No patron matching id : "
                    + patronId + " found.");
        }
    }

    public Patron(Properties props)
    {
        super(myTableName);
        setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while(allKeys.hasMoreElements() == true)
        {
            String nextKey = (String)allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);
            if(nextValue != null)
            {
                persistentState.setProperty(nextKey, nextValue);
            }
        }
    }
	
	public static int compare(Patron first, Patron second)
	{
		String firstPatron = (String)first.getState("patronId");
		String secondPatron = (String)second.getState("patronId");
		return firstPatron.compareTo(secondPatron);
	}
	
    private void setDependencies()
    {
        dependencies = new Properties();
        myRegistry.setDependencies(dependencies);
    }

    public Object getState(String key)
    {
        if(key.equals("UpdateStatusMessage") == true)
            return updateStatusMessage;
        return persistentState.getProperty(key);
    }

    public void stateChangeRequest(String key, Object value)
    {
        myRegistry.updateSubscribers(key, this);
    }

    public void updateState(String key, Object value)
    {
        stateChangeRequest(key, value);
    }

    public void update()
    {
        updateStateInDatabase();
    }

    private void updateStateInDatabase()
    {
        try
        {
            if(persistentState.getProperty("patronId") != null)
            {
                Properties whereClause = new Properties();
                whereClause.setProperty("patronId", persistentState.getProperty("patronId"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "Patron data for patron number : " + persistentState.getProperty("patronId")
                        + " updated successfull in database!";
            }
            else
            {
                Integer patronId = insertAutoIncrementalPersistentState(mySchema, persistentState);
                persistentState.setProperty("patronId", "" + patronId.intValue());
                updateStatusMessage = "Patron data for new patron : " + persistentState.getProperty("patronId")
                        + " installed successfully in database!";
            }
        }
        catch (SQLException e)
        {
            updateStatusMessage = "Error in installing patron data in database!";
        }
    }

    public String getPatronId() {
        return persistentState.getProperty("patronId");
    }

    public String getName() {
        return persistentState.getProperty("name");
    }

    public String getAddress() {
        return persistentState.getProperty("address");
    }

    public String getCity() {
        return persistentState.getProperty("city");
    }

    public String getStateCode() {
        return persistentState.getProperty("stateCode");
    }

    public String getZip() {
        return persistentState.getProperty("zip");
    }

    public String getEmail() {
        return persistentState.getProperty("email");
    }

    public String getDateOfBirth() {
        return persistentState.getProperty("dateOfBirth");
    }

    public String getStatus() {
        return persistentState.getProperty("status");
    }

	public String toString()
	{
		return persistentState.getProperty("patronId") + "; " + 
			   persistentState.getProperty("name") + "; " + 
			   persistentState.getProperty("address") + "; " +
			   persistentState.getProperty("city") + "; " +
			   persistentState.getProperty("stateCode") + "; " +
			   persistentState.getProperty("zip") + "; " +
			   persistentState.getProperty("email") + "; " +
			   persistentState.getProperty("dateOfBirth") + "; " +
			   persistentState.getProperty("status");
	}

    protected void initializeSchema(String tableName)
    {
        if(mySchema == null)
        {
            mySchema = getSchemaInfo(tableName);
        }
    }
}