package it.polimi.ingsw.cg_2.view.commons;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.view.gameplayer.Subscriber;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This remote interface handler the publisher-subscriber server component. It allows
 * clients to subscribe or unsubscribe from the broker.
 */
public interface BrokerInterface extends Remote {

    void subscribe(SubscriberInterface subscriber, Token token) throws RemoteException;

    void unsubscribe(SubscriberInterface subscriber) throws RemoteException,
            IllegalArgumentException;

}
