package it.polimi.ingsw.cg_2.model.map;

/**
 * This class enumarates the premade maps for this game, for each map it is
 * possible to get the name of the file on disk containing the map.
 */
public enum ZoneType {

    EN_GARDE("map-en-garde.png"), FERMI("map-fermi.png"), FRENZY(
            "map-frenzy.png"), GALILEI("map-galilei.png"), GALVANI(
            "map-galvani.png"), MACHIAVELLI("map-machiavelli.png"), SINISTRA(
            "map-sinsitra.png");

    private final String fileName;

    ZoneType(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the name of the file on disk containing the map.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

}
