# Escape from the Aliens #

This is the repository for the java implementation of the board game *Escape from Aliens in Outer Space*. This project is part of the Software Engineering 2015 course at Politecnico di Milano, group **CG_2**.

## General info ##

### Group members: ###

* Daniele Salvagni
* Simone Triventi


### Repository folders: ###

* **src/:** contains the java source code of the application.
* **docs/:** various resources unrelated to the source code like spare documentation and uml diagrams. 
* **docs/maps/:** a collection of maps, resources to manually draw them and a quick graphical documentation on the map import/export process.

## Usage info ##

### Start the game manager (server) ###

 In order to play you first need to start the game manager, to do that run `src/main/java/it/polimi/ingsw/cg_2/view/gamemanager/GameManager.java`, it will start automatically and wait for incoming RMI or Socket connections.

### Start the game player (client) ###

To start a game player run `src/main/java/it/polimi/ingsw/cg_2/view/gameplayer/GamePlayer.java`, you will then be prompted to choose between **RMI** or **Socket** connection:

* Insert `RMI` to use Remote Method Invocation
* Insert `SOCKET` to use a Socket connection

#### Waiting rooms ####

When the first player connects to the server, a new waiting room will be created, following players will be automatically placed in the same waiting room. When at least two people joined the same waiting room a countdown of 15 seconds starts: if the countdown does reach 0 a new game will be created, if someone else join the room during the countdown it will reset to 15 seconds. When the room reaches the maximum of 8 players the game will start instantly.

The first player (the one that created the waiting room) is allowed to **change the default map** (`GALVANI`) before the start of the game by using the command: `map <mapname>` where `<mapname>` can be one of the following:

    ASCESA, BALENA, CAVOUR, DILEMMA, EN_GARDE, FERMI,
    FRENZY, GALILEI, GARIBALDI, GALVANI, MACHIAVELLI,
    MORGENLAND, SINISTRA, SOCRATES, SOUND_OF_SILENCE

The game manager supports multiple concurrent games. When a game starts new incoming players will be put in another waiting room, and a new game will start with the same rules as before.

### Game commands ###

The following command will be available only for the waiting room creator and before the start of the game:

* `map <map>` - Change the default game map `GALVANI` to a new one (e.g. `map BALENA`).

The following command will be available before and after starting a game:

* `chat <message>` - Send a text `<message>` to all players subscribed to the game (e.g. `chat hello guys!`).

The following commands will be available during a game, based on the specific [game rules](http://www.escapefromthealiensinouterspace.com/pdf/manuale_eng_black_divise.pdf):

* `get info` - Display **private** informations about the player, such as: the race (alien or human), the rank, the current position, the held and activated items (if any). You should use this command at the start of the game to know the information about your character.
* `move <L:XX>` - Move to the specified sector, where `L` is a letter from A to Z and `XX` a number from 1 to 99 (e.g. `move S:22`).
* `attack` - Perform an attack in the current sector.
* `draw` - Draw a sector card (when in a DANGEROUS sector, only if you don't attack), you could also found an item by doing this.
* `escape` - When in an HATCH sector, try to escape.
* `noise <L:XX>` - Make a noise in the specified sector, only when you found a DECEPTION card (e.g. `noise D:7`).
* `pass` - Pass the turn to the following player.
* `use <item> [L:XX]` - Use a specific item, where `item` can be onf of the following: `adrenaline`, `attack`, `sedatives`, `teleport`, `spotlight`. In the case of `spotlight` you must provide an additional parameter to choose where to use it (e.g. `use spotlight A:13`).
* `get log` - Display the log from the server of the past **public** actions executed by the players.

Any other command is not valid.

## Implementation details ##

The whole architecture of the application follows the **MVC** pattern, the model contains all the information to store the state of the game manager, the controller manages game initialization and rules, the view is composed by two parts: a remote view on the game manager connected to the local view on the game player to remotely interact with the controller.

### Model ###

![Model UML Diagram](http://i.imgur.com/YoMGxLn.png)

The model is pretty straightforward, the various decks of cards are implemented with a generic `Deck` class that automatically shuffle cards in a transparent way when they are finished, some standard creational pattern have been used to instantiate various classes and Zones are loaded from files.

The constraints of the hexagonal maps are modeled by implementing some of the algorithms on *Amit Patel*'s blog ([Hexagonal Grids](http://www.redblobgames.com/grids/hexagons/)).

#### Map encoding ####

As hexagonal maps are hard to understand, we decided not to use Plain Text, XML files or external tools, but we used something quick and accessible to everyone to edit them or create new ones, images.

The official supported format for map files is .PNG (but also other formats may work), it is mandatory not to use lossy compression. By using 2x2px cells and shifting down each even column by 1px it is very easy to understand cell adjacency, giving an overview of the map and the possibility for anyone to edit them with the most basic image editing software. It could also be helpful in the case of the implementation of a random map generator to have an overview of the generated maps without the need of a graphical interface.

Every standard **RGB** color is supported, however we suggest not to use Black as it could be mistaken with margins by other people editing the maps (but the way it will still work).

The ZoneHelper class is able to support every map dimension, however the implementation of this game will be fixed to a 23x14 cells size like the non-digital version. Smaller maps should be handled correctly so in the case of switching to bigger maps older ones should still be compatible.

Here follows a graphical representation of the conversion process from human to computer readable and vice versa, notice how hard it is to understand neighbors in the version with 1px per sector:

![Image processing](http://i.imgur.com/INfo0dU.png)

[Vertical Version](http://i.imgur.com/ueXg8D8.png)  
*The red circle shows that by shifting columns by half a sector adjacencies becomes clear as every sector has 6 neighbors instead of 4 (or 8 considering diagonals).*

##### Color Palette #####

| Color         | Sector                             |
| ------------- | ---------------------------------- |
| **66CC66** *(Light Green)*    | Secure Sector      |
| **009966** *(Medium Green)*   | Dangerous Sector   |
| **993333** *(Red)*            | Escape Hatch       |
| **993399** *(Purple)*         | Alien Sector       |
| **0099CC** *(Cyan)*           | Human Sector       |
| **003333** *(Dark Green)*     | EMPTY, no sectors  |

##### Implemented Maps #####

![Implemented Maps](http://i.imgur.com/bFsiQvH.png)

### Controller ###

![Controller UML Diagram](http://i.imgur.com/53j1h0g.png)

*Yellow classes are the states of the State Machine, Red ones the command actions that act as transitions and in Green the classes used for the Visitor pattern.*

The controller has been implemented by merging multiple design patterns: the whole game is managed by a **FSM** (Finished State Machine) implemented with a **State pattern**, where each state is a **Singleton** which allows only some Actions to be executed. Actions are implemented very similarly to a **Command pattern**, and they determine the following state of the state machine based on their execution. After the execution, actions are also able to generate a pair of private response and public broadcast messages (with the help of the ResponseFactory class), to be handled respectively by the server and the publisher.

![State Machine Graph](http://i.imgur.com/NwGpHiM.png)
*The State Machine that handles actions and turn sequences.*

Actions are generated by a **Factory** that uses the **Visitor Pattern** to generate the appropriate action based on the type of the request coming from the view. Every implementation  of `ActionRequestMsg` implements `ActionCreator`, which imposes the message to return itself to be visited, this allows the ActionFactoryVisitor to create the appropriate action without having to check for the concrete type of the request message. 

Here is also an UML diagram that shows the messages alongside the controller (lot-of-arrows-warning): [Controller UML Diagram with messages](http://i.imgur.com/uvyq3rl.png)

### View ###

The view is separated in two parts, a remote view on the game manager and a local view on the game player to interact with the game manager. Communication between them can happen with both RMI or Socket based on the choice of each different client.

![View UML Diagram](http://i.imgur.com/AMy2huX.png)

#### Communication ####

Client-Server communication uses a **request-response** communication pattern, while game updates are sent by a broker following the **publisher-subscriber** pattern.

When a game needs to send an update to its clients, it sends a publish request to the broker with a specific topic for that game. Those publish requests will be put in a **queue** which will then be dispatched to the clients that subscribed to that topic when possible.

Every message (public or private) sent from the server to the client also contains a visit method from the **Visitor Pattern** so that the clients can display the displayed information without having to check for the concrete type of the message. This is useful also to implement different visitors for different types of interface (e.g. **CLI** or **GUI**).

The same communications pattern are transparently implemented for both **RMI** and **Socket**, thanks to polymorphism you don't need to know which one you are using to "talk" with the game manager.
