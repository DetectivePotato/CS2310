import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TextReader takes a formatted *.txt file and converts it into Lines and Paragraphs for a Book object
 * 
 * @author Anthony Wall
 */
public class TextReader 
{
	private Book book;
	
	/**
	 * Construct a TextReader, creating an empty Book object
	 */
	public TextReader()
	{
		book = new Book();
	}
	
	/**
	 * Read a given *.txt file and convert it into a Book object
	 * 
	 * @param filePath the *.txt file to read
	 * @return a Book object representation of the *.txt file
	 */
	public Book readFile(String filePath)
	{
		BufferedReader reader = null;
		
		try
		{
			//Declare placeholder Strings for the BufferedReader
			String currentLine = "";
			String bookString = "";
			
			reader = new BufferedReader(new FileReader(filePath));
			
			//Read each line and store it for later parsing
			while((currentLine = reader.readLine()) != null)
			{
				bookString += currentLine;
			}
			
			//Split the text into paragraphs and add it to the Book
			String[] paragraphs = bookString.split("(?m)(?=^\\s{2})");
			convertParagraphsToBook(paragraphs);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(reader != null)
					reader.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
		
		return book;
	}
	
	/**
	 * Convert a String representation of the Book into Paragraphs and add them to the Book
	 * 
	 * @param paragraphs The string Book to be converted
	 */
	public void convertParagraphsToBook(String[] paragraphs)
	{
		//Add each Paragraph to the Book
		for(String paragraph : paragraphs)
		{
			book.addParagraph(new Paragraph(paragraph));
		}
	}
}
