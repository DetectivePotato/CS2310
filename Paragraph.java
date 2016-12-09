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
}
