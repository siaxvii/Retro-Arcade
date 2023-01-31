## [Retro Desktop Arcade](https://www.youtube.com/watch?v=yU9HtyS0CTo)

[Retro Desktop Arcade](https://www.youtube.com/watch?v=yU9HtyS0CTo) consists of the two popular retro games Snake and Pong, condensed into an easy to access application for those who want to relieve stress or play some low commitment games to pass the time.

Watch video demo [here](https://www.youtube.com/watch?v=yU9HtyS0CTo)!

<img width="200" alt="image" src="https://user-images.githubusercontent.com/91913752/215720936-09dce359-7b1a-468b-a71a-64ecbefa4f46.png">       <img width="200" alt="image" src="https://user-images.githubusercontent.com/91913752/215721007-9baec189-e553-4e0b-b23e-9574e004d8ec.png"> <img width="200" alt="image" src="https://user-images.githubusercontent.com/91913752/215722791-de31a2e9-436d-48a5-acb1-cc4e71885973.png"> <img width="200" alt="image" src="https://user-images.githubusercontent.com/91913752/215721750-da43482b-742a-4621-95ad-66a7d3927184.png"> <img width="200" alt="image" src="https://user-images.githubusercontent.com/91913752/215721451-5c580ce0-c4c4-451b-9f09-b1d15430ee67.png"> <img width="200" alt="image" src="https://user-images.githubusercontent.com/91913752/215723024-7ed758af-504a-4467-89d9-09012d416626.png"> <img width="200" alt="image" src="https://user-images.githubusercontent.com/91913752/215721860-53e9b855-39a1-492b-8e26-022ecb02570b.png">   <img width="200" alt="image" src="https://user-images.githubusercontent.com/91913752/215722048-aca463c2-360b-4d22-a9c9-8a6bcbbf59b8.png">





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
