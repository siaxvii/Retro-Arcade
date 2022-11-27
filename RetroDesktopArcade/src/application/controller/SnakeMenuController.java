/*
 *  @author Leeza Mushtaq (shy277)
 */

package application.controller;

//All import statements
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SnakeMenuController implements EventHandler<ActionEvent>{

	//FXML elements initialized
	@FXML Button startBtn;
	@FXML Button backBtn;
	
	@Override
	public void handle(ActionEvent event) {
		Button b = (Button) event.getSource();
		//Opens Game Options Screen
		if(b.getId().equals("startBtn")) {//opens snake game view so that user can start playing
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/SnakeName.fxml"));
	
				Scene scene = new Scene(loader.load());
	
				Main.stage.setScene(scene);//opens scene
				Main.stage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(b.getId().equals("backBtn")) {//takes user back to game select view if they dont want to play this game
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/GameSelect.fxml"));
	
				Scene scene = new Scene(loader.load());
	
				Main.stage.setScene(scene);//opens new scene
				Main.stage.show();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
}
