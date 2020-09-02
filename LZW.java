import java.util.*;
public class LZW {
	private HashMap<Integer, String>> table;
	private FileReader fr;

	public LZW(String fileName) {
		fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		table=new H
	}
	
	public void fillTable() //puts in all the values for one letter chars into the hash map
	{
		
	}
	
	public ArrayList<Integer> encodedString (BufferedReader text)
	{
		table=new HashMap<Integer, String>;
		for(int n=0;n<127;n++){
			table.put(n,(char)n);
		}
	}

}
