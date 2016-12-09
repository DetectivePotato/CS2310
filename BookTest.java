import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BookTest 
{
	private Book testBook;
	private String title = "Emma";
	private String author = "Jane Austin";
	
	private String paragraphText1 = "Emma Woodhouse, handsome, clever, and rich, with a comfortable home"	+
									"\nand happy disposition, seemed to unite some of the best blessings" 	+
									"\nof existence; and had lived nearly twenty-one years in the world" 	+
									"\nwith very little to distress or vex her.";
	
	private String paragraphText2 =	"Poor Miss Taylor!--I wish she were here again.  What a pity it"		+
									"\nis that Mr. Weston ever thought of her!";
	
	private String paragraphText3 = "A house of her own!--But where is the advantage of a house of her own?"+
									"\nThis is three times as large.--And you have never any odd humours,"	+
									"\nmy dear.";
	
	@Before
	public void setUp()
	{
		testBook = new Book();
		testBook.setAuthor(author);
		testBook.setTitle(title);
	}
	
	@Test
	public void testAddParagraph() 
	{
		assertEquals(0, testBook.paragraphSize());
		testBook.addParagraph(new Paragraph(paragraphText1));
		
		assertEquals(1, testBook.paragraphSize());
		testBook.addParagraph(new Paragraph(paragraphText2));
		testBook.addParagraph(new Paragraph(paragraphText3));
		
		assertEquals(3, testBook.paragraphSize());
	}
	
	@Test
	public void testContains() {
		String target = "her";
		
		testBook.addParagraph(new Paragraph(paragraphText1));
		testBook.addParagraph(new Paragraph(paragraphText2));
		testBook.addParagraph(new Paragraph(paragraphText3));
		
		Line[] searchResults = testBook.contains(target);
		
		assertEquals(3, searchResults.length);
		
	}

	@Test
	public void testToString() {
		testBook.addParagraph(new Paragraph(paragraphText1));
		
		assertEquals("Title: " + title + "\nAuthor: " + author + "\n\n" + paragraphText1, testBook.toString());
		
		testBook.addParagraph(new Paragraph(paragraphText2));
		
		assertEquals("Title: " + title + "\nAuthor: " + author + "\n\n" + paragraphText1 +"\n\n" + paragraphText2, testBook.toString());
		
		System.out.println(testBook.toString());
	}

}
