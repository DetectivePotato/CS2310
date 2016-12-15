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
			
			reader = new BufferedReader(new FileReader(filePath));
			
			ArrayList<String> lineSplit = new ArrayList<String>();
			
			//Read each line and store it for later parsing
			while((currentLine = reader.readLine()) != null)
			{
				lineSplit.add(currentLine);
			}
			//Split the text into paragraphs and add it to the Book
			parseBook(lineSplit.toArray(new String[lineSplit.size()]));
			
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
		
		//Reset the held Book object
		Book tempBook = book;
		book = new Book();
		
		return tempBook;
	}
	
	/**
	 * Convert a String[] representation of the Book into Paragraphs and add them to the Book
	 * 
	 * @param paragraphs The string Book to be converted
	 */
	private void parseBook(String[] lineSplit)
	{
		String currentChapter = "";
		String currentVolume = "";
		String[] parseResults = new String[2];
		
		int emptyParagraphs = 0;
		Paragraph paragraph = new Paragraph();
		
		ArrayList<String> linesToAdd = new ArrayList<String>();

		//Look for paragraph spaces using blank lines
		for(String line : lineSplit)
		{
			//Found a new Paragraph
			if(emptyParagraphs >= 1 && !line.isEmpty())
			{				
				String[] lines = linesToAdd.toArray(new String[linesToAdd.size()]);
				
				//Create a new paragraph with all previous lines
				paragraph = new Paragraph(lines);
				
				if(!currentVolume.isEmpty())
					paragraph.setVolume(currentVolume);
				if(!currentChapter.isEmpty())
					paragraph.setChapter(currentChapter);
				
				book.addParagraph(paragraph);	
				
				//Reset the empty paragraph counter and the lines to add list
				emptyParagraphs = 0;
				linesToAdd.clear();
				
				//Ensure the line that triggered the paragraph change is ready to add to the next Paragraph
				linesToAdd.add(line);
			}
			//Found an empty line
			else if(line.isEmpty())
			{
				//System.out.println("Found Blank line, adding to " + emptyParagraphs);
				emptyParagraphs++;
			}
			//Found a line within a paragraph
			else
			{
				linesToAdd.add(line);
			}
			
			parseResults = parseLine(line);
			
			if(parseResults[0] != null)
			{
				switch(parseResults[0])
				{
				case "V":
					currentVolume = parseResults[1];
					break;
				case "C":
					currentChapter = parseResults[1];
					break;
				}
			}
		}
	}
	
	/**
	 * Takes a given String array pulls necessary information from it
	 * 
	 * @param line	The array of words representing the line
	 * @param para	The paragraph the line belongs too
	 */
	private String[] parseLine(String lineToParse)
	{
		String[] line = lineToParse.split(" ");
		String[] parseResults = new String[2];
		//First word of the line
		switch(line[0])
		{
		/* Book Details */	
		case "Title:":
			{	
				String newTitle = "";
				for(int i = 1; i<line.length; i++)
				{
					newTitle += line[i] + " ";
				}
				book.setTitle(newTitle);
				break;
			}
			
			case "Author:":
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
			case "VOLUME":
			{
				parseResults[0] = "V";
				parseResults[1] = line[1];
				break;
			}
			
			case "CHAPTER":
			{
				parseResults[0] = "C";
				parseResults[1] = line[1];
				break;
			}
		}
		
		return parseResults;
	}
}
