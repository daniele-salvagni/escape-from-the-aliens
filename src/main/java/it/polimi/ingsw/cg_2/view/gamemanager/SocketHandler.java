package it.polimi.ingsw.cg_2.view.gamemanager;

import java.net.Socket;

/**
 * This abstract class contain a basic structure to handle a socket connection. Implements
 * Runnable for multithreading.
 */
abstract class SocketHandler implements Runnable {

    private final Socket socket;

    /**
     * Abstract constructor for a new SocketHandler.
     *
     * @param socket the Socket connection
     */
    SocketHandler(Socket socket) {

        this.socket = socket;
    }

    /**
     * Get the Socket connection.
     *
     * @return the Socket connection
     */
    protected Socket getSocket() {

        return socket;

    }

}
