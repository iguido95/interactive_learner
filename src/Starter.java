import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


public class Starter {
	
	
	/*
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
	
		
	} */
	
	Vocabulary totalVocabulary = new Vocabulary();
	ArrayList<Category> categories = new ArrayList<Category>();
	
	/**
	 * Gets category by name. If the category name does not exist yet
	 * it will be added to the collection of categories
	 * @param name
	 * @return 
	 */
	public Category getCategory(String name) {
		Category foundCategory = null;
		boolean found = false;
		for (Category cat : categories) {
			if (cat.name().equals(name)) {
				found = true;
				foundCategory = cat;
			}
		}
		
		if (!found) {
			foundCategory = createNewCategory(name);
		}
		
		return foundCategory;
	}
	
	/**
	 * Creates a new category and adds it to the category collection
	 * Category name should not exist already
	 * @param name - of the new category 
	 */
	private Category createNewCategory(String name) {
		boolean exists = false;
		for (Category cat : categories) {
			if (cat.name().equals(name)) {
				exists = true;
			}
		}
		
		if (!exists) {
			Category newCat = new Category(name);
			categories.add(newCat);
			return newCat;
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @return the total number of documents from all categories
	 */
	public int getTotalDocumentCount() {
		int counter = 0;
		for (Category cat : categories) {
			counter += cat.number_of_documents();
		}
		return counter;
	}
	
	
	
	public static void main(String[] args) {
		Starter starter = new Starter();
		//TRAINING
		ArrayList<Tuple<String, ArrayList<String>>> trainingData = (new TxtReader()).importAll("/Users/Guido/Development/Java/eclipse/workspace/interactive_learner/blogs/SMALLSAMPLE");
		
		//System.out.println(trainingData.get(0).x);
		
		for (Tuple<String, ArrayList<String>> tuple : trainingData) {
			String categoryName = tuple.x;
			ArrayList<String> tokens = tuple.y;
			Category category = starter.getCategory(categoryName);
			category.addDocument(tokens);
			starter.totalVocabulary.addWords(tokens);
		}
		
		
		//TESTEN
		
		//ArrayList<String> test_doc1_male = new ArrayList<>(Arrays.asList("i", "went", "to", "party", "policeman"));
		TxtReader tr = new TxtReader();
		ArrayList<String> test_doc1_male = null; 
		try {
			test_doc1_male= tr.normalize(tr.readTxt("/Users/Guido/Development/Java/eclipse/workspace/interactive_learner/blogs/M/M-test3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double highestProb = 0.0;
		Category highestCategory = null;
		for (Category cat : starter.categories) {
			double prob = cat.getProbability(test_doc1_male, starter.getTotalDocumentCount(), starter.totalVocabulary);
			if (prob > highestProb) {
				highestProb = prob;
				highestCategory = cat;
			}
		}
		
		//RESULT
		System.out.println("Predicted class: " + highestCategory.name() + " (" + highestProb + ")");

		
		
	}
	
}
