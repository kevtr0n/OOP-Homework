package model;

import java.sql.*;

import database.*;
import model.*;
import exception.InvalidPrimaryKeyException;
import database.JDBCBroker;
import database.SQLQueryStatement;

import java.util.Properties;
import java.util.Vector;

/**
 * Author:      Kevin Hayden
 * Date:        January 31st, 2017
 * Class:       CSC429 - Object-Oriented Software Development
 * Professor:   Dr. Sandeep Mitra
 */
public class BookCollection extends EntityBase {
    private static final String myTableName = "Book";
    private Vector<Book> books;
    private String updateStatusMessage = "";

    // Constructor.
    public BookCollection() {
        super(myTableName);
        books = new Vector();
    }

    public Vector runQuery(String query) {
        try {
            Vector allDataRetrieved = getSelectQueryResult(query);
            if (allDataRetrieved != null) {
                books = new Vector<Book>();
                for (int index = 0; index < allDataRetrieved.size(); index++) {
                    Properties data = (Properties) allDataRetrieved.elementAt(index);
                    Book book = new Book(data);
                    if (book != null) {
                        addBook(book);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return books;
    }

    public Vector findBooksByBookId(String bookId) {
        String query = "SELECT * FROM " + myTableName + " WHERE patronId LIKE '%" + bookId + "%'";
        return runQuery(query);
    }

    public Vector findBooksByPubYear(String year) {
        String query = "SELECT * FROM " + myTableName + " WHERE pubYear LIKE '%" + year + "%'";
        return runQuery(query);
    }

    public Vector findBooksOlderThanDate(String year) {
        String query = "SELECT * FROM " + myTableName + " WHERE (pubYear < " + year + ")";
        return runQuery(query);
    }

    public Vector findBooksNewerThanDate(String year) {
        String query = "SELECT * FROM " + myTableName + " WHERE (pubYear > " + year + ")";
        return runQuery(query);
    }

    public Vector findBooksWithTitleLike(String title) {
        String query = "SELECT * FROM " + myTableName + " WHERE title LIKE '%" + title + "%'";
        return runQuery(query);
    }

    public Vector findBooksWithAuthorLike(String author) {
        String query = "SELECT * FROM " + myTableName + " WHERE author LIKE '%" + author + "%'";
        return runQuery(query);
    }

    private void addBook(Book book) {
        books.add(book);
    }

    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    public Vector getVector() {
        return books;
    }

    public void updateState(String key, Object value) {
        stateChangeRequest(key, value);
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