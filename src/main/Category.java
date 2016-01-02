package main;
import java.util.ArrayList;

/**
 * Een categorie in een Bayesian network. "eg. Male, Female"
 * @author Guido
 *
 */
public class Category {
	
	public static int k_smoothingFactor = 1;
	
	//Name of this category eg. Male or Female
	private String name;
	//The vocabulary of this category. All the distinct words and their occurences.
	public Vocabulary vocabulary;
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
	 * @return log10 of the probability
	 */
	public double getProbability(ArrayList<String> tokens, int countTotalDocuments, Vocabulary totalTrainVocabulary) {
		double firstTerm = 0;
		// P(C= this_class) = (Number of documents with this_class) / (Number of total documents)
		//The first part of the multinomial formula; The probability of this class.
		firstTerm = (double)this.number_of_documents() / (double)countTotalDocuments;
		firstTerm = Math.log10(firstTerm) / Math.log(2.0);
		
//		System.out.println("-----------");
//		System.out.println("no of documents category: " + this.number_of_documents());
//		System.out.println("total documents: " + countTotalDocuments);
//		System.out.println("* prob of C= " + this.name() + " " + String.valueOf(firstTerm) + "\n-----------"); 

		
		// the second part of the multinomial formula; The probability of a word given this class. Gesommeerd.
		int totalWords = this.vocabulary.countTotalWords();
		double secondTerm = 0.0;
		for (String token : tokens) {
			secondTerm += this.getConditionalProbability(token, totalTrainVocabulary, totalWords);
		}
		return firstTerm + secondTerm;
	}
	
	/**
	 * TESTING METHOD
	 * Calculates the probability of this class, given one word
	 * 
	 * @param given_word
	 * @param totalTrainVocabulary
	 * @return log10 of the conditional probability
	 */
	public double getConditionalProbability(String given_word, Vocabulary totalTrainVocabulary, int totalWords) { 
//		System.out.println("*" + given_word + " " + this.vocabulary.countByWord(given_word));
		int k = k_smoothingFactor;
		double teller = (this.vocabulary.countByWord(given_word) + k);
		double noemer = (totalWords + (k*totalTrainVocabulary.countDistinctWords()));
		double conditionalProb = Math.log10((teller / noemer)) / Math.log10(2.0);

//		System.out.println("(" + this.vocabulary.countByWord(given_word) + " + " + k + ") / (" + this.vocabulary.countTotalWords() + " + " + (k*totalTrainVocabulary.countDistinctWords()) + ")"  );
//		System.out.println("Word :" + given_word + " - totaal: " + conditionalProb + " (teller: " + teller + " | noemer: " + noemer + ")");

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
	
	public String toString() {
		return this.name;
	}
	
}
