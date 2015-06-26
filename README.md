# Escape from the Aliens #

This is the repository for the java implementation of the board game *Escape from Aliens in Outer Space*. This project is part of the Software Engineering 2015 course at Politecnico di Milano, group **CG_2**.

### General info ###

#### Group members: ####

* Daniele Salvagni
* Simone Triventi


#### Repository folders: ####

* **src/:** contains the java source code of the application.
* **docs/:** various resources unrelated to the source code like spare documentation and uml diagrams. 
* **docs/maps/:** a collection of maps, resources to manually draw them and a quick graphical documentation on the map import/export process.

### Usage info ###

#### Start the game manager (server) ####

 In order to play you first need to start the game manager, to do that run `src/main/java/it/polimi/ingsw/cg_2/view/gamemanager/GameManager.java`, it will start automatically and wait for incoming RMI or Socket connections.

#### Start the game player (client) ####

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

#### Game commands ####

The following command will be available only for the waiting room creator and before the start of the game:

* `map <map>` - Change the default game map `GALVANI` to a new one (e.g. `map BALENA`).

The following command will be available before and after starting a game:

* `chat <message>` - Send a text `<message>` to all players subscribed to the game (e.g. `chat hello guys!`).

The following commands will be available during a game, based on the specific [game rules](http://www.escapefromthealiensinouterspace.com/pdf/manuale_eng_black_divise.pdf):

* `get info` - Display **private** informations about the player, such as: the race (alien or human), the rank, the current position, the held and activated items (if any). You should use this command at the start of the game to know the informations about your character.
* `move <L:XX>` - Move to the specified sector, where `L` is a letter from A to Z and `XX` a number from 1 to 99 (e.g. `move S:22`).
* `attack` - Perform an attack in the current sector.
* `draw` - Draw a sector card (when in a DANGEROUS sector, only if you don't attack), you could also found an item by doing this.
* `escape` - When in an HATCH sector, try to escape.
* `noise <L:XX>` - Make a noise in the specified sector, only when you found a DECEPTION card (e.g. `noise D:7`).
* `pass` - Pass the turn to the following player.
* `use <item> [L:XX]` - Use a specific item, where `item` can be onf of the following: `adrenaline`, `attack`, `sedatives`, `teleport`, `spotlight`. In the case of `spotlight` you must provide an additional parameter to choose where to use it (e.g. `use spotlight A:13`).
* `get log` - Display the log from the server of the past **public** actions executed by the players.

Any other command is not valid.
