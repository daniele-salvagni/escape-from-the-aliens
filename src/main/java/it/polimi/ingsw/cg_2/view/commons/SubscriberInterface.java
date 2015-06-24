package it.polimi.ingsw.cg_2.view.commons;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This remote interface is used by the publisher component to dispatch message to its
 * subscribers.
 */
public interface SubscriberInterface extends Remote {

    void dispatchMessage(BroadcastMsg message) throws RemoteException;

}
