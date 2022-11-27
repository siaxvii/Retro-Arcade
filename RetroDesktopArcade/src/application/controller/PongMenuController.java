package application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import application.Main;

/**
 * PongMenuController is a class that handles the user's input (into PongMenu.fxml), and depending on their choice
 * either moves to the name prompt or moves back to the Game Select menu.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class PongMenuController implements EventHandler<ActionEvent>{
	
	/**
	 * Runs upon button press, and switches to the appropriate scene (Pong Name Prompt / Main Menu) depending on button fxID.
	 */
	@Override
	public void handle(ActionEvent event) {
		
		switch(((Button) event.getSource()).getId()) {
			
			case "startBtn":
				
				try {
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation( getClass().getResource("../view/PongName.fxml") );
					
					AnchorPane layout = (AnchorPane) loader.load();
					Scene scene = new Scene(layout);
					
					Main.stage.setScene(scene);
					Main.stage.show();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
			
			case "backBtn":
				
				try {
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation( getClass().getResource("../view/GameSelect.fxml") );
					
					AnchorPane layout = (AnchorPane) loader.load();
					Scene scene = new Scene(layout);
					
					Main.stage.setScene(scene);
					Main.stage.show();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
			
		}
	}
}
