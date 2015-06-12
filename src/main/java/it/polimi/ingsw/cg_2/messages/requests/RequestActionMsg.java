package it.polimi.ingsw.cg_2.messages.requests;

import it.polimi.ingsw.cg_2.controller.actions.ActionCreator;


public abstract class RequestActionMsg implements ActionCreator {

    String token;

    public RequestActionMsg(String token) {

        this.token = token;

    }

    public String getToken() {

        return token;

    }

}
