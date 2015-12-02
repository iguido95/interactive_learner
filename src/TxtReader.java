import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TxtReader {
	
	TxtReader() {		
	}
	
	public ArrayList readTxt(String filepath) throws FileNotFoundException {
		Scanner s = new Scanner(new File(filepath));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		    System.out.println(s.next());
		}
		s.close();
		return list;
	}
	
	public static void main(String[] args) {
		TxtReader tr = new TxtReader();
		try {
			tr.readTxt("C:\\Development\\eclipse\\workspace\\interactive_learner\\blogs\\F-test1.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}