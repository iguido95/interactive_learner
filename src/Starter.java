import java.util.ArrayList;
import java.util.Arrays;


public class Starter {
	
	
	public static void main(String[] args) {
		Vocabulary totalVocabulary = new Vocabulary();
		int k = 1;
		ArrayList<Category> categories = new ArrayList<Category>();
		
		Category catFemale = new Category("female");
		Category catMale = new Category("male");
		
		categories.add(catFemale);
		categories.add(catMale);
		
		ArrayList<String> train_doc1_male = new ArrayList<>(Arrays.asList("car", "blue", "bmw"));
		ArrayList<String> train_doc2_male = new ArrayList<>(Arrays.asList("flower", "red", "bmw", "blue"));
		ArrayList<String> train_doc1_female = new ArrayList<>(Arrays.asList("rose", "flower", "flower"));
		
		catMale.addDocument(train_doc1_male);
		catMale.addDocument(train_doc2_male);
		catFemale.addDocument(train_doc1_female);
		
		totalVocabulary.addWords(train_doc1_male);
		totalVocabulary.addWords(train_doc2_male);
		totalVocabulary.addWords(train_doc1_female);
		
		System.out.println("Total Vocabulary count:" + totalVocabulary.countDistinctWords() + " \n");
		
		System.out.println("Category name: " + catMale.name());
		System.out.println("No of documents: " + catMale.number_of_documents());
		System.out.println("No of total words: " + catMale.number_of_total_words());
		
		
		ArrayList<String> test_doc1_male = new ArrayList<>(Arrays.asList("ict", "flower", "not"));
		double prob_male = catMale.getProbability(test_doc1_male, 3, totalVocabulary); //TODO hardcoded total document count
		double prob_female = catFemale.getProbability(test_doc1_male, 3, totalVocabulary); //TODO hardcoded total document count
		System.out.println("################");
		System.out.println(prob_male);
		System.out.println(prob_female);
		if (prob_male > prob_female) {
			System.out.println("Predicted class: Male (" + prob_male + ")");
		} else {
			System.out.println("Predicted class: Female (" + prob_female + ")");
		}
		
		
		
	}
	
}
