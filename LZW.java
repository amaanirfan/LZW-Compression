import java.util.*;
public class LZW {
	private HashMap<String, Integer> table;
	private FileReader fr;
	private ArrayList<Integer> output;

	public LZW(String fileName) {
		fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		table=new HashMap<Integer, String>();
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
		encode(text);
		return output;
	}
	
	public void encode(BufferedReader text) //goes through text, adds new patterns to hmap, and updates output with more integers
	{
		String temp = "";
		while (text.ready())//while ((i=br.read()) != -1){
		{
			temp+=char(text.read());
			if(!table.containsKey(temp)){
				String temp2=temp.substring(0,temp.length()-1)
				int tableIndex =table.get(temp2);
				output.add(tableIndex);
				temp = temp.substring(temp.length()-1));
			}
		}
	}

}
