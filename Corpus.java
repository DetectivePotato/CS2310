import java.util.ArrayList;

/**
 *  Corpus represents a collection of books and provides functionality for KWIC searching through the collection
 *  
 * @author Anthony Wall
 * @version 12/12/2016
 *
 */
public class Corpus
{
	private ArrayList<Book> books;
	
	/**
	 * Construct an empty Corpus
	 */
	public Corpus()
	{
		books = new ArrayList<Book>();
	}
	
	/**
	 * Construct a Corpus with an initial Book object
	 * 
	 * @param book the Book object to add into the Corpus
	 */
	public Corpus(Book book)
	{
		this();
		
		books.add(book);
	}
	
	/**
	 * Return the number of Book objects stored in this Corpus
	 */
	public int size()
	{
		return books.size();
	}
	
	/**
	 * Return the Book object stored at the given index in this Corpus
	 * 
	 * @param index the index Book to return
	 */
	public Book getBook(int index)
	{
		return books.get(index);
	}
	
}
