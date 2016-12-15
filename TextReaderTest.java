import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextReaderTest 
{
	private TextReader reader;
	private final String FILE_PATH = "src\\emmaEd11.txt";
	private Book emmaEd11;
	
	@Before
	public void setUp() throws Exception 
	{
		reader = new TextReader();
	}

	@Test
	public void testReadFile() 
	{
		emmaEd11 = reader.readFile(FILE_PATH);
		
		String testString = emmaEd11.toString();
		
		assertNotEquals(testString.isEmpty(),testString);
		assertEquals("Emma", emmaEd11.getTitle().trim());
		
		System.out.println(emmaEd11.toString());
	}

}
