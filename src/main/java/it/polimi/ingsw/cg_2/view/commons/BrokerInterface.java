package it.polimi.ingsw.cg_2.view.commons;

import it.polimi.ingsw.cg_2.messages.Token;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This remote interface handler the publisher-subscriber server component. It allows
 * clients to subscribe or unsubscribe from the broker.
 */
public interface BrokerInterface extends Remote {

    /**
     * Subscribe a client to the broker.
     *
     * @param subscriber the subscription handler
     * @param token the token of the client
     * @throws RemoteException if there was a problem during the connection
     */
    void subscribe(SubscriberInterface subscriber, Token token) throws RemoteException;

    /**
     * Unsubscribe a client from the broker.
     *
     * @param subscriber
     * @throws RemoteException if there was a problem during the connection
     * @throws IllegalArgumentException if the subscriber did not exist
     */
    void unsubscribe(SubscriberInterface subscriber) throws RemoteException,
            IllegalArgumentException;

}
