package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.rmi.RemoteException;

/**
 *
 */
public class Subscriber implements SubscriberInterface {

    protected ViewUpdater viewUpdater;

    Subscriber(ViewUpdater viewUpdater) {

        this.viewUpdater = viewUpdater;

    }

    @Override
    public void dispatchMessage(BroadcastMsg message) throws RemoteException {

        viewUpdater.update(message);

    }

}
