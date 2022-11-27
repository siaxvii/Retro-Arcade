package application.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import application.model.Ball;
import application.model.Paddle;
import application.model.PongTask;

/**
 * PongGameController is a class that handles the user's input during a game of pong, and updates
 * the game data accordingly. It also spawns a subprocess (PongTask) to time each "game tick".
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class PongGameController implements EventHandler<KeyEvent>, Initializable{
	
	//FXML Elements
	
	@FXML Text leftScoreTxt;
	@FXML Text rightScoreTxt;
	@FXML Text gameOverTxt;
	@FXML Rectangle paddleL;
	@FXML Rectangle paddleR;
	@FXML Circle ball;
	
	/**
	 * NOTE: The "ball" FXML element is FocusTraversible.
	 * 
	 * This was the only way that the application's KeyListener would work, as setting the AnchorPane to
	 * FocusTraversible still did not result in the application window holding user focus, and none of
	 * the key events would come through. I also tried to make the scene itself FocusTraversible, but that
	 * didn't work either.
	 * 
	 * Anyways, this shouldn't really pose any issues down the line.
	 */
	
	//Game elements (for keeping track of game data)
	private Paddle left;
	private Paddle right;
	private Ball ballObj;
	
	private int leftScore;
	private int rightScore;
	private int speed;
	
	//Screen boundaries (for bound checking)
	private double leftBound;
	private double rightBound;
	private double upperBound;
	private double lowerBound;
	
	//Game logic thread
	private PongTask gameThread;
	
	/**
	 * Instantiates and initializes the objects needed to hold game data and spawns a PongTask instance to update objects on a timer.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Initialize player scores (difficulty)
		this.leftScore = 0;
		this.leftScoreTxt.setText("" + leftScore);
		
		this.rightScore = 0;
		this.rightScoreTxt.setText("" + rightScore);
		
		//Initialize the values of our screen boundaries (for usage in moveBall() and raisePaddle()/lowerPaddle())
		final double WINDOWS_APP_BORDER_WIDTH = 23.6000061035156;
		
		this.leftBound = this.ball.getRadius();
		this.rightBound = (Main.stage.getWidth() - this.ball.getRadius() * 3);
		
		this.upperBound = this.ball.getRadius();
		this.lowerBound = (Main.stage.getHeight() - this.ball.getRadius() * 3) - WINDOWS_APP_BORDER_WIDTH;
		
		//Instantiate game element objects to hold game data (x, y, and speed of all game elements).
		this.left = new Paddle();
		this.right = new Paddle();
		this.ballObj = new Ball();
		
		//Initialize game element objects and FXML elements with the correct starting coordinates.
		this.initRound();
		
		//Spawn game logic thread, passing the current controller instance as a parameter to facilitate a non-static reference frame
		this.gameThread = new PongTask(this);
		this.gameThread.start();
		
	}
	
	/**
	 * Handles the user's keyboard input.
	 * 
	 * 'W' and 'S' move the left paddle up and down respectively.
	 * 'I' and 'K' move the right paddle up and down respectively.
	 * 'SPACE' checks the current score, and either ends or continues the game accordingly.
	 * 'ESCAPE' forcefully ends the game.
	 */
	@Override
	public void handle(KeyEvent event) {
		
		switch(event.getCode().toString()) {
			
			case "W":
				this.movePaddle("paddleL", this.left.getSpeed());
				break;
				
			case "S":
				this.movePaddle("paddleL", this.left.getSpeed() * -1);
				break;
				
			case "I":
				this.movePaddle("paddleR", this.right.getSpeed());
				break;
				
			case "K":
				this.movePaddle("paddleR", this.right.getSpeed() * -1);
				break;
				
			case "SPACE":
				
				//If either player scores 10 points, trip a "game over"
				if (this.leftScore > 9 || this.rightScore > 9) this.endGame();
				else this.startRound();
				
				break;
				
			case "ESCAPE":
				this.endGame();
				break;
		
		}
		
	}
	
	/**
	 * Moves a given paddle up or down by a given distance.
	 * 
	 * @param fxID id of the paddle being moved
	 * @param distToMove the distance to move the paddle (+ = up, - = down)
	 */
	public void movePaddle(String fxID, double distToMove) {
		
		//Load the paddle being moved
		Rectangle curPaddle = (Rectangle) Main.stage.getScene().lookup("#" + fxID);
		Paddle curPaddleObj = (fxID.equals("paddleL")) ? this.left : this.right;
		
		//Fetch current Paddle y value and width
		double curY = curPaddle.getLayoutY();
		double height = curPaddle.getHeight();
		
		//Check that moving the paddle any further will not cross the upper or lower screen boundary
		if((curY - distToMove) + (height / 2) >= upperBound && (curY - distToMove) + (height / 2) <= lowerBound) {
			
			//Calculate the new y value, then update the game element object and FXML element to match
			curPaddleObj.setY(curY - distToMove);
			curPaddle.setLayoutY(curY - distToMove);
			
		}
		
	}
	
	/**
	 * Updates the speed of the ball or resets the game state depending on whether or not any collisions have occured between the ball
	 * and the screen boundaries or paddles, then updates the position of the ball depending on its speed.
	 */
	public void doGameTick() {
		
		//Used to increase readability.
		double curX = this.ballObj.getX();
		double curY = this.ballObj.getY();
		double curXSpeed = this.ballObj.getXSpeed();
		double curYSpeed = this.ballObj.getYSpeed();
		
		double nextX = curX + curXSpeed;
		double nextY = curY + curYSpeed;
		double radius = this.ball.getRadius();
		
		final double lPadLBound = this.paddleL.getLayoutX() - radius;
		final double lPadUBound = this.paddleL.getLayoutY() - radius;
		final double lPadRBound = lPadLBound + this.paddleL.getWidth() + radius * 2;
		final double lPadBBound = lPadUBound + this.paddleL.getHeight() + radius * 2;
		
		final double rPadLBound = this.paddleR.getLayoutX() - radius;
		final double rPadUBound = this.paddleR.getLayoutY() - radius;
		final double rPadRBound = rPadLBound + this.paddleR.getWidth() + radius * 2;
		final double rPadBBound = rPadUBound + this.paddleR.getHeight() + radius * 2;
				
		//Check if the ball is crossing the left or right borders of the stage, and award points to respective player if so.
		if(nextX < this.leftBound) {
			
			//Award points and update display
			this.rightScore++;
			this.rightScoreTxt.setText("" + this.rightScore);
			
			//Reset game elements and return
			this.initRound();
			return;
			
		} else if(nextX > this.rightBound) {
			
			//Award points and update display
			this.leftScore++;
			this.leftScoreTxt.setText("" + this.leftScore);
			
			//Reset game elements and return
			this.initRound();
			return;
		}
		
		//Check if the ball is about to cross the upper or lower borders of the stage, and flip the ball's travel direction if so.
		if(nextY < this.upperBound || nextY > this.lowerBound ) {
			
			curYSpeed *= -1;
			this.ballObj.setYSpeed(curYSpeed);
			
		}
		
		//Check if the ball is about to collide with the left paddle, increasing and inverting the ball's travel speed if so.
		if(nextX <  lPadRBound && nextX > lPadLBound 
		&& nextY < lPadBBound && nextY > lPadUBound) {
						
			//If the ball is already past the right bound (thus the collision must be with the TOP or BOTTOM of the paddle), flip and increase vertical speed.
			if(curX < lPadRBound) {
				
				//Flip direction of vertical travel
				curYSpeed *= -1;
				
				//Increments speed by 1, depending on sign
				curXSpeed += (curXSpeed > 0) ? 1 : -1;
				curYSpeed += (curYSpeed > 0) ? 1 : -1;
				this.speed++;
				
				//Update speed held in game element object
				this.ballObj.setXSpeed(curXSpeed);
				this.ballObj.setYSpeed(curYSpeed);
				
			}
			
			//Otherwise, collision is occurring with the LEFT or RIGHT side, so flip horizontal speed.
			else {
				
				//Flip direction of horizontal travel
				curXSpeed *= -1;
				
				//Increments speed by 1, depending on sign
				curXSpeed += (curXSpeed > 0) ? 1 : -1;
				curYSpeed += (curYSpeed > 0) ? 1 : -1;
				this.speed++;
				
				//Update speed held in game element object
				this.ballObj.setXSpeed(curXSpeed);
				this.ballObj.setYSpeed(curYSpeed);
				
			}
			
		}
		
		//Check if the ball is about to collide with the right paddle, increasing and inverting the ball's travel speed if so.
		else if(nextX >  rPadLBound && nextX < rPadRBound 
			 && nextY < rPadBBound && nextY > rPadUBound) {
			
			//If the ball is already past the left bound (thus the collision must be with the TOP or BOTTOM of the paddle), flip and increase vertical speed.
			if(curX > rPadLBound) {
				
				//Flip direction of vertical travel
				curYSpeed *= -1;
				
				//Increments speed by 1, depending on sign
				curXSpeed += (curXSpeed > 0) ? 1 : -1;
				curYSpeed += (curYSpeed > 0) ? 1 : -1;
				this.speed++;
				
				//Update speed held in game element object
				this.ballObj.setXSpeed(curXSpeed);
				this.ballObj.setYSpeed(curYSpeed);
				
			}
			
			//Otherwise, collision is occurring with the LEFT or RIGHT side, so flip horizontal speed.
			else {
				
				//Flip direction of horizontal travel
				curXSpeed *= -1;
				
				//Increments speed by 1, depending on sign
				curXSpeed += (curXSpeed > 0) ? 1 : -1;
				curYSpeed += (curYSpeed > 0) ? 1 : -1;
				this.speed++;
				
				//Update speed held in game element object
				this.ballObj.setXSpeed(curXSpeed);
				this.ballObj.setYSpeed(curYSpeed);
				
			}
			
		}
				
		//Updates Ball object's coordinates
		this.ballObj.setX(nextX);
		this.ballObj.setY(nextY);
		
		//Updates Ball object's respective FXML element to match
		this.ball.setLayoutX(nextX);
		this.ball.setLayoutY(nextY);
		
	}
	
	/**
	 * Sets all game object's position and speed to initial values.
	 * If the game is over (based on player scores), then notify the users.
	 */
	public void initRound() {
		
		//If game is over (either player has scored 10 points) let the user know
		if(this.leftScore > 9 || this.rightScore > 9) {
			this.gameOverTxt.setText("Game Over!\nPress Space to Continue.");			
		}
		
		//Initialize game data and FXML elements with the correct starting coordinates.
		else this.speed = 3;
		
		this.ball.setLayoutX(300);
		this.ball.setLayoutY(200);
		
		this.ballObj.setX( ball.getLayoutX() );
		this.ballObj.setY( ball.getLayoutY() );
		this.ballObj.setXSpeed(0);
		this.ballObj.setYSpeed(0);
		
		this.paddleL.setLayoutX(30);
		this.paddleL.setLayoutY(165);
		
		this.left.setX( paddleL.getLayoutX() );
		this.left.setY( paddleL.getLayoutY() );
		this.left.setSpeed(40);
		
		
		this.paddleR.setLayoutX(550);
		this.paddleR.setLayoutY(165);
		
		this.right.setX( paddleR.getLayoutX() );
		this.right.setY( paddleR.getLayoutY() );
		this.right.setSpeed(40);
		
	}
	
	/**
	 * Randomly generates a direction for the ball to move in, then gives it speed in that direction if it isnt already moving.
	 */
	public void startRound() {
		
		//Prevent speed resets during ongoing rounds
		if(this.ballObj.getXSpeed() != 0 || this.ballObj.getYSpeed() != 0) return;
		
		//Randomly generate starting direction
		Random rand = new Random();
		
		final int xDir = (rand.nextInt() % 2 == 0) ? 1 : -1;
		final int yDir = (rand.nextInt() % 2 == 0) ? 1 : -1;
		
		//Set Ball object's speed to the initial value and newly generate direction
		this.ballObj.setXSpeed( xDir * this.speed);
		this.ballObj.setYSpeed( yDir * this.speed);
		
	}
	
	/**
	 * Ends the timer subprocess, then switches to the Pong end screen.
	 */
	public void endGame() {
		
		//Trip the exit flag of the game logic thread, signalling it to end the loop in its run() function
		this.gameThread.tripExitFlag();
		
		try {
						
			//Loads the Pong end screen
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( getClass().getResource("../view/PongEnd.fxml") );
			
			AnchorPane layout = (AnchorPane) loader.load();
			Scene scene = new Scene(layout);
			
			//Sets the score values of the new scene
			((Text) scene.lookup("#p1Score")).setText("" + this.leftScore);
			((Text) scene.lookup("#p2Score")).setText("" + this.rightScore);
			
			//Switches to the new scene
			Main.stage.setScene(scene);
			Main.stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
