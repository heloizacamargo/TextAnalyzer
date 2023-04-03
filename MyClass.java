import java.io.*;  // Import the File class
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;


/**
 * MyClass reads the text file and counts how many times a word appears.
 * 
 * @author Heloiza Camargo
 */
public class MyClass {
	
	public static void main(String[] args) {
		
		ArrayList<String> lines = new ArrayList<>();
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		ArrayList<Integer> list = new ArrayList<>();
		
	    try {
	      File file = new File("theraven.txt");
	      Scanner scan = new Scanner(file, "UTF-8");	   
	      while (scan.hasNextLine()) {	      
	    	  lines.add(scan.nextLine());
	      }	    
	      scan.close();	      
	    } 
	    
	    catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();	      	      
	    }
	    
	    for(String line: lines) {
	    	//Remove punctuation, change all to lower case
	    	String[] words = line.replaceAll("\\p{IsPunctuation}", " ").toLowerCase().split("\\s+");
	    	for(String w: words) {
	    		if(!(w.isEmpty())) {
		    		if(!(wordMap.containsKey(w))) {
		    			wordMap.put(w, 1);
		    		}
		    		else {
		    			wordMap.put(w, wordMap.get(w) + 1);
		    		}	    	
	    		}
	    	}	
	    }	    	
	    

	    
	    for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        Collections.reverse(list);
        for (int num : list) {
            for (Entry<String, Integer> entry : wordMap.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        
        
        sortedMap.forEach( (key, value) -> {
        System.out.printf("%-20s %d%n", key, value);
        } );
	}
}