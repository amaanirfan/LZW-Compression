import java.util.*;
public class LZW {
	private HashMap<String, HashMap<Integer, String>> table;
	private FileReader fr;

	public LZW(String fileName) {
		fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
	}
	
	public void fillTable() //puts in all the values for one letter chars into the hash map
	{
		
	}
	
	public ArrayList<Integer> encodedString (BufferedReader text)
	{
		
	}

}
