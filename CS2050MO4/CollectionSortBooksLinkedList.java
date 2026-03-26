import java.util.*; //Uses everything. Pulling all the classes in the package

/**
 * 
 */
public class CollectionSortBooksLinkedList
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		// Step 1: Create a LinkedList (dynamic)
		LinkedList<Book> bookInventory = new LinkedList<>();
		//bookInventory. //Allows us to use all methods
		
		bookInventory.add(new Book("Unmasking AI", "Dr. Joy Buolamwini", 2023));
		bookInventory.add(new Book("Hello World", "Hannah Fry", 2018));
		bookInventory.add(new Book("The Mathematics of Love", "Hannah Fry", 2015));
		bookInventory.add(new Book("Weapons of Math Destruction", "Cathy O’Neil", 2016));
		bookInventory.add(new Book("Race After Technology", "Ruha Benjamin", 2019));

		System.out.println("Original LinkedList of books:");
		for (Book currentBook : bookInventory)
		{
			System.out.println(currentBook);
		}

		System.out.println();

		// Step 2: Convert to ArrayList for sorting
		List<Book> books = new ArrayList<>(bookInventory); //convert linked list to ArrayList
		//books. //Access to all the methods for ArrayList

		// Step 3: Sort by Title
		System.out.println("Books sorted by title:");
		books.sort(Comparator.comparing(Book::getTitle)); //Compare it based on title
		for (Book currentBook : books) //Sorting a copy of books so it won't affect it?
		{
			System.out.println(currentBook);
		}

		System.out.println();

		// Step 4: Sort by year (newest to oldest)
		// add code
		System.out.println("Sorted years by newest to oldest:");
		books.sort(Comparator.comparingInt(Book::getYear).reversed()); //:: is a method reference operator
		for (Book currentBook : books)
		{
			System.out.println(currentBook);
		}

		System.out.println();

		// Step 5: Sort by author then title
		// add code
		System.out.println("Sorted by author then title:");
		books.sort(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle));
		for (Book currentBook : books)
		{
			System.out.println(currentBook);
		}
		
		System.out.println();
		
		// Step 6: Add Queue for signing out books
        Queue<Book> signOutQueue = new LinkedList<>();

        // Simulate students requesting to sign out books
        signOutQueue.add(bookInventory.get(0)); // Unmasking AI
        signOutQueue.add(bookInventory.get(2)); // Weapons of Math Destruction
        
        System.out.println(); 
        
        System.out.println("Sign-out queue:");
        for (Book currentBook : signOutQueue) {
            System.out.println(currentBook);
        }

        System.out.println();

        // Step 7: Process the sign-out queue
        System.out.println("Processing sign-outs:");
        // add code
        
        
        //Step 8: Search by author
        System.out.println("\nSearching for books by Hannah Fry:");
        List<Book> searchBooksResult = findBooksByAuthor(bookInventory, "Hannah Fry");
        printBooks(searchBooksResult, "Hannah Fry", -1);
        
        //Step 9: search books by author and year
        //create a method that
        List<Book> foundBooks = findBooks(bookInventory, "Hannah Fry", 2018);
        printBooks(foundBooks, "Hannah Fry", 2018);
        
        //Step 10: Use HashMap to organize books by title
        System.out.println("\nStep 10 HashMap of books by title:");
        Map<String, Book> bookMapByTitle = new HashMap<>();
        // Using for loop to create hashmap quickly for book titles
        // Remember title must be unique
        for (Book currentBook : bookInventory)
        {
        	bookMapByTitle.put(currentBook.getTitle(), currentBook); // key = title, value = book
        	//Overrides because key is unique.
        }
        // Try getting a book by title
        String searchTitle = "Hello World";
        // containsKey Returns true if the key exists
        if (bookMapByTitle.containsKey(searchTitle))
        {
        	System.out.println("Found book: " + bookMapByTitle.get(searchTitle));
        } else
        {
        	System.out.println("Book not found: " + searchTitle);
        }
        
        System.out.println("\nFind a book by title:");
        System.out.println(bookMapByTitle.get("Unmasking AI"));
        System.out.println(bookMapByTitle.get("Not in list"));
        
        
        // step 11 Create a TreeMap to organize books by year
        System.out.println("\nCreate and print tree map");
        TreeMap<Integer, List<Book>> booksByYear = new TreeMap<>();
        // Group books by year (keys are sorted automatically)
        for (Book currentBook : bookInventory){
        	int year = currentBook.getYear();
        	if (!booksByYear.containsKey(year)) {
        	    booksByYear.put(year, new ArrayList<>());
        	}
        	booksByYear.get(year).add(currentBook);
        	//System.out.println(booksByYear);
        }

     // step 12 Print books by year (TreeMap keeps them sorted)
        for (Map.Entry<Integer, List<Book>> entry : booksByYear.entrySet()){
         	System.out.println("\nYear: " + entry.getKey());
        	for (Book book : entry.getValue())
        	{
        		System.out.println(book);
        	}
        }

        
        
    }//End main
	public static List<Book> findBooksByAuthor(List<Book> inventory, String author) {
	    List<Book> results = new ArrayList<>();
	    for (Book currentBook : inventory) {
	        if (currentBook.getAuthor().equalsIgnoreCase(author)) {
	            results.add(currentBook);
	        }
	    }
	    return results;
	}
	
	public static List<Book> findBooks(List<Book> inventory, String author, int year) {
	    List<Book> results = new ArrayList<>();

	    return results;
	}
	
	public static void printBooks(List<Book> books, String author, int year) {
	    String label;
	    if (year != -1) {
	        label = " in " + year;
	    } else {
	        label = "";
	    }

	    if (books.isEmpty()) {
	        System.out.println("\nNo books found by " + author + label + ".");
	    } else {
	        System.out.println("\nBooks by " + author + label + ":");
	        for (Book currentBook : books) {
	            System.out.println(currentBook);
	        }
	    }
	}

	
}//End class

class Book
{
	private String title;
	private String author;
	private int year;

	public Book(String title, String author, int year)
	{
		this.title = title;
		this.author = author;
		this.year = year;
	}

	public String getTitle()
	{
		return title;
	}

	public String getAuthor()
	{
		return author;
	}

	public int getYear()
	{
		return year;
	}

	public String toString()
	{
		return title + " by " + author + " (" + year + ")";
	}
}