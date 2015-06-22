package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.rmi.RemoteException;

/**
 * Abstract class for a PlayerConnectionFactory.
 */
public abstract class PlayerConnectionFactory {

    private final ViewUpdater viewUpdater;
    private final Subscriber subscriber;

    private Token token;

    /**
     * Abstract class constructor for the PlayerConnectionFactory class.
     *
     * @param viewUpdater a view updater to display updates from the server
     */
    public PlayerConnectionFactory(ViewUpdater viewUpdater) {

        if (viewUpdater == null) {
            throw new IllegalArgumentException("ViewUpdater must not be null.");
        }

        this.viewUpdater = viewUpdater;
        subscriber = new Subscriber(viewUpdater);

        token = null;

    }

    /**
     * Setup a new connection to the game manager as a new client.
     */
    public void setupConnection() throws RemoteException {

        token = getRequestHandler().connect();

        getBrokerInterface().subscribe(getSubscriberInterface(), token);

    }

    /**
     * Get the remote request handler.
     *
     * @return the remote request handler
     */
    public abstract RequestHandler getRequestHandler();

    /**
     * Get the remote broker interface.
     *
     * @return the remote broker interface
     */
    public abstract BrokerInterface getBrokerInterface();

    public void setToken(Token token) {

        this.token = token;

    }

    /**
     * Get the token that identifies the client.
     *
     * @return the token that identifies the client
     */
    public Token getToken() {

        return token;

    }

    /**
     * Get the view updater.
     *
     * @return the view updater
     */
    public ViewUpdater getViewUpdater() {

        return viewUpdater;

    }

    /**
     * Get the Subscriber.
     *
     * @return the subscriber
     */
    public Subscriber getSubscriber() {

        return subscriber;

    }

    /**
     * Get the subscriber interface
     *
     * @return the subscriber interface
     */
    public SubscriberInterface getSubscriberInterface() {

        return (SubscriberInterface)subscriber;

    }



}
