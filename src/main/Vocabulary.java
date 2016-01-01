package main;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * THe collection of words in a training set or a category
 * @author Guido
 *
 */
public class Vocabulary {
	//private ArrayList<Word> vocabulary = new ArrayList<Word>();

	public HashMap<String, Integer> vocabulary = new HashMap<String, Integer>();


	/**
	 * @return the number of distinct words in this vocabulary
	 */
	public int countDistinctWords() {
		return vocabulary.size();
	}

	/**
	 * Also keeps in mind the count per word
	 * @return the number of all words in this vocabulary
	 */
	public int countTotalWords() {
		int counter = 0;
		for (String word : this.vocabulary.keySet()) {
			counter += this.vocabulary.get(word);
		}
		return counter;
	}

	/**
	 * Searches the word in the vocabulary and returns its count
	 * @param given_word
	 * @return
	 */
	public int countByWord(String givenWord) {
		int counter = 0;
		if (this.vocabulary.get(givenWord) != null) {
			counter = this.vocabulary.get(givenWord);
		}
		return counter;
	}

	/**
	 * Adds a given word, from the normalized and tokenized document,
	 * to this word vocabulary. If already exists in the vocabulary it
	 * increments the count of this word with 1.
	 * @param given_word - A normalized and tokenized string from the training txt file
	 */
	public void addWord(String givenWord) {
			if (this.vocabulary.get(givenWord) != null) {
				int currentCount = this.vocabulary.get(givenWord);
				this.vocabulary.put(givenWord, currentCount + 1);
			} else {
				this.vocabulary.put(givenWord, 1);
			}
	}
	
	/**
	 * Adds a given word, from the normalized and tokenized document,
	 * to this word vocabulary. If already exists in the vocabulary it
	 * increments the count of this word with the given count.
	 * @param given_word - A normalized and tokenized string from the training txt file
	 */
	public void addWordWithCount(String givenWord, int count) {
		if (this.vocabulary.get(givenWord) != null) {
			int currentCount = this.vocabulary.get(givenWord);
			this.vocabulary.put(givenWord, currentCount + count);
		} else {
			this.vocabulary.put(givenWord, count);
		}	
	}

	/**
	 * Adds multiple words and their count to this vocabulary
	 * @param givenWords
	 */
	public void addWords(ArrayList<String> givenWords) {
		for (String word : givenWords) {
			this.addWord(word);
		}
	}
	
	public String toString() {
		String output = "";
		for (String word : this.vocabulary.keySet()) {
			output += (word + ", ");
		}
		
		return output;
	}
	
	//Experimenteel gedeelte
	
//	public void featureSelection(double percentage) {
//		int count = 0;
//		int totalwords = this.countTotalWords();
//		System.out.println("Amount of words before feature selection: " + totalwords);
//		double limit = percentage*totalwords;
//		HashMap<String, Integer> copy = new HashMap<String, Integer>();
//		copy.putAll(this.vocabulary); //Zet alle mappings van vocabulary in copy
//		
//		for (String word : this.vocabulary.keySet()) {
//			if (this.vocabulary.get(word) < limit) {
//				copy.remove(word);
//				count ++;
//				//System.out.println(word + " REMOVED");
//			} 
//		}
//		this.vocabulary.clear(); //Maakt de orginele vocabulary leeg
//		this.vocabulary.putAll(copy); //Zet alle mappings van copy zonder verwijderde mappings terug in vocabulary
//		System.out.println(count + " words are removed from the vocabulary");
//		System.out.println("Amount of words after feature selection: " + this.countTotalWords());
//		System.out.println("Feature selection completed!");
//	}


}
