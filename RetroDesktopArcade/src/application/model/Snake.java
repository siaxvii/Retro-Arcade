/*
 * 
 * @author Alex Lemire - BTJ926
 * 
 * This file is responsible for handling all components related to the Snake object
 * 
 */


package application.model;

public class Snake {
	
	public double xPos;		//X coordinates for the snake head
	public double yPos;		//Y coordinates for the snake head
	public int score;		//Running score for that particular player
	
	
	//Constructor for the Snake object, initializes the x and y coordinates as well as passes on the score
	public Snake(double xPos, double yPos, int score) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.score = score;
	}

	
	//All the getters and setters for the snake object
	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
	}
	
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
