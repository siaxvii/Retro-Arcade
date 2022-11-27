/*
 * 
 * @author Lasya Yakkala - IQY403
 * 
 * This file is responsible for handling all components related to the Snake game, has the main snake game logic
 * 
 */

package application.controller;

//All import statements
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import application.Main;
import application.model.Food;
import application.model.Snake;
import application.model.SnakePlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SnakeController implements Initializable {

	//FXML Components initialized
	@FXML AnchorPane pane;
    @FXML Button start;
    
    @FXML Text curScore;
    @FXML Text gameOver;
    
	SnakePlayer player;
	
	//Holds highest 3 score values of the particular player
	public static int sortedScore[] = new int[]{0,0,0};
	
	//snake body part will be 25x25
    public int size = 25;

    //Initializing head and tail of the snake
    public Rectangle head;
    public Rectangle tail;
    
    double xPos;
    double yPos;

    //Food object instantiated
    Food f;

    //Direction of snake
    public enum Direction {UP, DOWN, RIGHT, LEFT}
    
    public int score = 0;
    
	//Ensures that the initialized direction of the snake is right
    Direction dir;

    //List to keep track of the positions of the snake
    public List<Snake> pos = new ArrayList<>();
    
    //ArrayList to keep track of the body (tail parts being tacked on to the end of the snake head)
    public ArrayList<Rectangle> body = new ArrayList<>();

    public int ticks; //how many times the snake has moved
    public boolean changeDir;	//Ensures the snake can actually change direction
    
    Timeline time;

    
    //Initializes all the FXML components, main game logic contained in Time line function
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	player = SnakeNameController.player;//player object to hold player data like score and name 
        
    	//Time line that runs the game every time the KeyFrame is called, every 0.2 seconds
    	//uses a lambda expression to call an event which holds the current position of the snake head, moving the snake head once
    	//for loop moves all the body parts consequently to the proper positions, following the head
    	time = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
            pos.add(new Snake(head.getX() + xPos, head.getY() + yPos, score));
            moveHead(head);
            for (int i = 1; i < body.size(); i++) {
                moveTail(body.get(i), i);
            }
            
            changeDir = true;
            
            ticks++;
            eat();
            
            //ends the game and switches to the SnakeEnd screen, stops the timer
            if(gameOver()){
                time.stop();
                
                try {
    				
    				FXMLLoader loader = new FXMLLoader();
    						
    				//sets the location of the view to User View
    				loader.setLocation(getClass().getResource("../view/SnakeEnd.fxml"));
    						
    				Scene scene = new Scene(loader.load());
    						
    				Main.stage.setScene(scene);
    						
    				Main.stage.show();
    						
    					
    			} catch(Exception x) {
    				x.printStackTrace();
    			}
            }
            
        }));
        
        //creates randomized food square to show up on the Anchor pane
        f = new Food(-100,-200, pane, size);
        
    }
    
    //Initiates all variables to start a new game
    @FXML
    public void handle(MouseEvent event) {
    	//Sets the start button to a black background once pressed, giving the illusion that it is invisible
    	Shadow effect = new Shadow();
    	start.setEffect(effect);
    	ticks = 0;
        
        body.clear();
        pos.clear();
        
        for (Rectangle s : body) {
            pane.getChildren().remove(s);   
        }

        //Sets new rectangle objects for the tail and head of the snake
        head = new Rectangle(100, 100, size, size);
        tail = new Rectangle(head.getX() - size, head.getY(), size, size);
        
        xPos = head.getLayoutX();
        yPos = head.getLayoutY();
        
        dir = Direction.RIGHT;
        changeDir = true;
        
        f.foodMove();
        
        //Adds the head and tails of the snake
        body.add(head);
        body.add(tail);
        
        head.setFill(Color.PALEGREEN);
        tail.setFill(Color.GREEN);
        
        time.setCycleCount(Animation.INDEFINITE);
        time.play();

        //Adds all components onto the AnchorPane
        pane.getChildren().addAll(head, tail);
    }

   

    //Change position with key pressed, connected to the start button
    @FXML
    public void move(KeyEvent event) {
        if(changeDir){
            if (event.getCode().equals(KeyCode.UP) && dir != Direction.DOWN) {
                dir = Direction.UP;	//Sets the direction of the snake head to up
            } else if (event.getCode().equals(KeyCode.DOWN) && dir != Direction.UP) {
                dir = Direction.DOWN; //Sets the direction of the snake head to down
            } else if (event.getCode().equals(KeyCode.LEFT) && dir != Direction.RIGHT) {
                dir = Direction.LEFT; ////Sets the direction of the snake head to left
            } else if (event.getCode().equals(KeyCode.RIGHT) && dir != Direction.LEFT) {
                dir = Direction.RIGHT;  //Sets the direction of the snake head to right
                
            //End the game when the player presses escape, switches to Snake End screen
            } else if(event.getCode().equals(KeyCode.ESCAPE)) {
            	try {
    				
    				FXMLLoader loader = new FXMLLoader();
    						
    				//sets the location of the view to User View
    				loader.setLocation(getClass().getResource("../view/SnakeEnd.fxml"));
    						
    				Scene scene = new Scene(loader.load());
    						
    				Main.stage.setScene(scene);
    						
    				Main.stage.show();
    						
    					
    			} catch(Exception x) {
    				x.printStackTrace();
    			}
            }
            
            
            changeDir = false;  //Makes it so that the snake is not able to change direction otherwise, continues moving the same direction
        }
    }


    //Snake head is moved in the direction specified
    public void moveHead(Rectangle snakeHead) {
        if (dir.equals(Direction.RIGHT)) {
            xPos = xPos + size;
            snakeHead.setTranslateX(xPos);
        } else if (dir.equals(Direction.LEFT)) {
            xPos = xPos - size;
            snakeHead.setTranslateX(xPos);
        } else if (dir.equals(Direction.UP)) {
            yPos = yPos - size;
            snakeHead.setTranslateY(yPos);
        } else if (dir.equals(Direction.DOWN)) {
            yPos = yPos + size;
            snakeHead.setTranslateY(yPos);
        }
    }
    

    //A specific tail is moved to the position of the head x game ticks after the head was there
    public void moveTail(Rectangle tail, int tailNum) {
        
    	double yPos = pos.get(ticks - tailNum + 1).getYPos() - tail.getY();
        double xPos = pos.get(ticks - tailNum + 1).getXPos() - tail.getX();
        
        tail.setTranslateX(xPos);
        tail.setTranslateY(yPos);
    }

    //New snake tail is created and added to the snake and the anchor pane
    public void addTail() {
    	//handles score related tasks
    	score++;	//Increments total score for that particular round for that player
    	player.setSCORE(score);
        curScore.setText("Score: " + String.valueOf(score));
    	
    	//New tail of the snake being added to the head
        Rectangle s = new Rectangle(body.get(1).getX() + xPos + size, body.get(1).getY() + yPos, size, size);
        s.setFill(Color.GREEN); //Sets the tail of the snake to be green
        body.add(s);
       
        //Adds it to the anchor pane
        pane.getChildren().add(s);
        
    }

	//Returns true if the game is over and returns false if not game over
    public boolean gameOver() {
        
    	//If the position of the snake is outside the boundaries, will trigger end of game protocol by returning true
    	if (xPos > 500 || xPos < -500 ||yPos < -300 || yPos > 300) {
            return true;
           
        //If the snake hits itself, will trigger end of game protocol by returning true
        } else if(snakeHit()){
            return true;
            
        }
        
		//Otherwise, returns false because the game is continuing
        return false;
    }

	//Returns true if snake hit itself, false otherwise
    public boolean snakeHit(){
        //If the snake hit is in the same position as a body part
    	int s = pos.size() - 1;
        
        if(s > 2){
            for(int i = s - body.size(); i < s; i++) {
                
				//If the snake hits itself, will return true
            	if(pos.get(s).getXPos() == (pos.get(i).getXPos()) && pos.get(s).getYPos() == (pos.get(i).getYPos())){
                    return true;
                }
            }
        }
		//Otherwise, snake has not hit itself and can continue the game
        return false;
    }

    //Authored by Alex Lemire
    //Logic for snake head eating a food item
    public void eat(){
    	//Checks if the snake head is at the same position as a food object on the anchor pane
        if(xPos + head.getX() == f.getPosition().getXPos() && yPos + head.getY() == f.getPosition().getYPos()){
        	//Checking that the food didn't spawn inside the snake
        	notInSnake();
        	
        	//If it is, then it will add onto the snake head
            addTail();
        }
        	//Sets the updated value of score on the screen as a snake eats a food item
            curScore.setText("Score: " + String.valueOf(score));
        
    }//end eat();

  //Authored by Alex Lemire
  //The food is inside the snake, returns true if the snake eats the food, false otherwise
    public boolean extendSnake(){
        
    	int x = pos.size();
    	
    	//If the food object is still inside the snake, it generates a new position for the food, returns true
        if(x > 2){
            for (int i = x - body.size(); i < x; i++) {
                //Checking if the food is in any positions of the snake head/tail
            	if(f.getPosition().getXPos() == (pos.get(i).getXPos()) && f.getPosition().getYPos() == (pos.get(i).getYPos())){
                	return true;
                    
                }
            }  
        }//end if
        
        //If the food is not in the snake, it leaves the food there, returns false
        return false;
    }
    
    
    //Ensures the food is not in the same position as the snake
    public void notInSnake(){
        f.foodMove();
        
        //While the food is inside the snake, it will generate a new random position for the next food object
        while (extendSnake()){
            f.foodMove();
        }
    }

	

    
}//end all