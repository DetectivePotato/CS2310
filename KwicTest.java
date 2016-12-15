import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KwicTest {

	private Book book;
	private TextReader reader;
	private final String FILE_PATH = "C:\\CodeRepository\\CS2310\\CS2310-Experimental\\emmaEd11.txt";

	@Before
	public void setUp() throws Exception {
		reader = new TextReader();
		book = reader.readFile(FILE_PATH);
	}

	@Test
	public void test() {
		ArrayList<String> contextStrings = new ArrayList<String>();
		contextStrings.addAll(book.kwic(10, "affectionate"));
		for(String string :contextStrings){
			System.out.println(string);
		}
	}

}
