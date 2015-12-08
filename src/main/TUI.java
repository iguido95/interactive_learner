package main;
import java.util.Scanner;


public class TUI {
	
	public TUI() {	
	}
	
	public static final String EXIT = "EXIT";
	
	public static String getTrainingPath() {
		System.out.println("Please provide path to trainingset directory:");
		Scanner s = new Scanner(System.in);
		String trainingPath = s.next();
		System.out.println("You have provided " + trainingPath + " as training set directory");
		System.out.println("Please wait. The AI is learning ...");
		return trainingPath;
	}
	
	public static String getTestFilePath() {
		System.out.println("\nPlease provide path to test file or '" + EXIT + "' if you want to stop: ");
		Scanner s = new Scanner(System.in);
		String testFilePath = s.next();
		
		if (testFilePath.equals(EXIT)) {
			System.out.println("Goodbye :'(");
		} else {
			System.out.println("You have provided " + testFilePath + " as test file path");
		}
		return testFilePath;
	}
	
	public static void printPredictedCategory(Category category){
		System.out.println("\n-------------------------------------");
		System.out.println("The predicted category is: " + category.name());
		System.out.println("-------------------------------------\n");
	}

}
 