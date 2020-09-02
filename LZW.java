import java.util.*;
public class LZW {
	private HashMap<Integer, String>> table;
	private FileReader fr;
	private ArrayList<Integer> output;

	public LZW(String fileName) {
		fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		table=new H
		output = new ArrayList<Integer>();
	}
	
	public void fillTable() //puts in all the values for one letter chars into the hash map
	{
		table=new HashMap<Integer, String>;
		for(int n=0;n<127;n++){
			table.put(n,(char)n);
		}
	}
	
	public ArrayList<Integer> encodeString (BufferedReader text)
	{
		while (text.ready())
		{
			encode(text);
		}
		return output;
	}
	
	public void encode(BufferedReader text) //goes through text, adds new patterns to hmap, and updates output with more integers
	{
		
	}

}
