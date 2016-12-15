import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KwicTest {

	private Book book;
	private Corpus corpus;
	private Kwic kwic;
	private TextReader reader;
	private final String FILE_PATH_EMMA = "src\\emmaEd11.txt";
	private final String FILE_PATH_PANDP = "src\\pandpEd12.txt";

	@Before
	public void setUp() throws Exception {
		reader = new TextReader();
		book = reader.readFile(FILE_PATH_EMMA);
		corpus = new Corpus(book);
		corpus.addBook(reader.readFile(FILE_PATH_PANDP));
		kwic = new Kwic(corpus);
	}

	@Test
	public void test() {
		System.out.print(kwic.kwicSearch(5, "affectionate"));
		
		assertEquals(26, kwic.size());
		
		System.out.print("\n\n" + kwic.getResultsDetails("" + 14));	
		kwic.reset();
		
		assertEquals(0, kwic.size());
	}

}
