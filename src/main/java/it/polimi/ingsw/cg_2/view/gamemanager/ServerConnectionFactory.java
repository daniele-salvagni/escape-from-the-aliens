package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.net.Socket;
import java.rmi.registry.Registry;

/**
 *
 */
public class ServerConnectionFactory {

    private final static int RMI_PORT = 7777;
    private final static int SOCKET_SERVER_PORT = 1337;
    private final static int SOCKET_PUBLISHER_PORT = 1338;
    private final static int MAX_SUB_THREADS = 32;

    private RequestHandler requestHandler;
    private final Broker broker;
    private Registry registry;
    private SocketServer server, publisher;


    public ServerConnectionFactory() {

        broker = new Broker();

    }

}
