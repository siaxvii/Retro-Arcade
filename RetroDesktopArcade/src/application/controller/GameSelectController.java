package application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import application.Main;

/**
 * GameSelectController is a class that handles the user's input (into GameSelect.fxml), and depending on their choice
 * moves them to the Snake menu, the Pong menu, or back to the Main menu.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class GameSelectController implements EventHandler<ActionEvent>{
	
	/**
	 * Runs upon button press, and switches to the appropriate scene (Snake Menu, Pong Menu, Main Menu) depending on button fxID.
	 */
	@Override
	public void handle(ActionEvent event) {
		
		switch(((Button) event.getSource()).getId()) {
			
			case "snakeBtn":
				
				try {
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation( getClass().getResource("../view/SnakeMenu.fxml") );
					
					AnchorPane layout = (AnchorPane) loader.load();
					Scene scene = new Scene(layout);
					
					Main.stage.setScene(scene);
					Main.stage.show();
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
			
			case "pongBtn":
				
				try {
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation( getClass().getResource("../view/PongMenu.fxml") );
					
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
					loader.setLocation( getClass().getResource("../view/Main.fxml") );
					
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
