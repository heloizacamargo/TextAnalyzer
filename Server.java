package application;
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application{
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) throws IOException {
		// Text area for displaying contents
		TextArea ta = new TextArea();
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Server"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
		ServerSocket serverSocket = new ServerSocket(8000);
		new Thread( () -> {
			try {
				// Create a server socket
				Runtime.getRuntime().addShutdownHook(new Thread(){public void run(){
				    try {
				    	serverSocket.close();
				        System.out.println("The server is shut down!");
				    } catch (IOException e) { /* failed */ }
				}});
				
				Platform.runLater(() ->
					ta.appendText("Server started at " + new Date() + '\n'));
					
				// Listen for a connection request
				Socket socket = serverSocket.accept();
					
				// Create data input and output streams
				DataInputStream inputFromClient = new DataInputStream(
						socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(
						socket.getOutputStream());
					
				while (true) {
					// Receive radius from the client
					int n = inputFromClient.readInt();
						
					// Compute area
					//double area = radius * radius * Math.PI;
					
					
						
					// Send area back to the client
					outputToClient.writeBoolean(isPrime(n));
						
					Platform.runLater(() -> {
						ta.appendText("Number received from client: " + n + '\n');
						ta.appendText("Check if prime: " + isPrime(n) + '\n');
					});
				}
			
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}).start();
	}
	
	/**
	* The main method is only needed for the IDE with limited
	* JavaFX support. Not needed for running from the command line.
	*/
	public static void main(String[] args) {
	launch(args);
	
	}
	
	static boolean isPrime(int n) {
		int count = 0;
		 
        for (int i = 1; i <= n; i++)
 
        {
 
            if (n % i == 0)
 
                count++;
        }
 
        if (count == 2)
 
            return true;
 
        else
 
            return false;
    }
}
	
	


