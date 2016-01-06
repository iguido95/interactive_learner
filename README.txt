Interactive Learner by Guido Teunissen & Kilian Ros
---------------------------------------------------
Using the program:
1. Import the folder as an existing project in Eclipse (or similar software)
2. Open InteractiveLearner.java in the main package.
3. Run the class and follow the User Interface instructions 
   in the console.

*GUI steps
1. Beforehand you can change the K smoothing value and/ or the critical Chi square value.
   Please mind that you have to press enter in order to save the values.
2. Click the "Choose Training Directory..." button to select your training directory.
All the files in the training directory should be named by this pattern: CLASSNAME-restOfFileName.txt
3. Press the "Train Classifier" button to train the classifier, please wait for this process to complete.
4. Go on to the next tab: "Use the Classifier"
5. Choose your test file by clicking the "Choose Txt File..." button
6. Click the "Classify File" button to get the most probable category.
---Optionally---
7. You can retrain the classifier by selecting one of the existing categories in the list or enter a new category.
   Please mind that you have to press enter in order to save your custom category.
8. Press the "Re-Classify File" button to execute the re-classification.
---
9. After this you could select a new file ‘Choose Txt File…’ to classify again.

*TUI steps
You can also still use the TUI we made before the GUI. You can start the TUI by
uncommenting the startTUI line in the main method of InteractiveLearner.java.
It also works, but is mainly kept in the code for the reviewer to get a more clear
look at what happens during the execution of the program.

