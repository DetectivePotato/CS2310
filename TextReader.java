import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

//"D:\\Documents\\Data Structures\\Concordence Viewer Coursework\\data\\emmaEd11.txt"

public class TextReader
{
	private String filePath;
	private Book book;
	private int emptyParagraphs = 0;
	
	public TextReader(String filePath)
	{
		this.filePath = filePath;
		book = new Book();
	}

	/**
	 * Read a provided file and serialise key data for later processing
	 */
	public void ReadFile()
	{
		//Define the Buffered Reader
		BufferedReader br = null;
		
		//Initialise the first Paragraph
		Paragraph para = new Paragraph();
		
		//Read through each line of the file
		try
		{
			//Define a String ready to store each line
			String currentLine;
			
			//Initialise the Buffered Reader with the desired file
			br = new BufferedReader(new FileReader(filePath));
			
			//Read through each line of the file
			while((currentLine = br.readLine()) != null)
			{			
				//Split the line into each word
				String[] lineSplit = currentLine.split(" ");

				if(emptyParagraphs >= 1 && !lineSplit[0].equals(""))
				{
					para = new Paragraph();
					book.addParagraph(para);
					emptyParagraphs = 0;
				}
				else if(!lineSplit[0].equals(""))
				{
					emptyParagraphs++;
				}
				
				//Either get info from the line or store it in a new Line object
				parseLine(lineSplit, para);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{			
				//Console test
				System.out.println(book.toString());
				
				//Close the BufferedReader for garbage collection
				if (br != null)
					br.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}

	}
	
	
	/**
	 * Takes a given String array pulls necessary information from it or passes it to the constructor of a new Line object
	 * @param line	The array of words representing the line
	 * @param para	The paragraph the line belongs too
	 */
	private void parseLine(String[] line, Paragraph para)
	{
		//First word of the line
		switch(line[0])
		{
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
			
			case "VOLUME":
				para.addVolume(line[1]);
				break;
				
			case "CHAPTER":
				para.addChapter(line[1]);
				break;
				
			//Create a new Line object
			default:
				Line newLine = new Line(line);
				para.addLine(newLine);
		}
	}
}
