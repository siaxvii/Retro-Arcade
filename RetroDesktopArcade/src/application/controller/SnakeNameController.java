/*
 * @author Leeza Mushtaq (shy277)
 */
package application.controller;

import application.Main;
import application.model.SnakePlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SnakeNameController implements EventHandler<ActionEvent>{
	//fxml elements for snake name controller 
	@FXML
	Button nextBtn;
	@FXML 
	TextArea name;
	@FXML
	Text authenticate;
	
	public static SnakePlayer player;//makes object representing the user/player 
	
	@Override
	public void handle(ActionEvent event) {
		String UN = name.getText();//gets user entered name
		player = SnakePlayer.authenticate(UN);//will make sure name string is not empty 
		
		Button b = (Button) event.getSource();//makes button object
		 
		if(player != null && b.getId().equals("nextBtn")) {//opens snake game view 
				try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("../view/SnakeGame.fxml"));
		
						Scene scene = new Scene(loader.load());
		
						Main.stage.setScene(scene);
						Main.stage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
		}
		else if (b.getId().equals("nextBtn")){
			authenticate.setText("ERROR: Enter your name!");//will ask user to enter valid name if none
		}
	}//end handle

}