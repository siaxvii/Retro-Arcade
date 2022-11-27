package application.model;

/**
 * Paddle is a class intended to hold data relevant to the paddles in our Pong game implementation.
 * It holds the x and y coordinates of the paddle, as well as its vertical speed in pixels.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class Paddle {
	
	private double x;
	private double y;
	private double speed;

	/**
	 * Constructor for the Paddle class. Initializes x/y coordinates to 0, and x/y speed to 0.
	 */
	public Paddle() {
		
		this.x = 0;
		this.y = 0;
		this.speed = 0;
		
	}

	/**
	 * @return the x coordinate of the paddle
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y coordinate of the paddle
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return the vertical speed of the paddle (pixels per movement)
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @param x the x coordinate of the paddle
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @param y the y coordinate of the paddle
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @param speed the vertical speed of the paddle (pixels per movement)
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
