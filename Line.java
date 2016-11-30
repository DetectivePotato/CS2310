import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Line 
{
	private LinkedList<String> words;
	
	public Line(String[] line)
	{
		words = new LinkedList<String>();
		
		populateLine(line);
	}
	
	private void populateLine(String[] line)
	{	
		for(String word : line)
		{
			addWord(word);
		}
	}
	
	public void addWord(String word)
	{
		words.add(word);
	}
	
	public boolean isEmpty()
	{
		if(words.contains(""))
			return true;
		else
			return false;
	}
	
	public void clear()
	{
		words.clear();
	}

	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for (String word : words)
		{
			sb.append(word);
			sb.append(" ");
		}
		
		return sb.toString();
	}
}
