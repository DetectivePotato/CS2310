import java.util.ArrayList;

public class Book 
{
	private String title;
	private String author;
	private ArrayList<Paragraph> paragraphs;
	
	public Book()
	{
		title = "";
		author = "";
		paragraphs = new ArrayList<Paragraph>();
	}
	
	public Book(String title, String author)
	{
		this.title = title;
		this.author = author;
		paragraphs = new ArrayList<Paragraph>();
	}
	
	public void setTitle(String newTitle)
	{
		title = newTitle;
	}
	
	public void setAuthor(String newAuthor)
	{
		author = newAuthor;
	}
	
	public void addParagraph(Paragraph para)
	{
			paragraphs.add(para);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Title: " + title + "\n");
		sb.append("Author:" + author + "\n");
		
		for(Paragraph para : paragraphs)
		{
			sb.append(para.toString());
			sb.append("\n");
		}
		
		sb.append("\n" + "No. Paragraphs: " + paragraphs.size());
		return sb.toString();
	}
}
