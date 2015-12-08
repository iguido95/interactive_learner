import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * the collection of all categories from the training set and their dependent data
 * (eg. vocabulary of whole document, document count etc)
 * @author Guido
 *
 */
public class Categories {
	
	
	Vocabulary totalVocabulary = new Vocabulary();
	ArrayList<Category> categories = new ArrayList<Category>();
	ArrayList<Tuple<String, ArrayList<String>>> trainingData;
	
	
	/**
	 * Adds the training data from a directory path
	 * adds all the documents that are in that directory
	 * The files should all be of the form CATEGORYNAME-restFileName.txt
	 * The categoryname must be unique.
	 * 
	 */
	public void addTrainingData(String pathName) {
		ArrayList<Tuple<String, ArrayList<String>>> trainingData = (new TxtReader()).importAll(pathName);

		for (Tuple<String, ArrayList<String>> tuple : trainingData) {
			String categoryName = tuple.x;
			ArrayList<String> tokens = tuple.y;
			Category category = this.getCategory(categoryName);
			category.addDocument(tokens);
			this.totalVocabulary.addWords(tokens);
		}
	}
	
	/**
	 * predicts a category based on the already given training data
	 * returns a class 
	 * !Training data has to be added already
	 * @param testDocument - the document to be tested. Give full path name. 
	 * @return predictedClass 
	 */
	public Category predictCategory(ArrayList<String> testDocument) {
		double highestProb = Double.NEGATIVE_INFINITY;
		Category maxCategory = null;
		for (Category cat : this.categories) {
			double prob = cat.getProbability(testDocument, this.getTotalDocumentCount(), this.totalVocabulary);
			//System.out.println("Category: " + cat.name());
			//System.out.println(prob + " > " + highestProb + " = " + (prob > highestProb));
			if (prob > highestProb) {
				highestProb = prob;
				maxCategory = cat;
			}
		}
		return maxCategory;
	}
	
	/**
	 * 
	 */
	public Category predictCategoryByFile(String testFilePath) throws FileNotFoundException {
		ArrayList<String> testDoc = null;
		testDoc = TxtReader.normalize(TxtReader.readTxt(testFilePath));
		return predictCategory(testDoc);
	}
	
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
	
	
	
	
}
