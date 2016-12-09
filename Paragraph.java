import java.util.ArrayList;

/**
 * Paragraph represents a collection of Line objects
 * 
 * @author Anthony Wall
 */
public class Paragraph 
{
	private ArrayList<Line> lines;
	
	/**
	 * Construct an empty Paragraph
	 */
	public Paragraph()
	{
		lines = new ArrayList<Line>();
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
	 * Convert a String into a Line object and add it to the Paragraph 
	 * 
	 * @param line the Line to add
	 */
	private void addLine(String line)
	{
		lines.add(new Line(line));
	}
}
