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
						contextStrings.add(contextFinder(l, nextLine, previousLine, context, word));
					}
					else if(paragraph.indexOf(l) == paragraph.size()-1){
						previousLine = paragraph.getLine(paragraph.size()-1);
						nextLine = null;
						numberOfLines.add(2);
						contextStrings.add(contextFinder(l, nextLine, previousLine, context, word));
					}
					else{
						previousLine = paragraph.getLine(paragraph.indexOf(l)-1);
						nextLine = paragraph.getLine(paragraph.indexOf(l)+1);
						numberOfLines.add(3);
						contextStrings.add(contextFinder(l, nextLine, previousLine, context, word));
					}
				}
			}
		return contextStrings;
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
				sb.append("\t");
			}
			else{
				sb.append(text + " ");
				}
		}
	return sb.toString();
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
	
	public String contextFinder(Line currentLine, Line nextLine, Line previousLine, int context, String target){
		Line cLine = currentLine;
		 int indexLeft;
		 int indexRight;
		 Line pLine = previousLine;
		 Line nLine = nextLine;
		 int previousIndex;
		 int nextIndex;
		 if(pLine == null && nLine == null){
			 previousIndex = -1;
			 nextIndex = -1;
		 }
		 else if(pLine == null){
			 previousIndex = -1;
			 nextIndex = paragraph.indexOf(nLine);
		 }
		 else if(nLine == null){
			 previousIndex = paragraph.indexOf(pLine);
			 nextIndex = -1;
		 }
		 else{
			 previousIndex = paragraph.indexOf(pLine);
			 nextIndex = paragraph.indexOf(nLine);
		 }
		 
		 int pLinesSize = cLine.size();
		 int nLinesSize = cLine.size();
		 int targetIndex = cLine.indexOf(target);
		 String[] contextString = new String[(context*2)+1];
		 contextString[context] = target;
		 for(int i = 0; i<context ; i++){
			 indexLeft = context-(i+1);
			 indexRight = context+(i+1);
			 int leftIndex = context-(1+i);
				int rightIndex = context+(1+i);
				
				if(targetIndex-(i+1)<0 && targetIndex+(i+1)>= cLine.size()){
					if(previousLine == null && nextLine == null){
						contextString[leftIndex] = null;
						contextString[rightIndex] = null;
						
					}
					else if(previousLine == null){
						contextString[leftIndex] = null;
						if(rightIndex - nLinesSize > nextLine.size()){
							if(nextIndex != paragraph.size()-1){
								nLinesSize = nLinesSize + nextLine.size();
								nextIndex++;
								nextLine = paragraph.getLine(nextIndex);
							}
						}
						contextString[rightIndex] = nextLine.get(rightIndex-nLinesSize);
					}
					else if(nextLine == null){
						if(pLinesSize + (targetIndex-(1+i)) < 0){
							if(previousIndex != 0){
								previousIndex--;
								pLinesSize = pLinesSize + previousLine.size();
								previousLine = paragraph.getLine(previousIndex);
							}
						}
						contextString[leftIndex] = previousLine.get(pLinesSize + (targetIndex-(1+i)));
						contextString[rightIndex] = null;
					}
					else{
						if(pLinesSize + (targetIndex-(1+i)) < 0){
							if(previousIndex != 0){
								previousIndex--;
								pLinesSize = pLinesSize + previousLine.size();
								previousLine = paragraph.getLine(previousIndex);
							}
						}
						contextString[leftIndex] = previousLine.get(pLinesSize + (targetIndex-(1+i)));
						if(rightIndex - nLinesSize > nextLine.size()){
							if(nextIndex != paragraph.size()-1){
								nLinesSize = nLinesSize + nextLine.size();
								nextIndex++;
								nextLine = paragraph.getLine(nextIndex);
							}
						}
						contextString[rightIndex] = nextLine.get((targetIndex+(1+i))- nLinesSize);
					}
				}
				//right index needs to go onto a line before the line containing the target word
				else if(targetIndex-(i+1)<0){
					//if null can't do anything so always equals null
					if(previousLine == null){
						contextString[leftIndex] = null;
						contextString[rightIndex] = cLine.get(targetIndex+(1+i));
	 				}
					//else can go onto another line
					else{
						if(pLinesSize + (targetIndex-(1+i)) < 0){
							if(previousIndex != 0){
								previousIndex--;
								pLinesSize = pLinesSize + previousLine.size();
								previousLine = paragraph.getLine(previousIndex);
							}
						}
						contextString[leftIndex] = previousLine.get(pLinesSize + (targetIndex-(1+i)));
						contextString[rightIndex] = cLine.get(targetIndex+(1+i));
					}
				}
				//if context is greater than number of words to right of target  word 
				// must go onto another line
				else if(targetIndex+(i+1) >= cLine.size()){
					//if next line is null then value must be null
					if(nextLine == null){
						contextString[leftIndex] = cLine.get(targetIndex-(1+i));
						contextString[rightIndex] = null;
					}
					// else go onto another line and continue getting words
					else{
						contextString[leftIndex] = cLine.get(targetIndex-(1+i));
						if(rightIndex - nLinesSize > nextLine.size()){
							if(nextIndex != paragraph.size()-1){
								nLinesSize = nLinesSize + nextLine.size();
								nextIndex++;
								nextLine = paragraph.getLine(nextIndex);
							}
						}
						contextString[rightIndex] = nextLine.get((targetIndex+1+i)-nLinesSize);
					}
				}
				else{
					contextString[leftIndex] = cLine.get(targetIndex-(1+i));
					contextString[rightIndex] = cLine.get(targetIndex+(1+i));
				}
			}	
		return contextStringBuilder(contextString);
	}
}
