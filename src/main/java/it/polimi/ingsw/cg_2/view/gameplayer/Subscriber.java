package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.rmi.RemoteException;

/**
 * The SubscriberInterface implementation that will be exposed to the broker to publish
 * message to the client.
 */
public class Subscriber implements SubscriberInterface {

    protected ViewUpdater viewUpdater;

    /**
     * Create a new subscriber "handler".
     *
     * @param viewUpdater the view updater to display received broadcast messages
     */
    Subscriber(ViewUpdater viewUpdater) {

        this.viewUpdater = viewUpdater;

    }

    @Override
    public void dispatchMessage(BroadcastMsg message) throws RemoteException {

        viewUpdater.update(message);

    }

}
