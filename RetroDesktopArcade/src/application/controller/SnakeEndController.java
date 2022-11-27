/*
 * @author Leeza Mushtaq (shy277)
 * 
 */
package application.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import application.Main;
import application.model.SnakePlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SnakeEndController implements EventHandler<ActionEvent> , Initializable{
	//all fxml elements needed for display
	@FXML Button playAgainBtn;
	@FXML Button menuBtn;
	@FXML Text firstPlace;
	@FXML Text secondPlace;
	@FXML Text thirdPlace;
	@FXML Text firstName;
	@FXML Text secondName;
	@FXML Text thirdName;
	
	SnakePlayer player;//player object that will be used 
	//as of now, score will be counted in SnakeGameController. that score will be in player.SCORE.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		player = SnakeNameController.player;//static, takes in values from the controller
		
		//players first time playing
		String score = String.valueOf(player.SCORE);//changes score to string value so that it can be used in setText
		if(firstPlace.getText().isEmpty()) {//if the user's score is empty, this is their first time playing
			firstPlace.setText(score);//set the score 
			SnakeController.sortedScore[2] = player.SCORE;//set the index for highest score value, 2, to the score
			
		}
		//check if score isnt already in array
		else if(player.SCORE != SnakeController.sortedScore[0] && player.SCORE != SnakeController.sortedScore[1] && player.SCORE != SnakeController.sortedScore[2]) {
			//check if current score is larger than any values in array
	         if(player.SCORE > SnakeController.sortedScore[0] || player.SCORE > SnakeController.sortedScore[1] || player.SCORE > SnakeController.sortedScore[2]) {
	        	//insert score into where it belongs
	        	// get min index, replace with the player's score
	        	 int changeIndex = 0;//will hold min index
	        	  int min = SnakeController.sortedScore[0]; 
	        	    for(int i=0;i<3;i++){ 
	        	      if(SnakeController.sortedScore[i] < min){ 
	        	        min = SnakeController.sortedScore[i];
	        	        changeIndex = i;//get min index and then use this later
	        	      } 
	        	    }
	        	 //change array to player score at min index 
	        	    SnakeController.sortedScore[changeIndex] = player.SCORE;
	         }//end LITTLE IF
	         //then sort the array. 2nd index is the highest score, 0th is the lowest score.
	         Arrays.sort(SnakeController.sortedScore);//least to greatest
		}//end BIG IF
		
		//set username text on view
		firstName.setText(player.UN);
		secondName.setText(player.UN);
		thirdName.setText(player.UN);
		//set score text on view
		String first = String.valueOf(SnakeController.sortedScore[2]);
		firstPlace.setText(first);
		String second = String.valueOf(SnakeController.sortedScore[1]);
		secondPlace.setText(second);
		String third = String.valueOf(SnakeController.sortedScore[0]);
		thirdPlace.setText(third);
	}//end initialize 
	
	
	@Override
	public void handle(ActionEvent event) {
		Button b = (Button) event.getSource();
		if(b.getId().equals("playAgainBtn")) {//lets user play game again
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/SnakeGame.fxml"));
	
				Scene scene = new Scene(loader.load());
	
				Main.stage.setScene(scene);//sets scene 
				Main.stage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(b.getId().equals("menuBtn")) {//or takes user to gameselect if they choose "main menu" button
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
		
	}//end handle	

}//end all
