/**
 * Command Prompt based application allowing a user to search through a predefined Corpus of Jane Austin books.
 * The user can search for specific words to see a list of all KWIC instances and expand any results to see more information
 * about that result including the full paragraph, the containing book, chapter and if applicable, volume
 * 
 * @author Anthony Wall
 *
 */
public class ConcordanceViewer 
{
	
	private static Kwic KWIC;;

	public static void main(String[] args) 
	{
		SET_UP();
		
		new TUI(KWIC);

	}
	
	private static void SET_UP()
	{
		String FilePathEmma = "src\\emmaEd11.txt";
		String FilePathMansfield = "src\\mansfieldParkEd10.txt";
		String FilePathPAndP = "src\\pandpEd12.txt";
		
		TextReader textReader = new TextReader();	
		Corpus corpus = new Corpus(textReader.readFile(FilePathEmma));
		
		corpus.addBook(textReader.readFile(FilePathMansfield));
		corpus.addBook(textReader.readFile(FilePathPAndP));
		
		KWIC = new Kwic(corpus);
	}

}
