import java.util.ArrayList;

public class Kwic {
	private ArrayList<Line> lines;
	private Paragraph paragraph;
	
	public Kwic(Paragraph paragraph){
		this.paragraph = paragraph;
	}
	
	/**
	 * concordance takes int corpus the number of words either side of the word that is passed into the method.
	 * @param context the number of words either side of
	 * @param word the target word
	 * @return ArrayList ArrayList containing all strings of target word with n words either side
	 * @author James Johnson
	 */	
	public ArrayList concordance(int context, String word )
	{
		Line previousLine;
		Line nextLine;
		Line currentLine;
		int lineSize;
		int wordIndex;
		ArrayList<Integer> numberOfLines = new ArrayList<Integer>();
		ArrayList<String> contextStrings = new ArrayList<String>();
		for(int index = 0; index < paragraph.size(); index++)
			{
				Line l = paragraph.getLine(index);
			
				if(l.contains(word))
				{
					lineSize = l.size();
					wordIndex = l.indexOf(word);
					if(paragraph.indexOf(l) == 0){
						previousLine = null;
						nextLine = paragraph.getLine(1);
						numberOfLines.add(1);
						contextStrings.add(contextString(l, nextLine, previousLine, context, word));
					}
					else if(paragraph.indexOf(l) == paragraph.size()-1){
						previousLine = paragraph.getLine(paragraph.size()-1);
						nextLine = null;
						numberOfLines.add(2);
						contextStrings.add(contextString(l, nextLine, previousLine, context, word));
					}
					else{
						previousLine = paragraph.getLine(paragraph.indexOf(l)-1);
						nextLine = paragraph.getLine(paragraph.indexOf(l)+1);
						numberOfLines.add(3);
						contextStrings.add(contextString(l, nextLine, previousLine, context, word));
					}
				}
			}
		return contextStrings;
	}
	
	/**
	 * called by concordance to get the words and put in them in an array
	 * calls another method to turn array to String.
	 * @param currentLine line containing target word
	 * @param nextLine next line after current line
	 * @param previousLine line before current line
	 * @param context n words left and right of target word
	 * @param word target word you want to find
	 * @return String string of target word and n words either side in order of how they appear
	 * @author James Johnson
	 */
	private String contextString(Line currentLine, Line nextLine, Line previousLine, int context, String word)
	{
		Line cLine = currentLine;
		Line nLine = nextLine;
		Line pLine = previousLine;
		
		
		int count = context;
		String[] concordance = new String[(context*2)+1];
		concordance[context] = word;
		
		int indexOfWord = cLine.indexOf(word);
		
		String lineString;
		
		for(int i=0; i<context; i++){
			int leftIndex = context-(1+i);
			int rightIndex = context+(1+i);
			
			if(indexOfWord-(i+1)<0 && indexOfWord+(i+1)>= cLine.size()){
				if(pLine == null && nLine == null){
					concordance[leftIndex] = null;
					concordance[rightIndex] = null;
					
				}
				else if(pLine == null){
					concordance[leftIndex] = null;
					concordance[rightIndex] = nLine.get(rightIndex-cLine.size());
				}
				else if(nLine == null){
					int previousLineSize = pLine.size();
					concordance[leftIndex] = pLine.get(previousLineSize + (indexOfWord-(1+i)));
					concordance[rightIndex] = null;
				}
				else{
					int previousLineSize = pLine.size();
					concordance[leftIndex] = pLine.get(previousLineSize + (indexOfWord-(1+i)));
					concordance[rightIndex] = nLine.get((indexOfWord+(1+i))-cLine.size());
				}
			}
			else if(indexOfWord-(i+1)<0){
				if(pLine == null){
					concordance[leftIndex] = null;
					concordance[rightIndex] = cLine.get(indexOfWord+(1+i));
 				}
				else{
					int previousLineSize = pLine.size();
					concordance[leftIndex] = pLine.get(previousLineSize + (indexOfWord-(1+i)));
					concordance[rightIndex] = cLine.get(indexOfWord+(1+i));
				}
			}
			else if(indexOfWord+(i+1) >= cLine.size()){
				if(nLine == null){
					concordance[leftIndex] = cLine.get(indexOfWord-(1+i));
					concordance[rightIndex] = null;
				}
				else{
					concordance[leftIndex] = cLine.get(indexOfWord-(1+i));
					concordance[rightIndex] = nLine.get((indexOfWord+1+i)-cLine.size());
				}
			}
			else{
				concordance[leftIndex] = cLine.get(indexOfWord-(1+i));
				concordance[rightIndex] = cLine.get(indexOfWord+(1+i));
			}
		}
		return contextStringBuilder(concordance);
	}
	
	/**
	 * Builds line from Strings in array
	 * @param array Array containing target word and n words either side
	 * @return String string of words in array.
	 * @author James Johnson
	 */
	private String contextStringBuilder(String[] array){
		StringBuilder sb = new StringBuilder();
		for(String text : array){
			if(text == null){
				sb.append("\t" + " ");
			}
			else{
				sb.append(text + " ");
				}
		}
	return sb.toString();
	}
}
