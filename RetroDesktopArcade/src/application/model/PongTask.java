package application.model;

import application.controller.PongGameController;

/**
 * PongTask is a class intended to handle the timing of each "game tick" (updates to the game data
 * occuring every 20 ms) during Pong. It holds a reference to the Controller instance that invoked
 * it, and has a function to safely shut down the game by tripping a flag that controls the loop.
 * 
 * @author Xander Wilson (ssr515)
 * UTSA CS 3443 - Semester Project
 * Fall 2022
 */
public class PongTask extends Thread{

	private PongGameController parent;
	private boolean exitFlag;
	
	/**
	 * Constructor for PongTask. Needs the PongGameController instance that invoked it to properly
	 * facilitate data transfer via non-static method calls.
	 * 
	 * @param parent
	 */
	public PongTask(PongGameController parent) {

		this.parent = parent;
		this.exitFlag = false;
		
	}

	/**
	 * Runs the doGameTick() function every 20 milliseconds, which first updates the speed of the
	 * ball depending on whether or not any collisions have occured between it and the screen
	 * boundaries or paddles, then updates the position of the ball depending on its speed.
	 */
	public void run() {
		
		while(!exitFlag) {
			
			try {
				
				parent.doGameTick();
				Thread.sleep(20);
				
			} catch (InterruptedException e) {
				
				exitFlag = true;
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	/**
	 * Trips the flag controlling whether or not the main loop should continue.
	 */
	public void tripExitFlag() {
		this.exitFlag = true;
	}
	
}
