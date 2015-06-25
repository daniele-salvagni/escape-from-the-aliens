package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.messages.Token;
import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ConnectionResponseMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.rmi.RemoteException;
import java.util.UUID;

/**
 *
 */
public class TESTCONTROLLER implements RequestHandler {

    @Override
    public Token connect() throws RemoteException {

        return new Token(UUID.randomUUID().toString());
    }

    @Override
    public ResponseMsg processRequest(RequestMsg request) throws RemoteException {

        return new ConnectionResponseMsg(new Token(UUID.randomUUID().toString()));

    }

}
