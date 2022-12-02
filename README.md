## Retro Desktop Arcade

Retro Desktop Arcade consists of the two popular retro games Snake and Pong, condensed into an easy to access application for those who want to relieve stress or play some low commitment games to pass the time.

## Known Issues

##### Pong:

-  Moving the paddle directly onto the ball will not affect the ball's direction.

	This is a result of the way paddle movement was implemented (incremental, rather than continuous), and is fully expected. In the future we hope to minimize the frequency of this occurence by adjusting the width of the paddles, but given the nature of the problem it will continue to exist unless we switch to a continuous system of movement.

##### Snake:

-  Running into the walls does not immediately end the game.

    This is a very simple issue, and we plan to fix this by adjusting some numbers in the boundary checks.
    
- Food/Snake objects are able to initialize/travel over the text

    This is another rather simple issue, which we plan to fix by adjusting the FXML file for snake's game loop. Adjusting the Snake game's logic to apply to a smaller pane would allow us to wrap the game board in another, cosmetic pane, on which we can place the text objects. This way, the snake and food objects will be prevented from covering up the text.  
    
## Contributors

Lasya Yakkala\
Leeza Mushtaq\
Xander Wilson\
Alex Lemire
