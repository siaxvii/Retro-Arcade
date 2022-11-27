package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * PongNameController is a class that handles the user's input (into PongName.fxml). Upon clicking the "next" button
 * or pressing enter, it verifies text from the two available text fields for the names of both players and continues
 * to the Pong menu if successful. If unsuccessful, it displays an error message and waits for further input.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class PongNameController implements EventHandler<ActionEvent>{
	
	@FXML TextField p1NameTxt;
	@FXML TextField p2NameTxt;
	@FXML Text errorTxt;
	
	private String p1Name;
	private String p2Name;
	
	/**
	 * Handles and verifies user input before continuing to the Pong menu.
	 */
	@Override
	public void handle(ActionEvent event) {
		
		this.p1Name = this.p1NameTxt.getText();
		this.p2Name = this.p2NameTxt.getText();
		
		if (this.p1Name.isEmpty()||this.p2Name.isEmpty()) {
			this.errorTxt.setText("ERROR: One or more names are missing!");
		}
		
		else {
			
			Main.p1Name = this.p1Name;
			Main.p2Name = this.p2Name;
						
			try {
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/PongGame.fxml"));
	
				Scene scene = new Scene(loader.load());
	
				Main.stage.setScene(scene);
				Main.stage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}