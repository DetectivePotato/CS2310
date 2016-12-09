import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextReaderTest 
{
	private TextReader reader;
	private final String FILE_PATH = "H:\\My Documents\\CS2310\\Labs\\CS2310_Coursework\\src\\emmaEd11.txt";
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
		
		emmaEd11.toString();
	}

}
