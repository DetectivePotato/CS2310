import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextReaderTest 
{
	private TextReader reader;
	
	@Before
	public void setUp() throws Exception 
	{
		reader = new TextReader("D:\\Documents\\Data Structures\\Concordence Viewer Coursework\\data\\emmaEd11.txt");
	}

	@Test
	public void testReadFile() 
	{
		reader.ReadFile();
	}

}
