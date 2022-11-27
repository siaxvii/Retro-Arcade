/*
 * 
 * @author Leeza Mushtaq (shy277)
 *
 *
 */

package application.model;


public class SnakePlayer {
//makes every attribute of the PLAYER object
	public String UN = new String(); 
	public int SCORE; 
   
	public SnakePlayer(String UN, int SCORE){
		//assigns items in object to entered input 
		this.UN = UN;//the user name
		this.SCORE = SCORE;//the user's score 
	}
	//the function below will check if the player entered something for their name or not. The name cannot be blank
	public static SnakePlayer authenticate(String UN) {
		SnakePlayer player = null;
		 
		if(UN.isEmpty() || UN.equals(" ")) { 
			return null;//null if blank
		}
		else {
			player = new SnakePlayer(UN, 0);
			return player;//creates new player object with name if not blank/is valid
		}

	}//end authenticate
	
	public String toString() {//no parameters
	      return "this is:" + this.UN + "\n";//returns string for output
	   }
//GETTERS and SETTERS====================================================================
	public String getUN() {//returns username, no paras
		return UN;
	}

	public void setUN(String uN) {//sets username, returns nothing
		UN = uN;
	}

	public int getSCORE() {//returns score, no paras
		return SCORE;
	}

	public void setSCORE(int sCORE) {//sets score, returns nothing 
		SCORE = sCORE;
	}
	
}//end class Player
