import java.util.*;
import java.io.*;

public class LZWDecoder {
	//codeMap is a hashmap that stores the LZW table
	private HashMap<Integer, String> codeMap;
	//lastIndex stores the index of last number that we added to the table (# things in table - 1)
	private int lastIndex;
	//the initial size of the table
	private final int initTableSize = 128;
		
	public LZWDecoder() {
		//adds first 128 ascii characters to table
		codeMap =  new HashMap<Integer, String>(initTableSize);
		for (int i = 0; i<initTableSize; i++) {
			codeMap.put(i, Character.toString((char)(i))); //value of ith ascii as a key is i
		}
		lastIndex = initTableSize - 1;
	}
	
	public void decode(String inputFile, String outputFile) throws IOException{
		//input string we're about to encode
		String buffer = "";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(outputFile)));
		//read the first char beforehand
		int inputCharNum = reader.read();
		//stores the previous code
		String prev = "";
		Integer bufferNum = new Integer(0);
		while(inputCharNum != -1) {
			//add the new character to our buffer
			buffer += Character.toString((char)inputCharNum);
			
			//buffer will now become all the chars up until the next space
			//the one exception is if we have SPACE SPACE SPACE, in which case buffer=space
			inputCharNum = reader.read();
			while(inputCharNum != ' ' && inputCharNum != -1) { 
				buffer += Character.toString((char)inputCharNum);
				inputCharNum = reader.read();
			}

			//if a written out number (like 2539) we parse the int, otherwise it's a single char/int
			if (buffer.length() > 1) {
				bufferNum = new Integer(Integer.parseInt(buffer));
			} else {
				bufferNum = new Integer((int)(buffer.charAt(0)));
			}
			
			//if the code has not been added yet, that means the last char is the first char of prev
			if (!codeMap.containsKey(bufferNum)) {
				outputWriter.write(prev+prev.substring(0,1));
				codeMap.put(bufferNum, prev+prev.substring(0,1));
				lastIndex++;
			} else { //write the corresponding string and add to codeMap
				outputWriter.write(codeMap.get(bufferNum));
				if (!prev.equals("")) {
					codeMap.put(lastIndex+1, prev+codeMap.get(bufferNum).substring(0,1));
					lastIndex++;
				}
			}
			
			//update variables
			prev = codeMap.get(bufferNum);
			buffer = "";
			inputCharNum = reader.read();
		}
			
		//closing the reader and writer
		reader.close();
		outputWriter.close();
	}
}