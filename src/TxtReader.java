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
			normalizedWord = normalizedWord.replace(":", "");
			normalizedWord = normalizedWord.replace("/", "");
			normalizedWord = normalizedWord.replace("+", "");
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
	
	//Reads a filename and determines the right category for this file. In this case F or M.
	public String getCategory(File file) {
		String filename = file.getName();
		if (filename.startsWith("F")) {
			return "F";
		} else {
			return "M";
		}
		
	}
	
	
	//Reads all .txt files from a given directory and returns the text in an array and the classification F or M
	public void importAll(String directoryPath) {
		
		File dir = new File(directoryPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
		  for (File child : directoryListing) {
			  try {
				ArrayList normalizedArrayList = normalize(readTxt(child.getAbsolutePath()));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
				//Geef M of F mee
				String category = getCategory(child);
				
				//Zet array en category in een tupel
				//TODO methode train aanroepen(array, category)			    	
		    	
		    }
		  } else {
		    System.out.println("Error in importAll function, given path might not be a directory");
		  }
	}
	
	
	
	public static void main(String[] args) {
		TxtReader tr = new TxtReader();
		/*try {
			ArrayList myList = tr.readTxt("C:/Development/eclipse/workspace/interactive_learner/blogs/F/F-test1.txt");
			System.out.println(tr.normalize(myList).toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		  File dir = new File("C:/Development/eclipse/workspace/interactive_learner/blogs/F");
		  File[] directoryListing = dir.listFiles();
		  if (directoryListing != null) {
		    for (File child : directoryListing) {
		    	try {
		    		System.out.println(child.getName());
					ArrayList myList = tr.readTxt(child.getAbsolutePath());
					//System.out.println(tr.normalize(myList).toString());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		  } else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		  }
	}
	
}