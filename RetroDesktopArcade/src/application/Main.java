package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

// NOTE: All FXML files were made by Leeza Mushtaq (shy277)

/**
 * Main is the driver of the program. It holds the player names for access between the different stages of each game.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class Main extends Application {
	
	public static Stage stage;
	public static String p1Name;
	public static String p2Name;
	
	/**
	 * Sets the initial scene to the Main menu.
	 */
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			stage = primaryStage;
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( getClass().getResource("view/Main.fxml") );
			
			AnchorPane layout = (AnchorPane) loader.load();
			Scene scene = new Scene(layout);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launches the JavaFX application.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
