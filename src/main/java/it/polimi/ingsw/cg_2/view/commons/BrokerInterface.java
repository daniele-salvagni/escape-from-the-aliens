package it.polimi.ingsw.cg_2.view.commons;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.view.gameplayer.Subscriber;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 */
public interface BrokerInterface extends Remote {

    void subscribe(SubscriberInterface subscriber, Token token) throws RemoteException;

    void unsubscribe(SubscriberInterface subscriber) throws RemoteException,
            IllegalArgumentException;


}
