import java.util.Scanner;


public class TUI {
	
	public TUI() {	
	}
	
	public static String getTrainingPath() {
		System.out.println("Please provide path to trainingset directory:");
		Scanner s = new Scanner(System.in);
		String trainingPath = s.next();
		System.out.println("You have provided " + trainingPath + " as trainingset directory");
		return trainingPath;
	}
	
	public static String getTestFilePath() {
		System.out.println("Please provide path to test file:");
		Scanner s = new Scanner(System.in);
		String testFilePath = s.next();
		System.out.println("You have provided " + testFilePath + " as test file path");
		return testFilePath;
	}
	
	public void getPredictedClass(Category category){
		System.out.println("The predicted category is: " + category.name());
	}

}
 