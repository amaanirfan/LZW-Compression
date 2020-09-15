import java.util.*;
import java.io.*;

public class LZWDecoder {
	//codeMap is a hashmap that stores the LZW table
	private HashMap<String, Integer> codeMap;
	//lastIndex stores the index of last number that we added to the table (# things in table - 1)
	private int lastIndex;
		
	public LZWDecoder() {
		//adds first 128 ascii characters to table
		codeMap =  new HashMap<String, Integer>(128);
		for (int i = 0; i<128; i++) {
			codeMap.put(Character.toString((char)(i)), i); //value of ith ascii as a key is i
		}
		lastIndex = 127;
	}
	
	public void decode(String inputFile, String outputFile) throws IOException{
		//input string we're about to encode
		String buffer = "";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(outputFile)));
		int inputCharNum = reader.read();
		while(inputCharNum != -1) {
			//add the new character to our buffer
			buffer += Character.toString((char)inputCharNum);
			
			
		}
		
		
		
		
		//closing the reader and writer
		reader.close();
		outputWriter.close();
	}
}