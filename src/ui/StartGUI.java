package ui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exceptions.NoDirectoryException;
import main.Categories;
import main.Category;
import main.ChiSquared;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class StartGUI extends JPanel {

	double chiValue = 0.0;
	int kValue = 1;
	String trainDirectoryPath = "";
	Categories categories;
	String[] categoryNames = {};
	
	String testFilePath = "";
	
	String selectedCategoryName = "";
	
	//UI Elements
	JButton trainClassifierButton = new JButton("Train Classifier");
	JButton chooseFileToClassifyButton = new JButton("Choose Txt File...");
	JButton classifyFileButton = new JButton("Classify File");
	final JLabel classyfiedAsLabel = new JLabel();
	JList categoryNamesList = new JList();
	final JTextField customCategoryField = new JTextField(12);
	JButton reclassifyFileButton = new JButton("Re-Classify File");
	

	public StartGUI() {
		super(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel buttonRow = new JPanel();
		// Use default FlowLayout.
		buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.PAGE_AXIS));
		buttonRow.add(createConstantsRow());
		buttonRow.add(createDirectoryChooserRow());
		buttonRow.add(createTrainClassifierRow());
		tabbedPane.addTab("Initialize Classifier", buttonRow);

		JPanel testClassifierTab = new JPanel();
		// Use default FlowLayout.
		testClassifierTab.setLayout(new BoxLayout(testClassifierTab, BoxLayout.PAGE_AXIS));
		testClassifierTab.add(createFileChooserRow());
		testClassifierTab.add(predictCategoryRow());
		testClassifierTab.add(retrainClassifierRow());
		tabbedPane.addTab("Use the Classifier", testClassifierTab);
		

		// Add tabbedPane to this panel.
		add(tabbedPane, BorderLayout.CENTER);
	}

	protected JPanel createConstantsRow() {

		JLabel kValueLabel = new JLabel("K Value (natural >= 1)");
		final JTextField kValueField = new JTextField(5);
		kValueField.setText("" + kValue);
		kValueField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int newValue = Integer.parseInt(kValueField.getText());
					if (newValue >= 1) {
						kValue = newValue;
					} else {
						kValueField.setText("" + kValue);
					}
				} catch (NumberFormatException err) {
					kValueField.setText("" + kValue);
				}
			}
		});

		final JLabel chiValueLabel = new JLabel("Chi Value (real >= 0.0)");
		final JTextField chiValueField = new JTextField(5);
		chiValueField.setText("" + chiValue);
		chiValueField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double newValue = Double.parseDouble(chiValueField
							.getText());
					if (newValue >= 0.0) {
						chiValue = newValue;
					} else {
						chiValueField.setText("" + chiValue);
					}
				} catch (NumberFormatException err) {
					chiValueField.setText("" + chiValue);
				}
			}
		});

		JPanel pane = new JPanel();
		Dimension size = new Dimension(600, 100);
		pane.setMaximumSize(size);
		pane.setPreferredSize(size);
		pane.setMinimumSize(size);
		pane.setBorder(BorderFactory.createTitledBorder("Constants"));
		// pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		pane.add(kValueLabel);
		pane.add(kValueField);
		pane.add(chiValueLabel);
		pane.add(chiValueField);
		return pane;
	}

	protected JPanel createDirectoryChooserRow() {

		// Create a file chooser
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		final JLabel directoryLabel = new JLabel();

		JButton openButton = new JButton("Choose Training Directory...");
		openButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					// This is where a real application would open the file.
					System.out.println(file.getAbsolutePath());
					directoryLabel.setText(file.getAbsolutePath());
					trainDirectoryPath = file.getAbsolutePath();
				} else {
					// log.append("Open command cancelled by user." + newline);
				}

			}

		});

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory
				.createTitledBorder("Training Data Directory"));
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		Dimension size = new Dimension(600, 100);
		pane.setMaximumSize(size);
		pane.setPreferredSize(size);
		pane.setMinimumSize(size);
		pane.add(openButton);
		pane.add(directoryLabel);

		return pane;
	}

	protected JPanel createTrainClassifierRow() {

		
		final JLabel informationLabel = new JLabel();
		trainClassifierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start Classifier!");
				startClassifier(informationLabel);
			}
		});

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createTitledBorder("Train Classifier"));
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		Dimension size = new Dimension(600, 100);
		pane.setMaximumSize(size);
		pane.setPreferredSize(size);
		pane.setMinimumSize(size);
		pane.add(trainClassifierButton);
		pane.add(informationLabel);
		return pane;
	}
	
	protected JPanel createFileChooserRow() {

		// Create a file chooser
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		final JLabel directoryLabel = new JLabel();
		
		chooseFileToClassifyButton.setEnabled(false);
		chooseFileToClassifyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					classyfiedAsLabel.setText("");
					File file = fc.getSelectedFile();
					// This is where a real application would open the file.
					System.out.println(file.getAbsolutePath());
					directoryLabel.setText(file.getAbsolutePath());
					testFilePath = file.getAbsolutePath();
					trainClassifierButton.setEnabled(true);
					categoryNamesList.setEnabled(false);
					customCategoryField.setEnabled(false);
				} else {
					// log.append("Open command cancelled by user." + newline);
				}

			}

		});

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory
				.createTitledBorder("File to classify"));
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		Dimension size = new Dimension(600, 100);
		pane.setMaximumSize(size);
		pane.setPreferredSize(size);
		pane.setMinimumSize(size);
		pane.add(chooseFileToClassifyButton);
		pane.add(directoryLabel);

		return pane;
	}
	
	protected JPanel predictCategoryRow() {
		classifyFileButton.setEnabled(false);
		classifyFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Category predictedCategory = categories.predictCategoryByFile(testFilePath);
					String predictedName = "<html><br><b>Predicted Category:</b><br><h2>" + predictedCategory.name() + "</h2></html>";
					classyfiedAsLabel.setText(predictedName);
					customCategoryField.setEnabled(true);
					categoryNamesList.setEnabled(true);
				} catch (FileNotFoundException e1) {
					customCategoryField.setEnabled(false);
					categoryNamesList.setEnabled(false);
					e1.printStackTrace();
					classyfiedAsLabel.setText("File Not Found!");
				}
			}
		});

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createTitledBorder("Classify File"));
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		Dimension size = new Dimension(600, 150);
		pane.setMaximumSize(size);
		pane.setPreferredSize(size);
		pane.setMinimumSize(size);
		pane.add(classifyFileButton);
		pane.add(classyfiedAsLabel);
		return pane;
	}
	
	
	protected JPanel retrainClassifierRow() {

		//Category Chooser JPanel
		categoryNamesList.setListData(this.categoryNames);
		categoryNamesList.setEnabled(false);
		categoryNamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		categoryNamesList.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent le) {
	        int idx = categoryNamesList.getSelectedIndex();
	        if (idx != -1) {
	        	selectedCategoryName = categoryNames[idx];
	        	System.out.println("Current selection: " + selectedCategoryName);
	        	customCategoryField.setText("");
	        	reclassifyFileButton.setEnabled(true);
	        } else {
	        	reclassifyFileButton.setEnabled(false);
	          //System.out.println("Please choose a language.");
	        }
	      }
	    });	
		
		
		//Custom Category Input Field
		customCategoryField.setEnabled(false);
		JLabel customCategoryLabel = new JLabel("Or New Category:");
		customCategoryField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (customCategoryField.getText().length() > 0) {
					selectedCategoryName = customCategoryField.getText();
					System.out.println("Current selection: " + selectedCategoryName);
					categoryNamesList.clearSelection();
					reclassifyFileButton.setEnabled(true);
				}
			}
		});
		
		//Reclassify Button		
		reclassifyFileButton.setEnabled(false);
		reclassifyFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categories.addTrainingFile(testFilePath, selectedCategoryName);
				System.out.println(testFilePath);
				System.out.println(selectedCategoryName);
				System.out.println("Re-Classify!");
				reclassifyFileButton.setEnabled(false);
				customCategoryField.setText("");
				//Update the category name list
				categoryNames = categories.getNames();
				categoryNamesList.setListData(categoryNames);
			}
		});

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createTitledBorder("Retrain The Classifier, select the desired category:"));
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		Dimension size = new Dimension(600, 300);
		pane.setMaximumSize(size);
		pane.setPreferredSize(size);
		pane.setMinimumSize(size);
		pane.add(new JScrollPane(categoryNamesList));
		pane.add(customCategoryLabel);
		pane.add(customCategoryField);
		pane.add(reclassifyFileButton);
		return pane;
	}



	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Naive Bayesian Classifier");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		StartGUI newContentPane = new StartGUI();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public void startClassifier(JLabel informationLabel) {
		informationLabel.setText("Creating Bayesian Network. Please wait ...");
		try {
			ChiSquared.critialChiValue = chiValue;
			Category.k_smoothingFactor = kValue;
		} catch (NumberFormatException e) {
			informationLabel.setText("This is not a correct number! Using default values...");
		}

		categories = new Categories();
		try {
			categories.addTrainingData(trainDirectoryPath);
		} catch (NoDirectoryException e) {
			informationLabel.setText("This is not a directory!");
			chooseFileToClassifyButton.setEnabled(false);
			customCategoryField.setEnabled(false);
			categoryNamesList.setEnabled(false);
			
			classifyFileButton.setEnabled(false);
			return;
		}
		informationLabel.setText("<html>Bayesian Network has been created!<br>Please go on to next tab to test it</html>");
		
		//Update the category name list
		categoryNames = categories.getNames();
		categoryNamesList.setListData(categoryNames);
		
		//Enable and disable elements
		trainClassifierButton.setEnabled(false);
		chooseFileToClassifyButton.setEnabled(true);
		classifyFileButton.setEnabled(true);
		reclassifyFileButton.setEnabled(false);
	}

}