import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LineTest 
{
	private String testText;
	private Line testLine;
	
	@Before
	public void setUp() throws Exception 
	{
		testText = "This is a line, it includes punctuation!";
		testLine = new Line(testText);
	}

	@Test
	public void testContains() 
	{
		assertTrue(testLine.contains("punctuation"));
		assertTrue(!testLine.contains("bee"));
	}
	
	@Test
	public void testToString()
	{
		assertEquals(testLine.toString(),"This is a line, it includes punctuation!");
	}

}
