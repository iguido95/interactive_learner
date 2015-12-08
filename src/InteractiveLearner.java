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
			String userInput = TUI.getTestFilePath();
			if (userInput.equals(TUI.EXIT)) {
				break;
			}
			
			Category predictedCategory = null;
			try {
				predictedCategory = categories.predictCategoryByFile(userInput);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
			
			TUI.printPredictedCategory(predictedCategory);
			
		}


		
		
	}
	
}
