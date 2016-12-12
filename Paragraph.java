import java.util.ArrayList;

/**
 * Paragraph represents a collection of Line objects
 * 
 * @author Anthony Wall
 */
public class Paragraph 
{
	private ArrayList<Line> lines;
	private String volume;
	private String chapter;
	
	/**
	 * Construct an empty Paragraph
	 */
	public Paragraph()
	{
		lines = new ArrayList<Line>();
		volume = "";
		chapter = "";
	}
	/**
	 * Construct a Paragraph from a given String Array
	 * 
	 * @param paragraphStrings The Array to create the Paragarph from
	 */
	public Paragraph(String[] lineStrings)
	{
		this();
		
		for(String line : lineStrings)
		{
			if(line != null && !line.equals(""))
				addLine(line);
		}
		
	}
	
	/**
	 * Construct a Paragraph filled with Line objects
	 * 
	 * @param paragraphString a string representing the Line objects to store in the Paragraph
	 */
	public Paragraph(String paragraphString)
	{
		this();
		
		String[] lines = paragraphString.split("\n");
		
		//Add each line to the Paragraph
		for(String line : lines)
		{
			if(!line.equals(""))
				addLine(line);
		}
	}
	
	/**
	 * Search all Line objects in the Paragraph for a given word
	 * 
	 * @param target The word to look for
	 * @return An Array of all lines containing the target word
	 */
	public ArrayList<Line> contains(String target)
	{
		ArrayList<Line> targetLines = new ArrayList<Line>();
		
		//Check if each line contains the target word
		for(Line line : lines)
		{
			if(line.contains(target))
			{
				targetLines.add(line);
			}
		}
		
		return targetLines;
	}
	
	/**
	 * Return a String representation of the Paragraph
	 */
	public String toString()
	{
		StringBuilder paragraphString = new StringBuilder();
		
		//Get each Line and add it to the StringBuilder,
		//if it is not the final Line also append a line break
		for(int i=0; i<lines.size(); i++)
		{
			paragraphString.append(lines.get(i).toString());
			
			if(i != lines.size()-1)
				paragraphString.append("\n");
		}
		
		return paragraphString.toString();
	}
	
	/**
	 * Set the volume this Paragraph is contained in
	 * 
	 * @param newVolume the volume this Paragraph is in
	 */
	public void setVolume(String newVolume)
	{
		volume = newVolume;
	}
	
	/**
	 * Set the chapter this Paragraph is contained in
	 * 
	 * @param newChapter the chapter this Paragraph is in
	 */
	public void setChapter(String newChapter)
	{
		chapter = newChapter;
	}
	
	/**
	 * Return the current volume
	 * @return The String of the volume this Paragraph is contained in
	 */
	public String getVolume()
	{
		return volume;
	}
	
	/**
	 * Return the current chapter
	 * @return The String of the chapter this Paragraph is contained in
	 */
	public String getChapter()
	{
		return chapter;
	}
	
	/**
	 * Convert a String into a Line object and add it to the Paragraph 
	 * 
	 * @param line the Line to add
	 */
	private void addLine(String line)
	{
		lines.add(new Line(line));
	}
	
	/**
	 * concordance takes int corpus the number of words either side of the word that is passed into the method.
	 * @param context the number of words either side of
	 * @param word the target word
	 * @return ArrayList ArrayList containing all strings of target word with n words either side
	 * @author James Johnson
	 */
	public ArrayList concordance(int context, String word )
	{
		Line previousLine;
		Line nextLine;
		Line currentLine;
		int lineSize;
		int wordIndex;
		ArrayList<Integer> numberOfLines = new ArrayList<Integer>();
		ArrayList<String> contextStrings = new ArrayList<String>();
		for(Line l	:	lines)
			{
				if(l.contains(word))
				{
					lineSize = l.size();
					wordIndex = l.indexOf(word);
					if(lines.indexOf(l) == 0){
						previousLine = null;
						nextLine = lines.get(1);
						numberOfLines.add(1);
						contextStrings.add(contextString(l, nextLine, previousLine, context, word));
					}
					else if(lines.indexOf(l) == lines.size()-1){
						previousLine = lines.get(lines.size()-1);
						nextLine = null;
						numberOfLines.add(2);
						contextStrings.add(contextString(l, nextLine, previousLine, context, word));
					}
					else{
						previousLine = lines.get(lines.indexOf(l)-1);
						nextLine = lines.get(lines.indexOf(l)+1);
						numberOfLines.add(3);
						contextStrings.add(contextString(l, nextLine, previousLine, context, word));
					}
				}
			}
		return contextStrings;
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
	private String contextString(Line currentLine, Line nextLine, Line previousLine, int context, String word)
	{
		Line cLine = currentLine;
		Line nLine = nextLine;
		Line pLine = previousLine;
		
		
		int count = context;
		String[] concordance = new String[(context*2)+1];
		concordance[context] = word;
		
		int indexOfWord = cLine.indexOf(word);
		
		String lineString;
		
		for(int i=0; i<context; i++){
			int leftIndex = context-(1+i);
			int rightIndex = context+(1+i);
			
			if(indexOfWord-(i+1)<0 && indexOfWord+(i+1)>= cLine.size()){
				if(pLine == null && nLine == null){
					concordance[leftIndex] = null;
					concordance[rightIndex] = null;
					
				}
				else if(pLine == null){
					concordance[leftIndex] = null;
					concordance[rightIndex] = nLine.get(rightIndex-cLine.size());
				}
				else if(nLine == null){
					int previousLineSize = pLine.size();
					concordance[leftIndex] = pLine.get(previousLineSize + (indexOfWord-(1+i)));
					concordance[rightIndex] = null;
				}
				else{
					int previousLineSize = pLine.size();
					concordance[leftIndex] = pLine.get(previousLineSize + (indexOfWord-(1+i)));
					concordance[rightIndex] = nLine.get((indexOfWord+(1+i))-cLine.size());
				}
			}
			else if(indexOfWord-(i+1)<0){
				if(pLine == null){
					concordance[leftIndex] = null;
					concordance[rightIndex] = cLine.get(indexOfWord+(1+i));
 				}
				else{
					int previousLineSize = pLine.size();
					concordance[leftIndex] = pLine.get(previousLineSize + (indexOfWord-(1+i)));
					concordance[rightIndex] = cLine.get(indexOfWord+(1+i));
				}
			}
			else if(indexOfWord+(i+1) >= cLine.size()){
				if(nLine == null){
					concordance[leftIndex] = cLine.get(indexOfWord-(1+i));
					concordance[rightIndex] = null;
				}
				else{
					concordance[leftIndex] = cLine.get(indexOfWord-(1+i));
					concordance[rightIndex] = nLine.get((indexOfWord+1+i)-cLine.size());
				}
			}
			else{
				concordance[leftIndex] = cLine.get(indexOfWord-(1+i));
				concordance[rightIndex] = cLine.get(indexOfWord+(1+i));
			}
		}
		return contextStringBuilder(concordance);
	}
	
	/**
	 * Builds line from Strings in array
	 * @param array Array containing target word and n words either side
	 * @return String string of words in array.
	 * @author James Johnson
	 */
	private String contextStringBuilder(String[] array){
		StringBuilder sb = new StringBuilder();
		for(String text : array){
			if(text == null){
				sb.append("\t" + " ");
			}
			else{
				sb.append(text + " ");
				}
		}
		return sb.toString();
	}
}
