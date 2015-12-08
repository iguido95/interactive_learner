import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JTextField;

public class GUI extends JPanel {

      public static void main(String[] a) {
         JFrame f = new JFrame();
         
         
         final JFileChooser fileChooser = new JFileChooser();
         fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         fileChooser.setDialogTitle("Browse trainingset");
         
         
         
         JButton startButton = new JButton("Start");
         
         
         f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               System.exit(0);
            }
         }
      );
      f.setContentPane(new GUI());
      f.setSize(400,400);
      f.setVisible(true);
      f.add(fileChooser, BorderLayout.NORTH);
      fileChooser.addActionListener(new ActionListener(){
    	  public void actionPerformed(ActionEvent e) {
    		  if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
    			  System.out.println("File selected " + fileChooser.getSelectedFile());
    		  }
    	  }
      });
      f.add(startButton);
   }
}