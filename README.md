# CS441_Assignment5
# Garbage Plate Clicker
A game modeled after the infamous cookie clicker, known for getting thousands of middle schoolers addicted to staring at their screens and 
watching numbers go up.
The game, of course, has multiple screens:
## The main menu
Consists of the title, as well as three buttons: the play button, the settings button, and the "how to play" button, which brings the 
player to the game screen, settings screen, and instructions screen, respectively.
## The main game
Where all the action happens. There is a large image of a garbage plate taking up the entire left half of the screen, and a counter 
displaying the total amount of plates clicked so far. Click the garbage plate and the number goes up! The player can click buttons to go 
to the shop, the settings screen, and the instructions screen. There is a button in the top left corner to take the player back to the main menu
## The shop screen
Here, the player can use the garbage plates they've acquired so far to purchase means of earning them automatically/faster. The cheapest 
option, costing 75 garbage plates (GP), increases the number of GP acquired per click by one. For 100GP, the player can hire a chef, and 
earn 1 GP per second (GPPS). For 500GP, a new kitchen can be installed, and the GP output increases by 6GPPS. 10000GP allows the player to 
open a new restaurant (pictured as an Olive Garden, the best place to get a good plate), worth an extra 150GPPS. The screen shows the 
amount of plates the player has in the bottom right (still updating if the player has any chefs, kitchens, or restaurants) as well as a 
prompt to be directed to "The Ultimate Purchase," and a button to go back to the clicking screen
## Settings
Change the background color of the game. There are four different colors to choose from. Riveting, I know. If I had the time, I would have 
changed the item to be collected, e.g. cookies for the "sweet" style. I will probably do this over the summer, to be honest, as I would 
like to have a fully refined version of this game.
## Instructions
Basic text displaying how to play. Includes interactive buttons to go to the main game screen (which also increases the GP count by the 
click amount), the settings screen, and "The Ultimate Purchase"
## Victory
A victory screen to let the player know that they have won. The player wins by making "The Ultimate Purchase." They can now reset the game, or exit.
## "The Ultimate Purchase"
A secret surprise.

# Struggles
This was an interesting one. I uploaded a free to use .ttf file for my game font, and I wanted to generate it at multiple sizes. It turns 
out, the classes available for use provided by LibGdx that do that need to be memory-controlled, i.e. the texts need to be created only 
once and disposed only once. Originally, I had one font object, and whenever I wanted the size to change, I would programmatically change 
it within the render() method. Turns out, this causes a MASSIVE MASSIVE MEMORY LEAK, and in my sleep-deprived stupor I assumed this was 
just because my laptop was slow, since LibGdx does not let you know that there is a MASSIVE MASSIVE MEMORY LEAK in your program. This 
took me serveral hours to realize.
