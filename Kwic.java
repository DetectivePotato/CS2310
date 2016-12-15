/**
 * KWIC provides the functionality for searching through a corpus for a target word and displaying all results as well as being able to
 * "zoom" into a result to see more details about it.
 */
import java.util.HashMap;
import java.util.Map;

public class Kwic {
	private Corpus corpus;
	private Map<String, Paragraph> contextStrings;
	
	/**
	 * Constructs a Kwic object with a Corpus object for later searching
	 * @param corpus
	 */
	public Kwic(Corpus corpus){
		this.corpus = corpus;
		contextStrings = new HashMap<String, Paragraph>();
	}
	
	/**
	 * Searches through the Corpus looking for the target word
	 * a default context size of 10 is used
	 * 
	 * @param word the target word to search for in the Corpus
	 */
	public String kwicSearch(String word)
	{
		return kwicSearch(10, word);
	}
	
	/**
	 * kwicSearch looks through the Corpus for a target word with a given context sze.
	 * @param context the number of words either side of the target word
	 * @param word the target word to search for
	 * 
	 * @author James Johnson
	 */	
	public String kwicSearch(int context, String word )
	{
		Line previousLine = null;
		Line nextLine = null;
		Line currentLine;
		int lineSize;
		int wordIndex;
		
		for(int index = 0; index < corpus.size(); index++)
			{
				Book book = corpus.getBook(index);
						//paragraph.getLine(index);
			
				for(int i = 0; i<book.paragraphSize(); i++)
				{
					Paragraph paragraph = book.getParagraph(i);
					for(int x = 0; x<paragraph.size(); x++)
					{
						Line line = paragraph.getLine(x);
						
						if(line.contains(word))
						{
							lineSize = line.size();
							wordIndex = line.indexOf(word);
							if(paragraph.indexOf(line) == 0){
								previousLine = null;
								if(paragraph.size() > 1)
								{
									nextLine = paragraph.getLine(1);
								}
							}
							else if(paragraph.indexOf(line) == paragraph.size()-1){
								previousLine = paragraph.getLine(paragraph.size()-2);
								nextLine = null;
							}
							else{
								if(paragraph.size() > 2)
								{
									previousLine = paragraph.getLine(paragraph.indexOf(line)-1);
									nextLine = paragraph.getLine(paragraph.indexOf(line)+1);								
								}
							}						
							String contextString = contextFinder(line, nextLine, previousLine, context, word);
							contextStrings.put(contextString, paragraph);
						}
					}
				}
			}
		String [] searchResults = contextStrings.keySet().toArray(new String[contextStrings.size()]);
				//toArray(new String[contextStrings.size()]);
		
		if(searchResults.length > 0)
			return resultsToString(searchResults);
		else
			return ("Word: " + word + " not found!");
	}
	
	/**
	 * called by concordance to get the words and put in them in an array
	 * calls another method to turn array to String.
	 * @param currentLine line containing target word
	 * @param nextLine next line after current line
	 * @param previousLine line before current line
	 * @param context n words left and right of target word
	 * @param word target word you want to find
	 * @return String string of target word and n words either side in order of how they appear
	 * @author James Johnson
	 */
	private String contextFinder(Line currentLine, Line nextLine, Line previousLine, int context, String word)
	{
		String[] results = new String[(context*2) + 1];
		results[context] = word;
		
		int indexOfWord = currentLine.indexOf(word);
		
		//Get the required number of words either side of the target word
		for(int i = 0; i<context; i++)
		{
			int rightIndex = ((indexOfWord + 1) + i);
			int leftIndex = ((indexOfWord - 1) - i);
			
			String leftWord = "";
			String rightWord = "";
			
			/* Right Hand Side */			
			//Overflow
			if(rightIndex >= currentLine.size())
			{				
				if(nextLine != null)
				{
					int remainingIndex = rightIndex-currentLine.size();				
					if(remainingIndex<nextLine.size())
						rightWord = nextLine.get(remainingIndex);
				}
			}
			//Within range
			else if(rightIndex < currentLine.size())
			{
				rightWord = currentLine.get(rightIndex);
			}	
			
			/* Left Hand Side */			
			if(leftIndex < 0)
			{
				
				if(previousLine != null)
				{
					int remainingIndex = previousLine.size() + leftIndex;		
					leftWord = previousLine.get(remainingIndex);
				}
					}
			//Within range
			else if(leftIndex >= 0)
			{
				leftWord = currentLine.get(leftIndex);
			}
			
			//Empty check before adding words to the results
			if(!leftWord.isEmpty())
				results[(context - 1) - i] = leftWord;
			if(!rightWord.isEmpty())
				results[(context + 1) + i] = rightWord;
		}
		
		return contextStringBuilder(results);
	}
	
	/**
	 * Builds line from Strings in array
	 * @param contextStrings Array containing target word and n words either side
	 * @return String string of words in array.
	 * @author James Johnson
	 */
	private String contextStringBuilder(String[] contextStrings){
		StringBuilder sb = new StringBuilder();
		for(String text : contextStrings)
		{			
			if(text == null){
				sb.append("\t" + " ");
			}
			else{
				sb.append(text + " ");
				}
		}
		return sb.toString();
	}
	
	/**
	 * Given an index, return more detail about a search result
	 * @param kwicId which searchResult to expand
	 */
	public String getResultsDetails(String kwicId)
	{
		Paragraph[] resultsParagraphs = contextStrings.values().toArray(new Paragraph[contextStrings.size()]);
		StringBuilder sb = new StringBuilder();		
		Paragraph resultsParagraph = resultsParagraphs[Integer.parseInt(kwicId) - 1];

		if(!resultsParagraph.getVolume().isEmpty())
			sb.append("Volume: " + resultsParagraph.getVolume() + "\t");
		
		if(!resultsParagraph.getChapter().isEmpty())
			sb.append("Chapter: " + resultsParagraph.getChapter() + "\n");
		
		sb.append(resultsParagraph.toString());
		return sb.toString();
	}
	
	/**
	 * Return a String representation of the searchResults
	 * 
	 * @param searchResults the results of a given Kwic search
	 */
	public String resultsToString(String[] searchResults)
	{
		int lineNumber = 1;
		StringBuilder sb = new StringBuilder();
		
		sb.append("Search Results" + "\n---------------------\n");		
		for(String searchResult : searchResults)
		{
			sb.append(lineNumber + ": " + searchResult + "\n");
			lineNumber++;
		}
		
		return sb.toString();
	}
	
	/**
	 * Returns the number of results currently stored in this Kwic
	 */
	public int size()
	{
		return contextStrings.size();
	}
	
	/**
	 * Reset this Kwic by clearing the stored Map
	 */
	public void reset()
	{
		contextStrings.clear();
	}
}
