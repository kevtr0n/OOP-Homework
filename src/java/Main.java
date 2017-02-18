import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;
import database.*;
import model.*;

public class Main 
{
    public static void main(String[] args)
    {
		int input;
        do
		{
			String queryParam = "";
			Scanner keyboard = new Scanner(System.in);
			// Welcome.
			System.out.println("Welcome to the Library System!");
			System.out.print("Press...\n'1' to insert book.\n" +
							 "'2' to insert a patron.\n" +
							 "'3' to search for books older than a target date.\n" +
							 "'4' to search for books newer than a target date.\n" +
							 "'5' to search for books by title.\n" +
							 "'6' to search for books by author.\n" +
							 "'7' to search for patrons older than a target date.\n" +
							 "'8' to search for patrons younger than a target date.\n" +
							 "'9' to search for patrons at a target zip code.\n" +
							 "'10' to search for patrons at a target state.\n" +
							 "'11' to search for patrons at a target city.\n" +
							 "'12' to search for patrons by name.\n" +
							 "Entry: ");
			// Choose operation. 
				
			input = keyboard.nextInt();
			keyboard.nextLine();
			switch (input)
			{
				case 0:
				{
					System.out.println("Goodbye!");
					break;
				}
				case 1:
				{
					insertBookIntoDatabase(); break;
				}
				case 2:
				{
					insertPatronIntoDatabase(); break;
				}
				case 3:
				{
					printBooksOlderThanQuery(); break;
				}
				case 4:
				{
					printBooksNewerThanQuery();	break;
				}
				case 5:
				{
					printBooksLikeTitleQuery();	break;
				}
				case 6:
				{
					printBooksLikeAuthorQuery(); break;
				}
				case 7:
				{
					printPatronsOlderThanQuery(); break;
				}
				case 8:
				{
					printPatronsYoungerThanQuery(); break;
				}
				case 9:
				{
					printPatronsAtZipCodeQuery(); break;
				}
				case 10:
				{
					printPatronsAtStateCodeQuery(); break;
				}
				case 11:
				{
					printPatronsAtCityQuery(); break;
				}
				case 12:
				{
					printPatronsWithNameQuery(); break;
				}
			} 
		} while (input != 0);	
    }
	
	public static void insertBookIntoDatabase()
	{
		String[] bookArray = {"bookId", "author", "title", "pubYear", "status"};
		Scanner keyboard = new Scanner(System.in);
		Properties props = new Properties();
		for(int i = 1; i < bookArray.length; i++)
		{
			System.out.print("Please enter " + bookArray[i] + ": ");
			String bookData = keyboard.nextLine();
			props.put(bookArray[i], bookData);
		}	
		Book newBook = new Book(props);
		newBook.update();
		System.out.println("(" + newBook.toString() + ") has been added.");
	}
	
	public static void insertPatronIntoDatabase()
	{
		String[] patronArray = {"patronId", "name", "address", "city", "stateCode",
								"zip", "email", "dateOfBirth", "status"};
		Scanner keyboard = new Scanner(System.in);
		Properties props = new Properties();
		for(int j = 1; j < patronArray.length; j++)
		{
			System.out.print("Please enter " + patronArray[j] + ": ");
			String patronData = keyboard.nextLine();
			props.put(patronArray[j], patronData);
		}
		Patron newPatron = new Patron(props);			
		newPatron.update();
		System.out.println("(" + newPatron.toString() + ") has been added.");
	}
	
	public static void printBooksOlderThanQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter date: ");
		String queryParam = keyboard.nextLine();
		BookCollection bookCol = new BookCollection();
		Vector books = bookCol.findBooksOlderThanDate(queryParam);
		for(int index = 0; index < books.size(); index++)
		{
			System.out.println(books.elementAt(index).toString());
		}
	}
	public static void printBooksNewerThanQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter date: ");
		String queryParam = keyboard.nextLine();
		BookCollection bookCol = new BookCollection();
		Vector books = bookCol.findBooksNewerThanDate(queryParam);
		for(int index = 0; index < books.size(); index++)
		{
			System.out.println(books.elementAt(index).toString());
		}
	}
	public static void printBooksLikeTitleQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter title: ");
		String queryParam = keyboard.nextLine();
		BookCollection bookCol = new BookCollection();
		Vector books = bookCol.findBooksWithTitleLike(queryParam);
		for(int index = 0; index < books.size(); index++)
		{
			System.out.println(books.elementAt(index).toString());
		}
	}
	public static void printBooksLikeAuthorQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter author: ");
		String queryParam = keyboard.nextLine();
		BookCollection bookCol = new BookCollection();
		Vector books = bookCol.findBooksWithAuthorLike(queryParam);
		for(int index = 0; index < books.size(); index++)
		{
			System.out.println(books.elementAt(index).toString());
		}
	}
	public static void printPatronsOlderThanQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter date: ");
		String queryParam = keyboard.nextLine();
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsOlderThan(queryParam);
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	public static void printPatronsYoungerThanQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter date: ");
		String queryParam = keyboard.nextLine();
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsYoungerThan(queryParam);
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	public static void printPatronsAtZipCodeQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter zip: ");
		String queryParam = keyboard.nextLine();
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsAtZipCode(queryParam);
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	public static void printPatronsAtStateCodeQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter state: ");
		String queryParam = keyboard.nextLine();
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsAtStateCode(queryParam);
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	public static void printPatronsAtCityQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter city: ");
		String queryParam = keyboard.nextLine();
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsAtCity(queryParam);
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
	public static void printPatronsWithNameQuery()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter name: ");
		String queryParam = keyboard.nextLine();
		PatronCollection patronCol = new PatronCollection();
		Vector patrons = patronCol.findPatronsWithNameLike(queryParam);
		for(int index = 0; index < patrons.size(); index++)
		{
			System.out.println(patrons.elementAt(index).toString());
		}
	}
}