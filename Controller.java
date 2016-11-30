/**
 * 
 */

/**
 * A controller for the Concordance Viewer application.
 * 
 * @author S H S Wong
 * @version 28/10/2016 
 *
 */
public interface Controller {
	
	/** 
	 * Returns a concordance for the specified word, assuming a default context size.
	 * 
	 * @param word	the keyword
	 * @return	the KWIC results as a string
	 */
	public String getKWIC(String word);
	
	/** 
	 * Returns a concordance for the specified word. 
	 * The result will be displayed in the KWIC view, with each KWIC result line 
	 * containing the required number of words as specified in the given context size.
	 * 
	 * @param word	the keyword
	 * @param contextSize	the number of words to the left/right of the keywords
	 * @return	the KWIC results as a string
	 */
	public String getKWIC(String word, int contextSize);

	/**
	 * Returns the wider context of the specified KWIC result line.
	 * 
	 * @param kwicID
	 * @return	the wider context of the required KWIC result
	 */
	public String getWiderContext(String kwicID);
	
}
