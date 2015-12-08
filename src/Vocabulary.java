import java.util.ArrayList;

/**
 * THe collection of words in a training set or a category
 * @author Guido
 *
 */
public class Vocabulary {
	private ArrayList<Word> vocabulary = new ArrayList<Word>();
	
	
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
		for (Word w : this.vocabulary) {
			counter += w.count();
		}
		return counter;
	}
	
	/**
	 * Searches the word in the vocabulary and returns its count
	 * @param given_word
	 * @return
	 */
	public int countByWord(String given_word) {
		int counter = 0;
		Word word = searchWord(given_word);
		if (word != null) {
			counter = word.count();
		}
			
		return counter;
	}
	
	/**
	 * Adds a given word, from the normalized and tokenized document,
	 * to this word vocabulary. If already exists in the vocabulary it
	 * increments the count of this word with 1.
	 * @param given_word - A normalized and tokenized string from the training txt file
	 */
	public void addWord(String given_word) {
		boolean found = false;
		for (Word w : this.vocabulary) {
			if (w.name().equals(given_word)) {
				w.incrementCount();
				found = true; //TODO stop for loop when found
				if (found) {
					break;
				}
			}
		}
		
		if (!found) {
			Word w = new Word(given_word);
			this.vocabulary.add(w);
		}
	}
	
	public void addWords(ArrayList<String> given_words) {
		for (String w : given_words) {
			this.addWord(w);
		}
	}
	
	private Word searchWord(String searchName) {
		Word resultWord = null;
		for (Word word : vocabulary) {
			if (word.name().equals(searchName)) {
				resultWord = word;
			}
		}
		return resultWord;
	}
	
	
}
