import java.util.ArrayList;

/**
 * Line represents a String containing several words in order. 
 * Each word is cleaned of punctuation or special characters and added to a separate list for searching
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
	 * Returns an array of all words in the Line
	 */
	public String[] getWords()
	{
		return words.toArray(new String[words.size()]);
	}
	
	/**
	 * Removes all punctuation and special characters from a word
	 */
	private String cleanWord(String target)
	{
		return target.replaceAll("[^a-zA-Z ]", "");
	}
	
	/**
	* Returns the size of the line.
	*/
	public int size(){
		return words.size();
	}
	
	/**
	* Returns index int of the given string
	*/
	public int indexOf(String target){
		return words.indexOf(target);
	}
	
	/**
	* Returns the String at the given index in the line
	*/
	public String get(int index){
		return words.get(index);
	}
}
