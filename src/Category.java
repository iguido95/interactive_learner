import java.util.ArrayList;

/**
 * Een categorie in een Bayesian network. "eg. Male, Female"
 * @author Guido
 *
 */
public class Category {
	private String name;
	private Vocabulary vocabulary;
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
	 * P(X=[all words]|C= this_class)
	 * @return
	 */
	public double getProbability(ArrayList<String> tokens, int countTotalDocuments, Vocabulary totalTrainVocabulary) {
		//TODO totalDocuments als argument??? 
		//TODO totalTrainVocabulary als argument ???
		
		double probability = 0;
		//P(C= this_class) = (Number of documents with this_class) / (Number of total documents) 
		probability = (double)this.number_of_documents() / (double)countTotalDocuments; 
		System.out.println("-----------");
		System.out.println("no of documents category: " + this.number_of_documents());
		System.out.println("total documents: " + countTotalDocuments);
		System.out.println("* prob of C= " + this.name() + String.valueOf(probability) + "\n-----------");
		for (String token : tokens) {
			probability *= this.getConditionalProbability(token, totalTrainVocabulary);
		}
		return probability;
	}
	
	public double getConditionalProbability(String given_word, Vocabulary totalTrainVocabulary) { 
		//TODO hardcoded k=1!
		System.out.println("*" + given_word + " " + this.vocabulary.countByWord(given_word));
		int k = 1;
		double teller = (this.vocabulary.countByWord(given_word) + k);
		double noemer = (this.vocabulary.countDistinctWords() + (k*totalTrainVocabulary.countDistinctWords()));
		double conditionalProb = teller / noemer;
		System.out.println("teller: " + teller);
		System.out.println("noemer: " + noemer);
		System.out.println("totaal: " + conditionalProb);
		return conditionalProb;
	}

	
	public void addDocument(ArrayList<String> tokens) {
		for (String token : tokens) {
			this.addWord(token);
		}
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
