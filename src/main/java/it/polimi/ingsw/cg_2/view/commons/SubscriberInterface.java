package it.polimi.ingsw.cg_2.view.commons;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 */
public interface SubscriberInterface extends Remote {

    void dispatchMessage(BroadcastMsg message) throws RemoteException;

}
