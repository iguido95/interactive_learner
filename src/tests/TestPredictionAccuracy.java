package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import main.Categories;
import main.Category;
import main.TxtReader;

import org.junit.Test;

import exceptions.NoDirectoryException;

public class TestPredictionAccuracy {
	Categories categories = new Categories();
	
	TestPredictionAccuracy(String trainingDirectory) throws NoDirectoryException {
		categories.addTrainingData(trainingDirectory);
	}

	// categories.predictCategoryByFile("");
	// categories.addTrainingData("blogs/BOTH");

	public double testPrediction(String testFileDirectory)
			throws FileNotFoundException {
		File dir = new File(testFileDirectory);
		File[] directoryListing = dir.listFiles();

		int totalFileCount = directoryListing.length;
		int positivePredictedCount = 0;

		for (File file : directoryListing) {
			if (file.getName().equals(".DS_Store")) {
				continue;
			}
			TxtReader.getCategoryName(file);
			Category predictedCategory = categories.predictCategoryByFile(file.getAbsolutePath());
			String correctCategoryName = TxtReader.getCategoryName(file);

			//System.out.println("Predicted: " + predictedCategory.name() + " [correct: " + correctCategoryName + "] - " + "(" + file.getName() + ")");
			if (predictedCategory.name().equals(correctCategoryName)) {
				positivePredictedCount++;
			}

		}
		
		double accuracy = (double) positivePredictedCount / (double) totalFileCount;
		return accuracy;
	}

	public static void main(String[] argv) throws NoDirectoryException, FileNotFoundException {
		DecimalFormat percentage = new DecimalFormat("#0.0%");
		
		TestPredictionAccuracy test = new TestPredictionAccuracy("blogs/TRAINING");		
		
		System.out.println("Accuracy for Female: " + percentage.format(test.testPrediction("blogs/F")));
		System.out.println("Accuracy for Male: " + percentage.format(test.testPrediction("blogs/M")));
		System.out.println("Accuracy for both: " + percentage.format(test.testPrediction("blogs/FM")));


	}

}
