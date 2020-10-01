import java.io.*;
public class LZWTester {
	public static void main (String [] args) throws IOException
	{
		LZW test = new LZW("message.txt", 4000);
		test.createFile();
		//LZWDecoder decoder = new LZWDecoder();
		//decoder.decode("out.txt", "bigtest.txt");
	}
	
}