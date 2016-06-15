package priv.online;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ip_set {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>();  
		set.add("1.2.3.6");  
		set.add("b");  
		set.add("c");  
		set.add("d");  
		set.add("d");  
		set.add("d");  
		set.add("d");  
		set.add("uuu");  
		set.add("e"); 
		    
		Iterator<String> it = set.iterator();  
		while (it.hasNext()) {
			System.out.println(it.next());  
		}
	}

}
