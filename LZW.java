import java.io.*;
import java.util.*;
public class LZW {
	private HashMap<String, Integer> table;
	private FileReader fr;
	private BufferedReader br;
	private String newFileName;

	public LZW(String fileName) throws FileNotFoundException {
		fr = new FileReader(fileName);
		br = new BufferedReader(fr);
		table=new HashMap<String, Integer>();
		newFileName = fileName+".lzw";
	}
	
	public void fillTable() //puts in all the values for one letter chars into the hash map
	{
		table=new HashMap<String, Integer>();
		for(int n=0;n<127;n++){
			table.put(""+(char)n, n);
		}
	}
	
	public void createFile () throws IOException
	{
		FileWriter out=new FileWriter(newFileName);
		BufferedWriter put = new BufferedWriter(out);
		fillTable();
		encode(br);
		put.close();
	}
	
	public void encode(BufferedReader text) throws IOException //goes through text, adds new patterns to hmap, and updates output with more integers
	{
		String temp = "";//the temp variable keeps track of the series of letters you are reading so you can check if the variable is already present in you table or not
		int counter=127;
		while (text.ready())
		{
			temp+=(char)(text.read());
			
			if(!table.containsKey(temp)){ //if the table contains the doesn't have the series of letters already it adds the new pattern to the table and resets the temp variable to the the last letter of the pattern
				String temp2=temp.substring(0,temp.length()-1);//temp 2 is a temporary placeholder that holds all the characters of temp besides the last one so that we can ouput the pattern which should be in the table already
				int tableIndex =table.get(temp2);
				put.write(""+tableIndex+" ");
				counter++;
				table.put(temp, counter);
				temp = temp.substring(temp.length()-1);
			}	
		}
		put.write(""+table.get(temp));//writes the last code
	}
}
