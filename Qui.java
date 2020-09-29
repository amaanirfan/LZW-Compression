import java.util.*;

public class Qui extends LinkedList<String> {
	public void placeToBack(String recentlyUsed) { //useful when I just used a String in my encoder
		String temp;
		
		ListIterator<String> iterator = listIterator();
		while(iterator.hasNext()) { //loops through the LinkedList until it finds the specified node. It then deletes that node and places it to the end.
			if(iterator.next().equals(recentlyUsed)) {
				temp = iterator.previous();
				remove(iterator.nextIndex());
				add(temp);
				break;
			}
		}
	}
}
