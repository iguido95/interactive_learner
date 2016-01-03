package ui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;
 
/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class FileChooserDemo extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;
 
    public FileChooserDemo() {
        super(new BorderLayout());
 
        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
 
        //Create a file chooser
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        openButton = new JButton("Choose Training Directory...");
        openButton.addActionListener(new ActionListener() {
        	
				public void actionPerformed(ActionEvent e) {
	                int returnVal = fc.showOpenDialog(FileChooserDemo.this);
	                
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    File file = fc.getSelectedFile();
	                    //This is where a real application would open the file.
	                    System.out.println(file.getAbsolutePath());
	                    log.append("Opening: " + file.getName() + "." + newline);
	                } else {
	                    log.append("Open command cancelled by user." + newline);
	                }
					
				}
        	
        });
 
        JLabel chiValueLabel = new JLabel("Chi Value (real >= 0.0)");
        final JTextField chiValueField = new JTextField(5);
        chiValueField.addActionListener(new ActionListener()
        {
               public void actionPerformed(ActionEvent e)
               {
                     System.out.println(chiValueField.getText());
               }
        });
        
        JLabel kValueLabel = new JLabel("K Value (natural >= 1)");
        final JTextField kValueField = new JTextField(5);
        kValueField.addActionListener(new ActionListener()
        {
               public void actionPerformed(ActionEvent e)
               {
                     System.out.println(kValueField.getText());
               }
        });
        
 
        JPanel constantsPanel = new JPanel();
        constantsPanel.add(chiValueLabel);
        constantsPanel.add(chiValueField);
        constantsPanel.add(kValueLabel);
        constantsPanel.add(kValueField);
        
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        constantsPanel.add(openButton);
        
 
        //Add the buttons and the log to this panel.
        add(constantsPanel);
        //add(buttonPanel);
        //add(logScrollPane, BorderLayout.CENTER);
    }
 

 
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooserDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new FileChooserDemo());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}