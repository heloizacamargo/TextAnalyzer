package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;
 
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
//   	      File file = new File("theraven.txt");
   	      Scanner scan = new Scanner(file, "UTF-8");
   	      //add lines to array list "lines"
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
		    			wordMap.put(w, 1); //if word is not in map: add word to map
		    		}
		    		else {
		    			wordMap.put(w, wordMap.get(w) + 1); //If word already in map: add +1 to value
		    		}	    	
	    		}
	    	}	
	    }
    	
    	for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            list.add(entry.getValue()); //add values to array list "list"
        }
 	    
        Collections.sort(list); //sort smallest to largest
        Collections.reverse(list); //sort largest to smallest
        
        for (int num : list) {
            for (Entry<String, Integer> entry : wordMap.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num); //add "wordMap" keys and sorted "list" elements to "sortedMap"
                }
            }
        }

        	        
        sortedMap.forEach( (key, value) -> {
            System.out.printf("%-20s %d%n", key, value);
        } );
        
        return sortedMap;
    }     
}