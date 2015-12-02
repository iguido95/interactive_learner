
public class Word {
	private String name;
	private int count;
	
	Word(String name) {
		this.name = name;
		this.count = 1;
	}
	
	public String name() {
		return name;
	}
	
	public int count() { 
		return count;
	}
	
	public void incrementCount() {
		this.count++;
	}

}
