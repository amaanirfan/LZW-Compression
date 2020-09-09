import java.io.*;
import java.util.*;
public class LZW {
	private HashMap<String, Integer> table;
	private FileReader fr;
	private BufferedReader br;
	private ArrayList<Integer> output;

	public LZW(String fileName) throws FileNotFoundException {
		fr = new FileReader(fileName);
		br = new BufferedReader(fr);
		table=new HashMap<String, Integer>();
		output = new ArrayList<Integer>();
	}
	
	public void fillTable() //puts in all the values for one letter chars into the hash map
	{
		table=new HashMap<String, Integer>();
		for(int n=0;n<127;n++){
			table.put(""+(char)n, n);
		}
	}
	
	public ArrayList<Integer> encodeString () throws IOException
	{
		fillTable();
		encode(br);
		return output;
	}
	
	public void encode(BufferedReader text) throws IOException //goes through text, adds new patterns to hmap, and updates output with more integers
	{
		String temp = "";
		int counter=127;
		while (text.ready())
		{
			temp+=(char)(text.read());
			if(!table.containsKey(temp)){
				String temp2=temp.substring(0,temp.length()-1);
				int tableIndex =table.get(temp2);
				output.add(tableIndex);
				counter++;
				table.put(temp, counter);
				temp = temp.substring(temp.length()-1);
			}	
		}
		output.add(table.get(temp));
	}

	public String toString(){
		return ("" + output);
	}

}
