import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


public class InteractiveLearner {
	
	/**
	 * Just start this without any configuration
	 * @param args
	 */
	public static void main(String[] args) {
		
		Categories categories = new Categories();
		//Roep TUI aan, om path name op te vragen voor training data.
		categories.addTrainingData(TUI.getTrainingPath());
		
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
