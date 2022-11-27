/*
 * 
 * @author Alex Lemire - BTJ926
 * 
 * This file is responsible for handling all components related to the food object
 * 
 */

package application.model;

//All of the import statements
import java.util.Random;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food {
	
	public Rectangle food;
	public Color c = Color.RED;
	public AnchorPane pane;
	
	public Snake pos;
	
	public Random rand = new Random();
	public int score = 0;
	public int size = 0;
	
	//Food object parameters include the position of the food object, the anchor pane it is on, as well as the size of the snake head
	public Food(double x, double y, AnchorPane pane, int size) {
		this.size = size;	//We want the size of the food object to be the same as the size of the snake head
		this.pane = pane;
		
		pos = new Snake(x, y, score);
		
		food = new Rectangle(pos.getXPos(), pos.getYPos(), size, size);
		food.setFill(c);	//We want the food to be red like an apple
		
		//Displays the food object on the anchor pane
		pane.getChildren().add(food);
		
	}
	
	
	public Snake getPosition() {
		return pos;
	}
	
	
	public void foodMove() {
		randFood();
	}
	
	
	public void randFood() {
		//Randomizes position of the food object being displayed on the screen
		int foodX = rand.nextInt(20);
		int foodY = rand.nextInt(15);
		
		//Sets coordinates of the food and snake head on the screen
		food.setX(foodX * size);
		pos.setXPos(foodX * size);
		
		food.setY(foodY * size);
		pos.setYPos(foodY * size);
	}
	
	
	
}
