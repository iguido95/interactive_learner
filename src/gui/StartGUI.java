package gui;

import javax.swing.*;
import javax.swing.border.*;

import exceptions.NoDirectoryException;
import main.Categories;
import main.Category;
import main.ChiSquared;
import main.TUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StartGUI extends JPanel {

	double chiValue = 0.0;
	int kValue = 1;
	String trainDirectoryPath = "";

	Categories categories;

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

		JPanel labelAndComponent = new JPanel();
		// Use default FlowLayout.
		labelAndComponent.add(createLabelAndComponent(false));
		labelAndComponent.add(createLabelAndComponent(true));
		tabbedPane.addTab("X alignment mismatch", labelAndComponent);

		JPanel buttonAndComponent = new JPanel();
		// Use default FlowLayout.
		buttonAndComponent.add(createYAlignmentExample(false));
		buttonAndComponent.add(createYAlignmentExample(true));
		tabbedPane.addTab("Y alignment mismatch", buttonAndComponent);

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
		Dimension size = new Dimension(500, 100);
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
		Dimension size = new Dimension(500, 100);
		pane.setMaximumSize(size);
		pane.setPreferredSize(size);
		pane.setMinimumSize(size);
		pane.add(openButton);
		pane.add(directoryLabel);

		return pane;
	}

	protected JPanel createTrainClassifierRow() {

		JButton button1 = new JButton("Train Classifier");
		final JLabel informationLabel = new JLabel();
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start Classifier!");
				startClassifier(informationLabel);
			}
		});

		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createTitledBorder("Train Classifier"));
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		Dimension size = new Dimension(500, 100);
		pane.setMaximumSize(size);
		pane.setPreferredSize(size);
		pane.setMinimumSize(size);
		pane.add(button1);
		pane.add(informationLabel);
		return pane;
	}

	protected JPanel createLabelAndComponent(boolean doItRight) {
		JPanel pane = new JPanel();

		JComponent component = new JPanel();
		Dimension size = new Dimension(150, 100);
		component.setMaximumSize(size);
		component.setPreferredSize(size);
		component.setMinimumSize(size);
		TitledBorder border = new TitledBorder(new LineBorder(Color.black),
				"A JPanel", TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		border.setTitleColor(Color.black);
		component.setBorder(border);

		JLabel label = new JLabel("This is a JLabel");
		String title;
		if (doItRight) {
			title = "Matched";
			label.setAlignmentX(CENTER_ALIGNMENT);
		} else {
			title = "Mismatched";
		}

		pane.setBorder(BorderFactory.createTitledBorder(title));
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.add(label);
		pane.add(component);
		return pane;
	}

	protected JPanel createYAlignmentExample(boolean doItRight) {
		JPanel pane = new JPanel();
		String title;

		JComponent component1 = new JPanel();
		Dimension size = new Dimension(100, 50);
		component1.setMaximumSize(size);
		component1.setPreferredSize(size);
		component1.setMinimumSize(size);
		TitledBorder border = new TitledBorder(new LineBorder(Color.black),
				"A JPanel", TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		border.setTitleColor(Color.black);
		component1.setBorder(border);

		JComponent component2 = new JPanel();
		size = new Dimension(100, 50);
		component2.setMaximumSize(size);
		component2.setPreferredSize(size);
		component2.setMinimumSize(size);
		border = new TitledBorder(new LineBorder(Color.black), "A JPanel",
				TitledBorder.CENTER, TitledBorder.BELOW_TOP);
		border.setTitleColor(Color.black);
		component2.setBorder(border);

		if (doItRight) {
			title = "Matched";
		} else {
			component1.setAlignmentY(TOP_ALIGNMENT);
			title = "Mismatched";
		}

		pane.setBorder(BorderFactory.createTitledBorder(title));
		pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
		pane.add(component1);
		pane.add(component2);
		return pane;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = StartGUI.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("BoxAlignmentDemo");
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
			informationLabel
					.setText("This is not a correct number! Using default values...");
		}

		categories = new Categories();
		try {
			categories.addTrainingData(trainDirectoryPath);
		} catch (NoDirectoryException e) {
			informationLabel.setText("This is not a directory!");
			return;
		}
		informationLabel.setText("<html>Bayesian Network has been created!<br>Please go on to next tab to test it</html>");
	}
}