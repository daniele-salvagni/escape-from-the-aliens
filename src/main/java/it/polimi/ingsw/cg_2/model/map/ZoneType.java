package it.polimi.ingsw.cg_2.model.map;

/**
 * This class enumarates the premade maps for this game, for each map it is
 * possible to get the name of the file on disk containing the map.
 */
public enum ZoneType {

    ASCESA("map-ascesa.png"),
    BALENA("map-balena.png"),
    CAVOUR("map-cavour.png"),
    DILEMMA("map-dilemma.png"),
    EN_GARDE("map-en-garde.png"),
    FERMI("map-fermi.png"),
    FRENZY("map-frenzy.png"),
    GALILEI("map-galilei.png"),
    GARIBALDI("map-garibaldi.png"),
    GALVANI("map-galvani.png"),
    MACHIAVELLI("map-machiavelli.png"),
    SINISTRA("map-sinsitra.png"),
    SOCRATES("map-socrates.png");

    private final String fileName;

    ZoneType(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the name of the file on disk containing the map (not the file path).
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

}
