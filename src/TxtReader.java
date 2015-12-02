import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TxtReader {
	
	TxtReader() {		
	}
	
	public ArrayList readTxt(String filepath) throws FileNotFoundException {
		Scanner s = new Scanner(new File(filepath));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()){	
		    list.add(s.next());
		}
		s.close();
		return list;
	}
	
	//Replaces uppercase letters with lowercase and removes punctuation.
	public ArrayList normalize(ArrayList<String> list) {
		ArrayList<String> normalizedList = new ArrayList<String>();
		
		for(String word : list) {
			String normalizedWord = word.toLowerCase();
			normalizedWord = normalizedWord.replace(",", "");
			normalizedWord = normalizedWord.replace(".", "");
			normalizedWord = normalizedWord.replace(";", "");
			normalizedWord = normalizedWord.replace("!", "");
			normalizedWord = normalizedWord.replace("?", "");
			normalizedWord = normalizedWord.replace("(", "");
			normalizedWord = normalizedWord.replace(")", "");
			normalizedWord = normalizedWord.replace("{", "");
			normalizedWord = normalizedWord.replace("}", "");
			normalizedWord = normalizedWord.replace("[", "");
			normalizedWord = normalizedWord.replace("]", "");
			normalizedWord = normalizedWord.replace("<", "");
			normalizedWord = normalizedWord.replace(">", "");
			normalizedWord = normalizedWord.replace("%", "");
			normalizedWord = normalizedWord.replace(" ", "");
			normalizedWord = normalizedWord.replace("'", "");
			normalizedWord = normalizedWord.replace("-", "");
			
			if (!normalizedWord.equals("")) {
				normalizedList.add(normalizedWord);
			}
		}
		
		return normalizedList;
	}
	
	
	
	public static void main(String[] args) {
		TxtReader tr = new TxtReader();
		try {
			//System.out.println(tr.readTxt("C:/Development/eclipse/workspace/interactive_learner/blogs/F/F-test1.txt").toString());
			ArrayList myList = tr.readTxt("C:/Development/eclipse/workspace/interactive_learner/blogs/F/F-test1.txt");
			System.out.println(tr.normalize(myList).toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}