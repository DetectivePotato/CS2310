import java.util.ArrayList;

/**
 * Book represents a collection of chaptered or volumed Paragraph objects and provides
 * 
 * @author Anthony Wall
 * @version 08/12/2016
 */
public class Book 
{
	private ArrayList<Paragraph> paragraphs;
	private String title;
	private String author;
	
	/**
	 * Construct an empty Book object and initialise the List
	 */
	public Book()
	{
		paragraphs = new ArrayList<Paragraph>();
		title = "";
		author = "";
	}
	
	/**
	 * Construct a Book object using a provided List of Paragraph objects
	 * 
	 * @param paragraphArray The List of Paragraph objects to add to the Book
	 */
	public Book(ArrayList<Paragraph> paragraphArray)
	{
		this();
		
		//Add each Paragraph to the Book
		for(Paragraph paragraph : paragraphArray)
		{
			paragraphs.add(paragraph);
		}
	}
	
	/**
	 * Looks through all Paragraph objects stored in the Book and returns the Lines in an indexed form
	 * 
	 * @param target The word to look for
	 * @return A list of all Line objects containing the target word
	 */
	public Line[] contains(String target)
	{
		ArrayList<Line> searchResults = new ArrayList<Line>();
		
		//Search through each paragraph adding any relevant lines to the results
		for(Paragraph paragraph : paragraphs)
		{
			searchResults.addAll(paragraph.contains(target));
		}	
		return searchResults.toArray(new Line[searchResults.size()]);
	}
	
	/**
	 * Returns a String representation of the entire Book
	 */
	public String toString()
	{
		
		StringBuilder bookString = new StringBuilder();
		
		//Iterate through each paragraph in the Book looking for
		//any chapter or volume changes and add it all to the String
		for(int i = 0; i < paragraphs.size(); i++)
		{
			Paragraph currentParagraph = paragraphs.get(i);
			
			//Add the current Paragraph to the String and if needed add the carraige returns
			bookString.append(currentParagraph.toString());
			if(i < (paragraphs.size()-1))
				bookString.append("\n\n");
		}
		
		return bookString.toString();
	}
	
	/**
	 * Adds a paragraph to the Book
	 * 
	 * @param paragraph The Paragraph object to add
	 */
	public void addParagraph(Paragraph paragraph)
	{
		paragraphs.add(paragraph);
	}
	
	/**
	 * Set the author of this book
	 * 
	 * @param newAuthor the String author to add
	 */
	public void setAuthor(String newAuthor)
	{
		author = newAuthor;
	}
	
	/**
	 * Set the title of this book
	 * 
	 * @param newTitle the String title to add
	 */
	public void setTitle(String newTitle)
	{
		title = newTitle;
	}
	
	/**
	 * Return the Paragraph stored at the given index
	 * 
	 * @param index the Paragraph index to get from this Book
	 */
	public Paragraph getParagraph(int index)
	{
		return paragraphs.get(index);
	}
	
	/**
	 * Return the number of Paragraph objects stored in the Book
	 */
	public int paragraphSize()
	{
		return paragraphs.size();
	}
}
