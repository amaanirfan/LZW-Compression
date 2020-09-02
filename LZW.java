public class LZW {
	private HashMap<String, HashMap<Integer, String>> table;
	private FileReader fr;

	public LZW(String fileName) {
		fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
	}
	
	public void fillTable()
	{
		
	}

}
