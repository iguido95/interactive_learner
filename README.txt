Interactive Learner by Guido Teunissen & Kilian Ros
---------------------------------------------------
1. Open InteractiveLearner.java in the main package.
2. Run the class and follow the Textual User Interface instructions 
   in the console.

*TUI steps
1. Provide a path to the directory of your training data. 
e.g. C:/Development/eclipse/workspace/interactive_learner/blogs/TRAINING
All the files should be named by this pattern: CLASSNAME-restOfFileName.txt

2. Provide a path to the file of the file you want to test.
e.g. C:/Development/eclipse/workspace/interactive_learner/blogs/M/M-test9.txt


----Extra Explanation Implementation-----
See our previous report for the used formulas. We chose to use the multinomial 
implementation to calculate the conditional probability. 

It all starts with the TxtReader class (aside from the TUI and the starter class). 
This class loads in the training documents by a specified directory by the user. 
It iterates over all the documents in the given directory. To identify which 
category a document belongs to the following file name structure has to be 
user: “CLASSNAME-restOfFileName.txt”, so for example ‘female-blog12training.txt’. 
Each document will become an array of words and an identifier for the category.
The TxtReader also tokenizes and normalizes the words like mentioned above.

When the TxtReader is done it passes the information to the Categories class 
which separates all the documents and their words into the correct 
category (and makes a new category if not already exists, based on the name). 
It also keeps track of the total vocabulary and the total documents, which 
are needed for the probability calculation. The vocabulary and number if 
documents is also tracked for each individual category in the Category class.

The calculation for a classification is implemented in the Category class by 
the formula’s mentioned in the previous report. It now also calculates in the 
log space to account for very small probabilities. 
