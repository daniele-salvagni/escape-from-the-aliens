# Map Files #

The official supported format for map files is *.PNG* (but also other formats may work), it is mandatory not to use lossy compression. We decided to use pixels inside an image file over other solutions like *XML* or *Plain Text* file simply because hexagonal maps are hard to understand. By using 2x2px cells and shifting down each even column by 1px it is very easy to understand cell adjacency, giving an overview of the map and the possibility for anyone to edit them with the most basic image editing software. It could also be helpful in the case of the implementation of a random map generator to have an overview of the generated maps without the need of a graphical interface.

Every standard **RGB** color is supported, however we suggest not to use *Black* as it could be mistaken with margins by other people editing the maps (but the way it will still work).

The ZoneHelper class is able to support every map dimension, however the implementation of this game will be fixed to a 23x14 cells size like the non-digital version.

In this folder is possible to find a template file containing the mask and the color palette used by this game to generate new maps.

