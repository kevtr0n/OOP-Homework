package model;

import exception.InvalidPrimaryKeyException;
import impresario.IModel;
import impresario.IView;

import java.util.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;
import java.util.Vector;

/**
 * Author:      Kevin Hayden
 * Date:        January 31st, 2017
 * Class:       CSC429 - Object-Oriented Software Development
 * Professor:   Dr. Sandeep Mitra
 */
public class Book extends EntityBase {
    private static final String myTableName = "Book";
    protected Properties dependencies;

    // GUI Stuff
    private String updateStatusMessage = "";

    // Constructor
    public Book(String bookId) throws InvalidPrimaryKeyException {
        super(myTableName);
        setDependencies();
        String query = "SELECT * FROM " + myTableName + " WHERE (BookID = " + bookId + ")";
        Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

        // You must get at least one book.
        if (allDataRetrieved != null) {
            int size = allDataRetrieved.size();

            // There should be EXACTLY one book.
            if (size != 1) {
                throw new InvalidPrimaryKeyException("Multiple books mathcing id : "
                        + bookId + " found.");
            }
            // Cope all the retrieved data into persistent state
            else {
                Properties retrievedBookData = allDataRetrieved.elementAt(0);
                persistentState = new Properties();

                Enumeration allKeys = retrievedBookData.propertyNames();
                while (allKeys.hasMoreElements() == true) {
                    String nextKey = (String) allKeys.nextElement();
                    String nextValue = retrievedBookData.getProperty(nextKey);
                    if (nextValue != null) {
                        persistentState.setProperty(nextKey, nextValue);
                    }
                }
            }
        } else {
            throw new InvalidPrimaryKeyException("No book matching ID : "
                    + bookId + " found.");
        }
    }

    public Book(Properties props) {
        super(myTableName);
        setDependencies();
        persistentState = new Properties();
        Enumeration allKeys = props.propertyNames();
        while (allKeys.hasMoreElements() == true) {
            String nextKey = (String) allKeys.nextElement();
            String nextValue = props.getProperty(nextKey);
            if (nextValue != null) {
                persistentState.setProperty(nextKey, nextValue);
            }
        }
    }

    private void setDependencies() {
        dependencies = new Properties();
        myRegistry.setDependencies(dependencies);
    }

    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage") == true)
            return updateStatusMessage;
        return persistentState.getProperty(key);
    }

    public void stateChangeRequest(String key, Object value) {

        myRegistry.updateSubscribers(key, this);
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
    }

    public void update() {
        updateStateInDatabase();
    }

    public static int compare(Book first, Book second) {
        String firstNum = (String) first.getState("title");
        String secondNum = (String) second.getState("title");
        return firstNum.compareTo(secondNum);
    }

    private void updateStateInDatabase() {
        try {
            if (persistentState.getProperty("bookId") != null) {
                Properties whereClause = new Properties();
                whereClause.setProperty("bookId", persistentState.getProperty("bookId"));
                updatePersistentState(mySchema, persistentState, whereClause);
                updateStatusMessage = "Book data for book ID : " + persistentState.getProperty("bookId")
                        + "installed successfully in database!";
            } else {
                Integer bookId = insertAutoIncrementalPersistentState(mySchema, persistentState);
                persistentState.setProperty("bookId", "" + bookId.intValue());
                updateStatusMessage = "Book data for new book : " + persistentState.getProperty("bookId")
                        + "installed successfully in database";
            }
        } catch (SQLException e) {
            updateStatusMessage = "Error in installing account data in database!";
        }
    }

    public String getBookId() {
        return persistentState.getProperty("bookId");
    }

    public String getAuthor() {
        return persistentState.getProperty("author");
    }

    public String getTitle() {
        return persistentState.getProperty("title");
    }

    public String getPubYear() {
        return persistentState.getProperty("pubYear");
    }

    public String getStatus() {
        return persistentState.getProperty("status");
    }

    public String toString() {
        return persistentState.getProperty("bookId") + "; " +
                persistentState.getProperty("author") + "; " +
                persistentState.getProperty("title") + "; " +
                persistentState.getProperty("pubYear") + "; " +
                persistentState.getProperty("status");
    }
}