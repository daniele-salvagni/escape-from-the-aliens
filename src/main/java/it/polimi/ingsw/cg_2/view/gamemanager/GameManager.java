package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.controller.GamesController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point to start a GameManager.
 */
public class GameManager {

    private static final Logger LOG = Logger.getLogger(GameManager.class.getName());

    public static void main(String[] args) {

        ServerConnectionFactory serverInitializer = new ServerConnectionFactory();
        PublisherInterface publisherInterface = serverInitializer.getPublisherInterface();

        GamesController mainController = new GamesController(publisherInterface);

        serverInitializer.setRequestHandler(mainController);
        serverInitializer.startServers();

        LOG.log(Level.INFO, "Game manager started, listening for RMI and Socket " +
                "incoming connections.");

    }

}
