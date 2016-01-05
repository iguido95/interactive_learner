package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChiSquared {
	
	public static double critialChiValue = 0.0;	
	
	/**
	 * Feature selection method
	 * Rewrites the vocabularies of the categories and the totalVocabulary
	 * to only include words with higher than given chi2 value. The chi2 value
	 * denotes independence. This means this word is not average and therefore 
	 * should be a better indicator for this class than other words  
	 * @param categories
	 * @param totalVocabulary
	 */
	public static void calculateChiSquareValue(ArrayList<Category> categories, Vocabulary totalVocabulary) {
		int totalVocabularyWordCount = totalVocabulary.countTotalWords();
		for(Category c : categories) {		
			Vocabulary newVocabulary = new Vocabulary();
			System.out.println("*** Category " + c.name() + " ***");
			System.out.println("Current vocabulary: " + c.vocabulary.countDistinctWords());
			
			int totalCategoryWordCount = c.vocabulary.countTotalWords();
			
			for(Map.Entry<String, Integer> word : c.vocabulary.vocabulary.entrySet()) {
				String w = word.getKey();
				double chiValue = ChiSquared.getChiSquare(c, w, totalVocabulary, totalVocabularyWordCount, totalCategoryWordCount);
				
//				System.out.print(" | Chi Value for " + w + ": " + chiValue);
				//When the word has a high chi-value (eg. is independent), add it to the new vocabulary
				if (chiValue > critialChiValue) {
					newVocabulary.addWordWithCount(w, word.getValue());
				}
				
			}
			//Set the new Vocabulary
			c.vocabulary = newVocabulary;
			System.out.println("New vocabulary: " + c.vocabulary.countDistinctWords());
					
		}

			
	}
	
	/**
	 * Helper method.
	 * @param category - the category to be examined 
	 * @param word - the word to be examined 
	 * @param totalVocabulary 
	 * @param totalVocabularyWordCount
	 * @param totalCategoryWordCount
	 * @return Chi2 value of the given for for the given category
	 */
	public static double getChiSquare(Category category, String word, Vocabulary totalVocabulary, int totalVocabularyWordCount, int totalCategoryWordCount) {
		double N11 = category.vocabulary.countByWord(word);
		double N12 = totalCategoryWordCount - N11;
		double rijsom1 = N11 + N12;
		
		double N21 = totalVocabulary.countByWord(word) - N11;
		double N22 = (totalVocabularyWordCount - totalVocabulary.countByWord(word)) - N12;
		
		System.out.println("*" + word);
//		System.out.println("N11: " + N11);
//		System.out.println("N12: " + N12);
//		System.out.println("N21: " + N21);
//		System.out.println("N22: " + N22);
		
		
		double rijsom2 = N21 + N22;
		double kolomsom1 = N11 + N21;
		double kolomsom2 = N12 + N22;
		
//		System.out.println("Rijsom1: " + rijsom1);
//		System.out.println("Rijsom2: " + rijsom2);
//		System.out.println("Kolomsom1: " + kolomsom1);
//		System.out.println("Kolomsom2: " + kolomsom2);
		
		
		double n = rijsom1 + rijsom2;
//		System.out.println("n: " + n + " total words: " + totalVocabulary.countTotalWords());
		
		double E11 = rijsom1 * kolomsom1 / n;
		double E12 = rijsom1 * kolomsom2 / n;
		double E21 = rijsom2 * kolomsom1 / n;
		double E22 = rijsom2 * kolomsom2 / n;
		
		double chiValue = Math.pow((N11 - E11), 2.0) / E11;
		chiValue += Math.pow((N12 - E12), 2.0) / E12;
		chiValue += Math.pow((N21 - E21), 2.0) / E21;
		chiValue += Math.pow((N22 - E22), 2.0) / E22;
		
		System.out.println("Chi Value: " + chiValue);
		
		return chiValue;
	}

}
