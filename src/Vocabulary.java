import java.util.ArrayList;
import java.util.HashMap;

/**
 * THe collection of words in a training set or a category
 * @author Guido
 *
 */
public class Vocabulary {
	//private ArrayList<Word> vocabulary = new ArrayList<Word>();

	private HashMap<String, Integer> vocabulary = new HashMap<String, Integer>();


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
	 * Adds multiple words and their count to this vocabulary
	 * @param givenWords
	 */
	public void addWords(ArrayList<String> givenWords) {
		for (String word : givenWords) {
			this.addWord(word);
		}
	}


}
