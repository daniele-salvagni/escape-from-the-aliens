package it.polimi.ingsw.cg_2.view.gameplayer;

/**
 *
 */
public abstract class PlayerConnectionFactory {

    private final ViewUpdater viewUpdater;
    private final Subscriber subscriber;

    private Token token;

    public PlayerConnectionFactory(ViewUpdater viewUpdater) {

        this.viewUpdater = viewUpdater;
        subscriber = new Subscriber(viewUpdater);

        token = null;

    }

    public void setupConnection() {



    }




}
