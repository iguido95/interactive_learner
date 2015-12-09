package main;
import java.util.ArrayList;

/**
 * Een categorie in een Bayesian network. "eg. Male, Female"
 * @author Guido
 *
 */
public class Category {
	//Name of this category eg. Male or Female
	private String name;
	//The vocabulary of this category. All the distinct words and their occurences.
	private Vocabulary vocabulary;
	//The number of documents given to this category to train
	private int number_of_documents;
	
	Category (String name) {
		this.name = name;
		this.vocabulary = new Vocabulary();
		this.number_of_documents = 0;
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
	
	/**
	 * TESTING METHOD
	 * Loops through all the words/tokens in the test-document and calculates
	 * the conditional probability. 
	 * P(X=[all words xi]| C = this_class)
	 * @return log10 of the probalility
	 */
	public double getProbability(ArrayList<String> tokens, int countTotalDocuments, Vocabulary totalTrainVocabulary) {
		double probability = 0;
		// P(C= this_class) = (Number of documents with this_class) / (Number of total documents)
		//The first part of the multinomial formula; The probability of this class.
		probability = (double)this.number_of_documents() / (double)countTotalDocuments;
		probability = Math.log10(probability);
		
/**		System.out.println("-----------");
		System.out.println("no of documents category: " + this.number_of_documents());
		System.out.println("total documents: " + countTotalDocuments);
		System.out.println("* prob of C= " + this.name() + " " + String.valueOf(probability) + "\n-----------"); 
**/
		
		// the second part of the multinomial formula; The probability of a word given this class. Gesommeerd.
		for (String token : tokens) {
			probability += this.getConditionalProbability(token, totalTrainVocabulary);
		}
		return probability;
	}
	
	/**
	 * TESTING METHOD
	 * Calculates the probability of this class, given one word
	 * 
	 * @param given_word
	 * @param totalTrainVocabulary
	 * @return log10 of the conditional probability
	 */
	public double getConditionalProbability(String given_word, Vocabulary totalTrainVocabulary) { 
		//TODO hardcoded k=1!
//		System.out.println("*" + given_word + " " + this.vocabulary.countByWord(given_word));
		int k = 1;
		double teller = (this.vocabulary.countByWord(given_word) + k);
		double noemer = (this.vocabulary.countDistinctWords() + (k*totalTrainVocabulary.countDistinctWords()));
		double conditionalProb = Math.log(teller / noemer);
//		System.out.println("teller: " + teller);
//		System.out.println("noemer: " + noemer);
//		System.out.println("totaal: " + conditionalProb);

		return conditionalProb;
	}

	/**
	 * TRAINING METHOD
	 * Adds the tokenized document to the category
	 * @param tokens
	 */
	public void addDocument(ArrayList<String> tokens) {
		for (String token : tokens) {
			this.addWord(token);
		}
		this.number_of_documents++;
	}
	
	/**
	 * TRAINING METHOD
	 * Adds a word from a new train document to this category
	 * @param word
	 */
	public void addWord(String word) {
		this.vocabulary.addWord(word);
	}
	
}
