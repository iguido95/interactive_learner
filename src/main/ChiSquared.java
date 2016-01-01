package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChiSquared {
	
	public static double critialChiValue = 0.5;
	
	
//	public static double calculateChiSquareValue(ArrayList<Category> categories, Vocabulary totalVocabulary) {
//		
//		double chiSquaredValue = 0.0;
//		int totalAll = totalVocabulary.countTotalWords();
//		
//		for(Map.Entry<String, Integer> word : totalVocabulary.vocabulary.entrySet()) {
//			String w = word.getKey();
//			
//			System.out.println("*** " + w);
//			
//			double kolomsom = 0.0;
//			for(Category c : categories) {
//				kolomsom += c.vocabulary.countByWord(w);
//			}
//			
//			for(Category c : categories) {
//				double rijsom = c.number_of_total_words();
//				double observedCount = c.vocabulary.countByWord(w);
//				double expectedCount = rijsom * kolomsom;
//				System.out.println("Chi-squared for " + c.name() + ": " + (Math.pow((observedCount - expectedCount), 2) / expectedCount));
//				chiSquaredValue += Math.pow((observedCount - expectedCount), 2) / expectedCount;
//				
//			}				
//					
//		}
//
//		return 	chiSquaredValue;	
//	}
	
	
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
				//When the word has a high chi-value (is independant), add it to the new vocabulary
				if (chiValue > critialChiValue) {
					newVocabulary.addWordWithCount(w, word.getValue());
				}
				
			}
			//Set the new Vocabulary
			c.vocabulary = newVocabulary;
			System.out.println("New vocabulary: " + c.vocabulary.countDistinctWords());
					
		}

			
	}
	
	public static double getChiSquare(Category category, String word, Vocabulary totalVocabulary, int totalVocabularyWordCount, int totalCategoryWordCount) {
		double N11 = category.vocabulary.countByWord(word);
		double N12 = totalCategoryWordCount - N11;
		double rijsom1 = N11 + N12;
		
		double N21 = totalVocabulary.countByWord(word) - N11;
		double N22 = (totalVocabularyWordCount - totalVocabulary.countByWord(word)) - N12;
		
//		System.out.println(word);
//		System.out.println(N11);
//		System.out.println(N12);
//		System.out.println(N21);
//		System.out.println(N22);
		
		
		double rijsom2 = N21 + N22;
		double kolomsom1 = N11 + N21;
		double kolomsom2 = N12 + N22;
		
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
		
		return chiValue;
	}

}
