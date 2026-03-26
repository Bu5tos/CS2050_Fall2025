import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class L15WorkingWithFilesLab {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Book unitTestPrintBook = new PrintBook ("Unmasking AI", "Joy Buolamwini", 2023);
		Book unitTestEBook = new EBook ("Deep Learning", "Ian Goodfellow", 2016);
	

		
		//Library setup
		System.out.println("Setting up Test Library");
		int numberOfShelves = 3;
		int shelfCapacity = 4;
		System.out.println("Shelves (rows): " + numberOfShelves);
		System.out.println("Slots per shelf (columns): " + shelfCapacity);
		System.out.println("Total capacity: " + (numberOfShelves * shelfCapacity));

		/**
		Library library = new Library("Test Library", numberOfShelves, shelfCapacity);
		System.out.println("Loading books from file: library_books.csv");
		LibraryLoader.loadFromCsv(library, "library_books.csv");
		library.displayCountPerShelf();
		library.printAllBooks();
		library.displayOldest();
		*/
		
		//Output test for PrintBook
		System.out.print("[Print, " + unitTestPrintBook.toString() + unitTestPrintBook.getLoanDays() + " days, ");
		System.out.printf("$" + unitTestPrintBook.getDailyLateFee() + "/day]");
		System.out.println();
		
		//Output test for EBook
		System.out.print("[EBook, " + unitTestEBook.getLoanDays() + " days, ");
		System.out.printf("$" + unitTestEBook.getDailyLateFee() + "/day]");
		System.out.println();
		
	} //End Main
	
} //End Class

abstract class Book {
	
	//Instance variables
	private String author;
	private String title;
	private int year;
	
	public Book() {
		
	}
	
	//Constructor
	public Book (String author, String title, int year) {
		this.author = author;
		this.title = title;
		this.year = year;
	}
	
	//Methods
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return author + title + year;
	}
	
	//Shared behavior using polymorphism
	/** Calculates the late fee based on subclass policy */
	public final double calculateLateFee(int daysLate) {
		double lateFee = 0;
		
		if (lateFee > 0) {
			lateFee = daysLate * getDailyLateFee();
		}
		return lateFee;	
	}
	
	//Abstract method
	public abstract int getLoanDays();
	
	public abstract double getDailyLateFee();
	
}//End Book class

class PrintBook extends Book {
	
	public PrintBook() {
		
	}
	
	public PrintBook(String author, String title, int year) {
		//super(author, title, year);
		
		setTitle(title);
		setAuthor(author);
		setYear(year);
	}
		
	@Override
	public String toString() {
		return "Author: " + getAuthor() + "Book title: " + getTitle() + "Year: " + getYear();
	}
	
	public int getLoanDays() {
			int loanDays = 21;
			return loanDays;
		}
		
	public double getDailyLateFee() {
		double lateFee = 0.25;
		return lateFee;
	}
		
}//End PrintBook class

class EBook extends Book {
	
	public EBook(String title, String author, int year) {
		super(author, title, year);
	}
	
	@Override
	public String toString() {
		return "Author " + getAuthor() + "Title " + getTitle() + "Year " + getYear();
	}
	
	
	public int getLoanDays() {
		int loanDays = 14;
		return loanDays;
	}
	
	
	public double getDailyLateFee() {
		double lateFee = 0.10;
		return lateFee;
	}
	
}//End EBook
	
class Library {
	
	private String name;
	private Book[][] bookShelf;
	private int numberOfShelves;
	private int shelfCapacity;
	private int currentShelf;
	private int currentSlot;
	private int currentTotalBooks;
	private int totalBookCapacity;
	private boolean isFull;
	
	public Library(String name, int numberOfShelves, int shelfCapacity) {
		this.name = name;
		this.numberOfShelves = numberOfShelves;
		this.shelfCapacity = shelfCapacity;
		this.currentTotalBooks = 0;
		this.totalBookCapacity = numberOfShelves * shelfCapacity;
		
		//Initializing 2D Array 
		bookShelf = new Book [numberOfShelves][shelfCapacity];
		
		//Fills bookShelf's rows and columns
		for(int row = 0; row < numberOfShelves; row++) {
			for(int col = 0; col < shelfCapacity; col++) {			
				bookShelf[row][col] = null; //(row + 1) * (col + 1)
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getCurrentTotalBooks() {
		return currentTotalBooks;
	}
	
	public int getTotalBookCapacity() {
		return totalBookCapacity;
	}
	
	public boolean addBook(Book book) {
		
		//Return false if book is null
		if (book == null) {
			System.out.println("Invalid Book");
			return false;
		}
		
		//Can't add certain book if library is full
		if (isFull) {
			System.out.println("Library is full, can't add " + book.toString());
			return false;
		}
		
		bookShelf[currentShelf][currentSlot] = book;
		System.out.println("Added" + book.toString() + "at shelf" + (currentShelf + 1) + "slot" + (currentSlot + 1));
		currentTotalBooks++;
		
		if (currentTotalBooks >= totalBookCapacity) {
			isFull = true;
		} else {
			int nextIndex = currentTotalBooks;
			currentShelf = nextIndex / shelfCapacity;
			currentSlot = nextIndex % shelfCapacity;
		}
		return true;
	}
	
	public void printAllBooks() {
		
		System.out.println("All books in " + getName() + "\n");
		System.out.println("Shelf   Slot   Book Details");
		
		//For each slot it would display book details
		for (int shelfIndex = 0; shelfIndex < numberOfShelves; shelfIndex++) {
			
			//Reuse helper method for each shelf row
			printListBooks(bookShelf[shelfIndex], shelfIndex + 1);
			
		}
		System.out.println();
		System.out.println("(" + currentTotalBooks + " of " + (numberOfShelves * shelfCapacity) + "slots filled)\n");

		}
	
	private void printListBooks(Book[]shelf, int shelfIndex) {
		
		for (int columnIndex = 0; columnIndex < shelf.length; columnIndex++) {
			Book currentBook = shelf[columnIndex];
			if (currentBook != null) {
				System.out.println("Shelf" + shelfIndex + " Slot " + (columnIndex + 1) + " " + currentBook.toString());
			}
		}
	}
	
	public void displayOldest() {
		
		Book[] allBooks = convertOneDimension();
		if (allBooks.length == 0)
		{
			System.out.println("Display Oldest: Library is empty.");
			return;
		}
		// Pass 1: find min year
		int earliestYear = allBooks[0].getYear();
		for (int i = 1; i < allBooks.length; i++)
		{
			if (allBooks[i].getYear() < earliestYear)
			{
				earliestYear = allBooks[i].getYear();
			}
		}
		// Pass 2: print all matches
		System.out.println("------------------------------------------------------------");
		System.out.println("Oldest books in " + getName());
		System.out.println("Earliest publication year: " + earliestYear);
		System.out.println();
		for (int i = 0; i < allBooks.length; i++)
		{
			if (allBooks[i].getYear() == earliestYear)
			{
				System.out.println(allBooks[i].toString());
			}
		}
	}
	
	private Book[] convertOneDimension() {
		Book[] oneDimension = new Book[currentTotalBooks];
		int index = 0;
		
		for (int shelfIndex = 0; shelfIndex < numberOfShelves; shelfIndex++) {
			for (int slotIndex = 0; slotIndex < shelfCapacity; slotIndex++) {
				if ( bookShelf[shelfIndex][slotIndex] != null) {
					oneDimension[index] = bookShelf[shelfIndex][slotIndex];
					index = index + 1;
					if (index >= currentTotalBooks) {
						return oneDimension; //Once all books are copied, exit early
					}
				}
			}
		}
		return oneDimension;
	}
	
	public void displayCountPerShelf() {
		int rows = currentTotalBooks / shelfCapacity;
		int remainder = currentTotalBooks % shelfCapacity;
		
		for(int rowIndex = 0; rowIndex < numberOfShelves; rowIndex++) {
			int thisShelf;
			
			if (rowIndex < rows) 
			{
				thisShelf = shelfCapacity;
			} else if (rowIndex == rows) 
			{
				thisShelf = remainder;
			} else
			{
				thisShelf = 0;
			}
			System.out.println("Shelf " + (rowIndex + 1) + " has " + thisShelf + " books");
		}
	}
	
}//End Library class

class LibraryLoader
{
public static void loadFromCsv(Library library, String filename)
{
    try (Scanner fileScan = new Scanner(new File(filename))){
	int lineNumber = 0;
	while (fileScan.hasNextLine()){
		String line = fileScan.nextLine();
		lineNumber++;
		Book parsed = parseBookLine(line, lineNumber);
		if (parsed != null){
		     boolean added = library.addBook(parsed);
		     if (!added){
			System.out.println("Line " + lineNumber + ": library full or invalid book.");
			}
		}
	}
      } catch (FileNotFoundException ex){
		System.out.println("Could not open file: " + filename);
     }
}
/**
* Parses one CSV line into a Book or returns null if invalid. Expected:
* title,author,year,type (type = P or E) Keeps logic simple for lecture.
*/
private static Book parseBookLine(String line, int lineNumber){
	if (line == null){
		System.out.println("Line " + lineNumber + ": empty line.");
		return null; // early return
	}
	String[] parts = line.split(",");
	if (parts.length != 4){
	      System.out.println("Line " + lineNumber + ": wrong number of fields → " + line);
	      return null; // early return
	}
	String title = parts[0].trim();
	String author = parts[1].trim();
	String yearText = parts[2].trim();
	String type = parts[3].trim();
	int year;
	try{
		year = Integer.parseInt(yearText);
	} catch (NumberFormatException ex){
	     System.out.println("Line " + lineNumber + ": invalid year \"" + yearText + "\" → skipping line.");
	     return null; // early return
	}
	if (type.equalsIgnoreCase("P")){
		return new PrintBook(title, author, year);
	} else if (type.equalsIgnoreCase("E")){
		return new EBook(title, author, year);
	} else{
	     System.out.println("Line " + lineNumber + ": invalid type \"" + type + "\" (use P or E).");
	     return null; // early return
	}
}
}//End LibrariLoader