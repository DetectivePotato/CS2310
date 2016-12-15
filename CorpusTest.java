import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CorpusTest 
{
	private Corpus corpus;
	private Book emma;
	private Book pandp;
	private TextReader reader;
	@Before
	public void setUp() throws Exception 
	{
		corpus = new Corpus();
		reader = new TextReader();
		
		emma = reader.readFile("src\\emmaEd11.txt");
		pandp = reader.readFile("src\\pandpEd12.txt");
	}

	@Test
	public void testAddBook() {
		corpus.addBook(emma);
		assertEquals(1, corpus.size());
		
		corpus.addBook(pandp);
		assertEquals(2, corpus.size());
	}

	@Test
	public void testGetBook() 
	{
		corpus.addBook(emma);
		corpus.addBook(pandp);
		
		
		assertEquals("Emma", corpus.getBook(0).getTitle().trim());
		assertEquals("Pride and Prejudice", corpus.getBook(1).getTitle().trim());
	}

}
