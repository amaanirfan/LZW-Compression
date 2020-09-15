import java.util.*;
import java.io.*;

public class LZWDecoder {
	//codeMap is a hashmap that stores the LZW table
	private HashMap<String, Integer> codeMap;
	//lastIndex stores the index of last number that we added to the table (# things in table - 1)
	private int lastIndex;
		
	public LZWDecoder() {
		//adds first 128 ascii characters to table
		codeMap =  new HashMap<Integer, String>(128);
		for (int i = 0; i<128; i++) {
			codeMap.put(i, Character.toString((char)(i))); //value of ith ascii as a key is i
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
		String prev = "";
		while(inputCharNum != -1) {
			//add the new character to our buffer
			while(inputCharNum != ' ') { //need to also check for double space...
				buffer += Character.toString((char)inputCharNum);
				inputCharNum = reader.read();
			}
		
			bufferNum = Integer.parseInt(buffer);

			if (lastIndex < 256) { //characters
				if (!codeMap.containsKey((char)bufferNum)) {
					
					lastIndex++;
				} else {
					outputWriter.write(codeMap.get((char)buffer));
				}
			} else { //integers
				if (!codeMap.containsKey(buffer)) {
					lastIndex++;
				} else {
					outputWriter.write(codeMap.get(new Integer(bufferNum)));
				}
			}
			codeMap.put(prev+buffer.substring(0,1), lastIndex+1);
			prev = buffer;
			buffer = "";
			
		}
		
		
		
		
		//writing the output to the file
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(outputFile)));
		outputWriter.write(output);
		reader.close();
		outputWriter.close();
	}
}