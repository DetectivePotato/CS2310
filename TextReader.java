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
				bookString += currentLine + "\n";
			}
			//Split the text into paragraphs and add it to the Book
			convertToParagraphs(bookString);
			
			//Free memory being used by the String
			bookString = "";
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				book.toString();
				
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
	private void convertToParagraphs(String bookString)
	{
		int emptyParagraphs = 0;
		Paragraph paragraph = new Paragraph();
		
		//Split the line into each word
		String[] lineSplit = bookString.split("\n");
		/*for(String line : lineSplit)
		{
			System.out.println(line);
		}*/
		ArrayList<String> linesToAdd = new ArrayList<String>();

		//Look for paragraph spaces using blank lines
		for(String line : lineSplit)
		{
			//Found a new Paragraph
			if(emptyParagraphs > 1 && !line.equals(""))
			{				
				linesToAdd.add(line);
				String[] lines = linesToAdd.toArray(new String[linesToAdd.size()+1]);
				
				paragraph = new Paragraph(lines);
				//System.out.println("-------------------------");
				parseLine(line, paragraph);
				
				book.addParagraph(paragraph);
				//System.out.println(paragraph.toString());
				
				//Reset the empty paragraph counter and the lines to add list
				emptyParagraphs = 0;
				linesToAdd.clear();
			}
			//Found an empty line
			else if(line.equals(""))
			{
				System.out.println("Found Blank line, adding to " + emptyParagraphs);
				emptyParagraphs++;
			}
			//Found a line within a paragraph
			else
			{
				parseLine(line, paragraph);
				linesToAdd.add(line);
			}
		}

		/*//Split the book into paragraphs
		String[] paragraphs = bookString.split("\r\n{2}");
		
		//Add each Paragraph to the Book
		for(String paragraph : paragraphs)
		{
			book.addParagraph(new Paragraph(paragraph));
		}*/
	}
	
	/**
	 * Takes a given String array pulls necessary information from it
	 * 
	 * @param line	The array of words representing the line
	 * @param para	The paragraph the line belongs too
	 */
	private void parseLine(String lineToParse, Paragraph paragraph)
	{
		String[] line = lineToParse.split(" ");
		
		//First word of the line
		switch(line[0].toLowerCase())
		{
		/* Book Details */	
		case "title":
			{	
				String newTitle = "";
				for(int i = 1; i<line.length; i++)
				{
					newTitle += line[i] + " ";
				}
				book.setTitle(newTitle);
				break;
			}
			
			case "author":
			{
				String newAuthor = "";
				for(int i = 1; i<line.length; i++)
				{
					newAuthor += line[i] + " ";
				}
				book.setAuthor(newAuthor);
				break;
			}
			/* Paragraph Details */
			case "volume":
			{
				paragraph.setVolume(line[1]);
				break;
			}
			
			case "chapter":
			{
				paragraph.setChapter(line[1]);
				break;
			}
		}
	}
}
