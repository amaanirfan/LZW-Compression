import java.io.*;
public class LZWTester {
	public static void main (String [] args) throws IOException
	{
		LZW test = new LZW("tester.txt");
		test.createFile();
	}
	
}