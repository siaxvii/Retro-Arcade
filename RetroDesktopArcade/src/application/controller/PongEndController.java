package application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;

/**
 * PongEndController is a class that displays the player names and end results of the last pong game, and handles the
 * user's input (into PongEnd.fxml). Depending on their choice, either restarts the game, or returns them to the game
 * select menu.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class PongEndController implements EventHandler<ActionEvent>, Initializable{
	
	@FXML Text p1NameTxt;
	@FXML Text p2NameTxt;
	
	/**
	 * Displays the player names. The scores are set to display in PongGameController, right before the scene is switched.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.p1NameTxt.setText(Main.p1Name);
		this.p2NameTxt.setText(Main.p2Name);
		
	}
	
	/**
	 * Runs upon button press, and either restarts Pong or move to the game select menu depending on button fxID.
	 */
	@Override
	public void handle(ActionEvent event) {
		
		switch(((Button) event.getSource()).getId()) {
			
			case "restartBtn":
				
				try {
					
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation( getClass().getResource("../view/PongGame.fxml") );
					
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
