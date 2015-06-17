package it.polimi.ingsw.cg_2.messages.responses;

/**
 * A response message for the MoveAction, contains information about the new
 * position.
 */
public class MoveResponse implements ResponseMsg {

    private final String coordinate;
    private final String sectorType;

    /**
     * Create a new response message for a MoveAction.
     *
     * @param coordinate the new sector coordinate (COL:ROW)
     * @param sectorType the new sector type (SAFE/DANGEROUS/HATCH)
     */
    public MoveResponse(String coordinate, String sectorType) {

        this.coordinate = coordinate;
        this.sectorType = sectorType;

    }

    /**
     * Get the new coordinate (COL:ROW)
     *
     * @return the new coordinate
     */
    public String getCoordinate() {

        return coordinate;

    }

    /**
     * Get the new sector type (SAFE/DANGEROUS/HATCH)
     *
     * @return the new sector type
     */
    public String getSectorType() {

        return sectorType;

    }

}
