package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * MainController is a class ran upon opening the program that waits for the user to click a button before taking
 * them to the game selection screen.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class MainController implements EventHandler<ActionEvent>{
	
	/**
	 * Runs once the user presses the Start button, and moves them to the Game Select screen.
	 */
	@Override
	public void handle(ActionEvent event) {
		
			try {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/GameSelect.fxml"));
	
				Scene scene = new Scene(loader.load());
	
				Main.stage.setScene(scene);
				Main.stage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
	}
	
}