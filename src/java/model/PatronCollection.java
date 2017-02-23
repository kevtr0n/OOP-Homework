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
public class PatronCollection extends EntityBase {
    private static final String myTableName = "Patron";
    private Vector<Patron> patrons;
    private String updateStatusMessage = "";

    // Constructor.
    public PatronCollection() {
        super(myTableName);
        Vector<Patron> patronCollection = new Vector<>();
    }

    public Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                patrons = new Vector<Patron>();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    Patron patron = new Patron(data);
                    if (patron != null) {
                        addPatron(patron);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return patrons;
    }

    public Vector findPatronsByPatronId(String patronId) {
        String query = "SELECT * FROM " + myTableName + " WHERE (patronId = " + patronId + ")";
        return runQuery(query);
    }

    public Vector findPatronsByAddress(String address) {
        String query = "SELECT * FROM " + myTableName + " WHERE (address = " + address + ")";
        return runQuery(query);
    }

    public Vector findPatronsByEmail(String email) {
        String query = "SELECT * FROM " + myTableName + " WHERE (email = " + email + ")";
        return runQuery(query);
    }

    public Vector findPatronsByStatus(String status) {
        String query = "SELECT * FROM " + myTableName + " WHERE (status = " + status + ")";
        return runQuery(query);
    }

    public Vector findPatronsByDateOfBirth(String dateOfBirth) {
        String query = "SELECT * FROM " + myTableName + " WHERE (birthDate = " + dateOfBirth + ")";
        return runQuery(query);
    }

    public Vector findPatronsOlderThan(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth < " + date + ")";
        return runQuery(query);
    }

    public Vector findPatronsYoungerThan(String date) {
        String query = "SELECT * FROM " + myTableName + " WHERE (dateOfBirth > " + date + ")";
        return runQuery(query);
    }

    public Vector findPatronsAtZipCode(String zip) {
        String query = "SELECT * FROM " + myTableName + " WHERE zip LIKE '%" + zip + "%'";
        return runQuery(query);
    }

    public Vector findPatronsAtStateCode(String state) {
        String query = "SELECT * FROM " + myTableName + " WHERE stateCode LIKE '%" + state + "%'";
        return runQuery(query);
    }

    public Vector findPatronsAtCity(String city) {
        String query = "SELECT * FROM " + myTableName + " WHERE city LIKE '%" + city + "%'";
        return runQuery(query);
    }

    public Vector findPatronsWithNameLike(String name) {
        String query = "SELECT * FROM " + myTableName + " WHERE name LIKE '%" + name + "%'";
        return runQuery(query);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    protected void initializeSchema(String tableName) {
        if (mySchema == null) {
            mySchema = getSchemaInfo(tableName);
        }
    }

    public Object getState(String key) {
        if (key.equals("UpdateStatusMessage") == true) {
            return updateStatusMessage;
        }
        return persistentState.getProperty(key);
    }
}
