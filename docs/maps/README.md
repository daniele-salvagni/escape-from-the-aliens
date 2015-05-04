# Map Files #

We decided to use pixels inside an image file over other solutions like *XML* or *Plain Text* file simply because hexagonal maps are hard to understand.

The official supported format for map files is *.PNG* (but also other formats may work), it is mandatory not to use lossy compression. By using 2x2px cells and shifting down each even column by 1px it is very easy to understand cell adjacency, giving an overview of the map and the possibility for anyone to edit them with the most basic image editing software. It could also be helpful in the case of the implementation of a random map generator to have an overview of the generated maps without the need of a graphical interface.

Every standard **RGB** color is supported, however we suggest not to use *Black* as it could be mistaken with margins by other people editing the maps (but the way it will still work).

The ZoneHelper class is able to support every map dimension, however the implementation of this game will be fixed to a 23x14 cells size like the non-digital version. Smaller maps should be handled correctly so in the case of switching to bigger maps older ones should still be compatible.

In this folder is possible to find a template file containing the mask and the color palette used by this game to generate new maps.

### Image Processing ###

File *map-saving-process.png* contained in this folder:

![Image processing](http://i.imgur.com/lYzt2cx.png)


### Colors Palette ###

| Color         | Sector                             |
| ------------- | ---------------------------------- |
| **66CC66** *(Light Green)*    | Secure Sector      |
| **009966** *(Medium Green)*   | Dangerous Sector   |
| **993333** *(Red)*            | Escape Hatch       |
| **993399** *(Purple)*         | Alien Sector       |
| **0099CC** *(Cyan)*           | Human Sector       |
| **003333** *(Dark Green)*     | EMPTY, no sectors  |
