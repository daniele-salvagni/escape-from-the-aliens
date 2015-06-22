package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

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

        token = getRequestHandler().connect();

        getBrokerInterface().subscribe(getSubscriber(), token);

    }

    public abstract RequestHandler getRequestHandler();

    public abstract BrokerInterface getBrokerInterface();

    public void setToken(Token token) {

        this.token = token;

    }

    public Token getToken() {

        return token;

    }

    public ViewUpdater getViewUpdater() {

        return viewUpdater;

    }

    public Subscriber getSubscriber() {

        return subscriber;

    }



}
