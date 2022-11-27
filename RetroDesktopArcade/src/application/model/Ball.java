package application.model;

/**
 * Ball is a class intended to hold the data relevant to the ball in our Pong game implementation.
 * It holds the x and y coordinates of the ball, as well as the x and y speed in pixels.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class Ball {
	
	private double x;
	private double y;
	private double xSpeed;
	private double ySpeed;

	/**
	 * Constructor for the Ball class. Initializes x/y coordinates to 0, and x/y speed to 0.
	 */
	public Ball() {
		
		this.x = 0;
		this.y = 0;
		this.xSpeed = 0;
		this.ySpeed = 0;
		
	}

	/**
	 * @return the x coordinate of the ball
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y coordinate of the ball
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return the horizontal speed of the ball (pixels per movement)
	 */
	public double getXSpeed() {
		return xSpeed;
	}

	/**
	 * @return the vertical speed of the ball (pixels per movement)
	 */
	public double getYSpeed() {
		return ySpeed;
	}

	/**
	 * @param x the x coordinate of the ball
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @param y the y coordinate of the ball
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @param speed the horizontal speed of the ball (pixels per movement)
	 */
	public void setXSpeed(double speed) {
		this.xSpeed = speed;
	}

	/**
	 * @param speed the vertical speed of the ball (pixels per movement)
	 */
	public void setYSpeed(double speed) {
		this.ySpeed = speed;
	}

}
