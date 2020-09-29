import java.io.*;
import java.util.*;
public class LZW {
	private HashMap<String, Integer> table;
	private FileReader fr;
	private BufferedReader br;
	private String newFileName;
	private final int INIT_TABLE_SIZE = 127;
	private final int EXTENDED_ASCII_SIZE = 256;
	private int maxSize;
	private Qui homeQueue;

	public LZW(String fileName, int maxSize) throws FileNotFoundException {
		fr = new FileReader(fileName);
		br = new BufferedReader(fr);
		table=new HashMap<String, Integer>();
		newFileName = "out.txt"; //OUTPUT FILE
		this.maxSize = maxSize;
		homeQueue = new Qui ();
	}
	
	public void fillTable() //puts in all the values for one letter chars into the hash map
	{
		table=new HashMap<String, Integer>();
		for(int n=0;n<INIT_TABLE_SIZE;n++){
			table.put(""+(char)n, n);
			homeQueue.add("" + (char)n);
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
				homeQueue.placeToBack(temp);
				counter++;
				if (maxSize <= table.size()) {
					String leastRecent = homeQueue.pollFirst();
					table.remove(leastRecent);
					//add this line in and find the last thing in the stack and delete it from the stack and earlier whenever we use an element from the table push that string to top of stack
				}
				table.put(temp, counter); //adds code to table
				//queue.push(temp);
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
