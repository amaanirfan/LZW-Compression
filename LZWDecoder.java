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
		//the final output to the file
		String output = "";
		
		//input string we're about to encode
		String buffer = "";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		int inputCharNum = reader.read();
		while(inputCharNum != -1) {
			//add the new character to our buffer
			buffer += Character.toString((char)inputCharNum);
			
			
		}
		
		
		
		
		//writing the output to the file
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(outputFile)));
		outputWriter.write(output);
		reader.close();
		outputWriter.close();
	}
}