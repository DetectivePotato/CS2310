import java.util.ArrayList;

/**
 * Line represents a String containing several words
 * 
 * @author Anthony Wall
 * @version 08/12/2016
 */
public class Line 
{
	private ArrayList<String> words;
	private String line;
	
	/**
	 * Construct an empty Line and initialising the words List
	 */
	public Line()
	{
		words = new ArrayList<String>();
	}
	
	/**
	 * Construct a Line and fill it with words
	 * 
	 * @param lineArray An array of words contained in the line
	 */
	public Line(String lineString)
	{
		this();
		
		line = lineString;
		
		String[] lineArray = lineString.split(" ");
		
		//Add each word into the words list
		for(String word : lineArray)
		{
			words.add(cleanWord(word));
		}
	}
	
	/**
	 * Check whether a provided word is contained in the Line and return true if it is
	 * 
	 * @param target The word to look for
	 */
	public boolean contains(String target)
	{
		return words.contains(target);
	}
	
	/**
	 * Return the Line in String representation
	 */
	public String toString()
	{		
		return line;
	}
	
	/**
	 * Removes all punctuation and special characters from a word
	 */
	private String cleanWord(String target)
	{
		return target.replaceAll("[^a-zA-Z ]", "");
	}
}
