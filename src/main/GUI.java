package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JTextField;

public class GUI extends Frame implements WindowListener,ActionListener {
        TextField text = new TextField(20);
        Button b;
        private int numClicks = 0;

        public static void main(String[] args) {
                GUI myWindow = new GUI("Interactive Learner");
                myWindow.setSize(500,500);
                myWindow.setVisible(true);
                
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setDialogTitle("Browse trainingset");

                myWindow.add(fileChooser, BorderLayout.NORTH);
               // fileChooser.addActionListener(new ActionListener());
        }

        public GUI(String title) {

                super(title);
                setLayout(new FlowLayout());
                addWindowListener(this);
                b = new Button("Browse");
                add(b);
                add(text);
                b.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
    		  if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
    			//  System.out.println("File selected " + fileChooser.getSelectedFile());
    		  }
        }

        public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}

}
