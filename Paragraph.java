import java.util.ArrayList;

public class Paragraph 
{
	private ArrayList<Line> lines;
	private String chapter;
	private String volume;
	
	public Paragraph()
	{
		lines = new ArrayList<Line>();
	}
	
	public void addLine(Line line)
	{
		lines.add(line);
	}
	
	public void addChapter(String newChapter)
	{
		chapter = newChapter;
	}
	
	public void addVolume(String newVolume)
	{
		volume = newVolume;
	}
	
	public boolean isEmpty()
	{
		if (lines.size() == 0 || lines.get(0).isEmpty())
			return true;
		else
			return false;
	}
	
	public int size()
	{
		return lines.size();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for(Line line : lines)
		{
			sb.append(line.toString());
			sb.append(" ");
		}
		
		sb.append("\n");
		return sb.toString();
	}
}
