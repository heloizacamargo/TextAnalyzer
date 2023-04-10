package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * Tutorial reads the text file and counts how many times a word appears.
 * 
 * @author Heloiza Camargo
 */

public class Tutorial extends Application {
	File file = new File("theraven.txt");
	static ArrayList<String> lines = new ArrayList<>();
	static HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
	static LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
	static ArrayList<Integer> list = new ArrayList<>();
    Button button;
    
    public static void main(String args[]){          
        launch(args);      
    }
    
    @Override    
    public void start(Stage primaryStage) throws Exception { 
        StackPane layout = new StackPane();
         
        Scene scene = new Scene(layout, 300, 300);        

        Button button = new Button("Click me to count words!");
        button.setOnAction(e -> wordOccurrence(file));
         
        layout.getChildren().addAll(button);
         
        primaryStage.setTitle("Word Occurrence");
        primaryStage.setScene(scene);   
        primaryStage.show();
    }   
    

    public LinkedHashMap<String, Integer> wordOccurrence(File file) {
    	
    	try {
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
        
        return sortedMap;
    }     
}