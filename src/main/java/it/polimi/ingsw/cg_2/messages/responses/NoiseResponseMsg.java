package it.polimi.ingsw.cg_2.messages.responses;


public class NoiseResponseMsg implements ResponseMsg {

    private final String coordinate;

    public NoiseResponseMsg(String coordinate) {

        this.coordinate = coordinate;

    }

    public String getCoordinate() {

        return coordinate;
    }
}
