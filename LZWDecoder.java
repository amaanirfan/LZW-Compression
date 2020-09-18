import java.util.*;
import java.io.*;

public class LZWDecoder {
	//codeMap is a hashmap that stores the LZW table
	private HashMap<Integer, String> codeMap;
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
		//input string we're about to encode
		String buffer = "";
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(new File(outputFile)));
		int inputCharNum = reader.read();
		String prev = "";
		Integer bufferNum = new Integer(0);
		while(inputCharNum != -1) {
			//add the new character to our buffer
			buffer += Character.toString((char)inputCharNum);
			inputCharNum = reader.read();
			while(inputCharNum != ' ' && inputCharNum != -1) { 
				buffer += Character.toString((char)inputCharNum);
				inputCharNum = reader.read();
			}

			if (buffer.length() > 1) {
				bufferNum = new Integer(Integer.parseInt(buffer));
			} else {
				bufferNum = new Integer((int)(buffer.charAt(0)));
			}

			if (!codeMap.containsKey(bufferNum)) {
				outputWriter.write(prev+prev.substring(0,1));
				codeMap.put(bufferNum, prev+prev.substring(0,1));
				lastIndex++;
			} else {
				outputWriter.write(codeMap.get(bufferNum));
				if (!prev.equals("")) {
					codeMap.put(lastIndex+1, prev+codeMap.get(bufferNum).substring(0,1));
					lastIndex++;
				}
			}
			//System.out.println(buffer);
			prev = codeMap.get(bufferNum);
			buffer = "";
			inputCharNum = reader.read();
		}
			
		//closing the reader and writer
		reader.close();
		outputWriter.close();
	}
}