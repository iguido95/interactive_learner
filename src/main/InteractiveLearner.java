package main;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import exceptions.NoDirectoryException;


public class InteractiveLearner {
	
	/**
	 * Just start this without any configuration
	 * @param args
	 */
	public static void main(String[] args) {
		ChiSquared.critialChiValue = 0.0;
		Category.k_smoothingFactor = 1;
		
		if (TUI.wantsToSetParameters()) {			
			boolean exceptionGiven = true;
			while(exceptionGiven) {
				try {
					ChiSquared.critialChiValue = TUI.newCritialChiValue();
					Category.k_smoothingFactor = TUI.newKsmoothingValue();
					exceptionGiven = false;
				} catch (NumberFormatException e) {
					exceptionGiven = true;
					System.err.println("This is not a correct number! Try again.");
				}
			}
		}
		
		Categories categories = new Categories();
		//Roep TUI aan, om path name op te vragen voor training data.
		boolean isDirectory = false;
		while(!isDirectory) {
			try {
				categories.addTrainingData(TUI.getTrainingPath());
				isDirectory = true;
			} catch (NoDirectoryException e) {
				System.out.println("This is not a directory!");
			}
		}
		
		
		//Loop in TUI om steeds weer een test document toe te voegen
			//Exit als user dat vertelt 
		while (true) {
			String userInputFilePath = TUI.getTestFilePath();
			if (userInputFilePath.equals(TUI.EXIT)) {
				break;
			}
			
			Category predictedCategory = null;
			try {
				predictedCategory = categories.predictCategoryByFile(userInputFilePath);
			} catch (FileNotFoundException e) {
				System.out.println("This is not a file, please enter a correct file path:");
				continue;
			}	
			
			TUI.printPredictedCategory(predictedCategory);
			
			// Retrain the classifier. if TUI.STOP skip this step
			String intendedCategoryName = TUI.getIntendedCategoryName(categories);
			if (!intendedCategoryName.equals(TUI.NO)) {
				categories.addTrainingFile(userInputFilePath, intendedCategoryName);
			}
			
		}


		
		
	}
	
}
