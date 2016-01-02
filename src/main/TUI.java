package main;
import java.util.Scanner;


public class TUI {
	
	public TUI() {	
	}
	
	public static final String EXIT = "EXIT";
	public static final String CATEGORY_LIST = "CATEGORY_LIST";
	public static final String YES = "YES";
	public static final String NO = "NO";
	
	public static boolean wantsToSetParameters() {
		boolean wantsToSet = false;
		System.out.println("Do you want to set a different value for the k-smooting or the critial value of the Chi-Squared feature selection?");
		System.out.println(YES + " or " + NO + "?");
		
		Scanner s = new Scanner(System.in);
		String userInput = s.next();
		if (userInput.equals(YES)) {
			wantsToSet = true;
		}
		
		return wantsToSet;
	}
	
	public static double newCritialChiValue() {
		double chiValue = 0.0;
		
		boolean isValid = false;
		while(!isValid) {
			System.out.println("Set the value for the critial Chi Value: \n(Real number of 0.0 or higher. For reference: 10.83 has significance of 0.001)");

			Scanner s = new Scanner(System.in);
			String userInput = s.next();
			
			chiValue = Double.parseDouble(userInput);
			isValid = chiValue >= 0;

		}
		
		return chiValue;
	}
	
	public static int newKsmoothingValue() {
		int kValue = 0;
		
		boolean isValid = false;	
		while(!isValid) {
			System.out.println("Set the value k for the smoothing: \n(Whole number of 1 and higher)");

			Scanner s = new Scanner(System.in);
			String userInput = s.next();
			
			kValue = Integer.parseInt(userInput);
			isValid = kValue > 0;
		}
		
		return kValue;
	}
	
	public static String getTrainingPath() {
		System.out.println("Please provide path to trainingset directory:");
		Scanner s = new Scanner(System.in);
		String trainingPath = s.next();
		System.out.println("You have provided " + trainingPath + " as training set directory");
		System.out.println("Please wait. The AI agent is learning ...");
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
	
	public static String getIntendedCategoryName(Categories categories) {
		Scanner s = new Scanner(System.in);
		String userInput = "";
		
		String categoryName = "";
		while(categoryName.length() == 0) {
			System.out.println("What do you think this category should have been?\nType your category name or " 
					+ CATEGORY_LIST + " to see all available categories, or " + NO + " if you don't want to retrain the classifier.");
			userInput = s.next();
			
			if (userInput.equals(NO)) {
				System.out.println("That's alright, I will not add this to my training data.");
				break;
			} else if (userInput.equals(CATEGORY_LIST)) {
				System.out.println("Available categories:");
				System.out.println(categories.toString());
			} else if (categories.isValidCategoryName(userInput)) {
				categoryName = userInput;
			} else {
				System.out.println("This is not an existing category or command! Try again.");
			}
		}
		
		
		return userInput;
	}
	
	public static void printPredictedCategory(Category category){
		System.out.println("\n-------------------------------------");
		System.out.println("The predicted category is: " + category.name());
		System.out.println("-------------------------------------\n");
	}

}
 