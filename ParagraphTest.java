import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ParagraphTest 
{
	private Paragraph testParagraph;
	private String testText = "Emma Woodhouse, handsome, clever, and rich, with a comfortable home"
			+ "\nand happy disposition, seemed to unite some of the best blessings"
			+ "\nof existence; and had lived nearly twenty-one years in the world"
			+ "\nwith very little to distress or vex her.";
	
	private Line testLine1;
	private Line testLine2;
	private Line testLine3;
	private Line testLine4;
	private Line[] testArray;
	
	@Before
	public void setUp() throws Exception 
	{
		testParagraph = new Paragraph(testText);
	}

	@Test
	public void testContains() 
	{
		ArrayList<Line> searchResults = new ArrayList<Line>();
		System.out.println(testParagraph.toString() + "--------------");
		
		searchResults = testParagraph.contains("and");
		for(Line line: searchResults)
		{
			System.out.println(line.toString());
		}
		
		assertEquals(3, searchResults.size());
	}

	@Test
	public void testToString() 
	{
		assertEquals(testText,testParagraph.toString());
	}

}
