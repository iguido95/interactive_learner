import java.util.ArrayList;

/**
 * Een categorie in een Bayesian network. "eg. Male, Female"
 * @author Guido
 *
 */
public class Category {
	private String name;
	private Vocabulary vocabulary = new Vocabulary();
	private int number_of_documents;
	
	Category (String name) {
		this.name = name;
	}
	
	
	
	public String name() {
		return name;
	}
	
	public int number_of_documents() {
		return number_of_documents;
	}
	
	public int number_of_total_words() {
		return this.vocabulary.countTotalWords();
	}
	
	public void addDocument(String tODO) {
		//TODO add all the words in this document to this vocabulary
			//for all words
				//add(given_word)
		//Increment document counter
		this.number_of_documents++;
	}
	
	/**
	 * Adds a word from a new train document to this category
	 * @param word
	 */
	public void addWord(String word) {
		this.vocabulary.addWord(word);
	}
	
}
