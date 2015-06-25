package it.polimi.ingsw.cg_2.view.commons;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This remote interface is used by the publisher component to dispatch messages to a
 * subscriber.
 */
public interface SubscriberInterface extends Remote {

    /**
     * Dispatch a message to a subscriber.
     *
     * @param message the message to dispatch
     * @throws RemoteException if there was a problem during data transfer
     */
    void dispatchMessage(BroadcastMsg message) throws RemoteException;

}
