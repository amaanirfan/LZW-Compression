import java.io.*;
import java.util.*;
public class LZW {
	private HashMap<String, Integer> table;
	private FileReader fr;
	private BufferedReader br;
	private String newFileName;
	private final int INIT_TABLE_SIZE = 127;
	private final int EXTENDED_ASCII_SIZE = 256;

	public LZW(String fileName) throws FileNotFoundException {
		fr = new FileReader(fileName);
		br = new BufferedReader(fr);
		table=new HashMap<String, Integer>();
		newFileName = "out.txt";
		
	}
	
	public void fillTable() //puts in all the values for one letter chars into the hash map
	{
		table=new HashMap<String, Integer>();
		for(int n=0;n<INIT_TABLE_SIZE;n++){
			table.put(""+(char)n, n);
		}
	}
	
	public void createFile () throws IOException
	{
		fillTable();
		encode(br);
	}
	
	public void encode(BufferedReader text) throws IOException //goes through text, adds new patterns to hmap, and updates output with more integers
	{
		String temp = "";//the temp variable keeps track of the series of letters you are reading so you can check if the variable is already present in you table or not
		int counter=INIT_TABLE_SIZE;
		FileWriter out;
		try {
			out = new FileWriter(newFileName);
			BufferedWriter put = new BufferedWriter(out);
		
		while (text.ready())
		{
			temp+=(char)(text.read());
			if(!table.containsKey(temp)){ //if the table doesn't have the series of letters already it adds the new pattern to the table
				String temp2=temp.substring(0,temp.length()-1);//temp 2 is a temporary placeholder that holds all the characters of temp besides the last one so that we can output the pattern which should be in the table already
				int tableIndex =table.get(temp2);
				if (tableIndex >= EXTENDED_ASCII_SIZE) {
					put.write(""+tableIndex+" "); //numbers not corresponding to ASCII table chars stay the same
				} else {
					put.write(""+(char)(tableIndex)+" "); //numbers corresponding to ASCII table chars get converted to chars (less space)
				}
				counter++;
				table.put(temp, counter); //adds code to table
				temp = temp.substring(temp.length()-1); //resets temp string to last char
			}	
		}
		put.write(""+table.get(temp)); //writes the last code
		put.close();
		} catch (IOException e) {
			System.out.println("BIG OOPS");
		}
	}
}
