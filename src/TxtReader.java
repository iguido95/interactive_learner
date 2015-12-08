import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.NoDirectoryException;

public class TxtReader {

	TxtReader() {
	}

	public static ArrayList readTxt(String filepath) throws FileNotFoundException {
		Scanner s = new Scanner(new File(filepath));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()) {
			list.add(s.next());
		}
		s.close();
		return list;
	}

	// Replaces uppercase letters with lowercase and removes punctuation.
	/**
	 * Normalizes each word in an array list with our own rules
	 * Chosen is to only allow letters and numbers. Not other characters
	 * This is because eg. "Hello," and "Hello" would be totally different 
	 * words to the program and should be the same.
	 * @param list
	 * @return
	 */
	public static ArrayList normalize(ArrayList<String> list) {
		ArrayList<String> normalizedList = new ArrayList<String>();

		for (String word : list) {
			String normalizedWord = word.toLowerCase();
			normalizedWord = normalizedWord.replaceAll("[^a-zA-Z0-9]", "");

			if (!normalizedWord.equals("")) {
				normalizedList.add(normalizedWord);
			}
		}
		//System.out.println(normalizedList);
		return normalizedList;
	}

	/**
	 * Reads a filename and splits it at the "-" sign. The word before the "-"
	 * sign is used as the categoryName.
	 * @param file
	 * @return the category as a string
	 */
	// Reads a filename and splits it at the "-" sign. The word before the "-"
	// sign is used as the categoryName
	public static String getCategoryName(File file) {
		String filename = file.getName();
		String categoryName = filename.split("-")[0];
		return categoryName;
	}


	/**
	 * Reads all the files in a given directory to output a tokenized and normalized format and returns the text in an
	 * array and the classification category
	 * Training file should be of format: "CLASSNAME-rest.txt"
	 * @param directoryPath - directory where all the training files are
	 * @return Arraylist of tuples containing a String of the category name and a arraylist of all the tokens
	 * @throws NoDirectoryException 
	 */
	public static ArrayList<Tuple<String, ArrayList<String>>> importAll(String directoryPath) throws NoDirectoryException {
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
					listAll.add(new Tuple<String, ArrayList<String>>(category, normalizedArrayList));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.err.println("Error in importAll function, given path might not be a directory");
			throw new NoDirectoryException();
		}
		return listAll;
	}
	

	

}