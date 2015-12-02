import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtReader {

	TxtReader() {
	}

	public ArrayList readTxt(String filepath) throws FileNotFoundException {
		Scanner s = new Scanner(new File(filepath));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()) {
			list.add(s.next());
		}
		s.close();
		return list;
	}

	// Replaces uppercase letters with lowercase and removes punctuation.
	public ArrayList normalize(ArrayList<String> list) {
		ArrayList<String> normalizedList = new ArrayList<String>();

		for (String word : list) {
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

	// Reads a filename and splits it at the "-" sign. The word before the "-"
	// sign is used as the categoryName
	public String getCategoryName(File file) {
		String filename = file.getName();
		String categoryName = filename.split("-")[0];
		return categoryName;
	}

	// Reads all .txt files from a given directory and returns the text in an
	// array and the classification category
	/**
	 * Reads all the files in a given directory to output a tokenized and normalized format
	 * Training file should be of format: "CLASSNAME-rest.txt"
	 * @param directoryPath - directory where all the training files are
	 * @return Arraylist of tuples containing a String of the category name and a arraylist of all the tokens
	 */
	public ArrayList<Tuple<String, ArrayList<String>>> importAll(String directoryPath) {
		// Voorbeeld: Tuple<"F", [hoi, ik, ben, een, blogger]>
		ArrayList<Tuple<String, ArrayList<String>>> listAll = new ArrayList<Tuple<String, ArrayList<String>>>();

		File dir = new File(directoryPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				try {
					ArrayList<String> normalizedArrayList = normalize(readTxt(child.getAbsolutePath()));

					// Vraag bijbehorende category op
					String category = getCategoryName(child);

					// Zet array en category in een tupel
					// TODO methode train aanroepen(array, category)
					listAll.add(new Tuple<String, ArrayList<String>>(category, normalizedArrayList));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Error in importAll function, given path might not be a directory");
		}
		return listAll;
	}
	
	public static void main(String[] args) {
		TxtReader tr = new TxtReader();
		try {
			System.out.println(tr.normalize(tr.readTxt("C:/Development/eclipse/workspace/interactive_learner/blogs/M/M-test3.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}