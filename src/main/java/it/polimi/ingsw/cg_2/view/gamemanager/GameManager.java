package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.controller.GamesController;

/**
 * Entry point to start a GameManager.
 */
public class GameManager {

    public static void main(String[] args) {

        ServerConnectionFactory serverInitializer = new ServerConnectionFactory();
        PublisherInterface publisherInterface = serverInitializer.getPublisherInterface();

        GamesController mainController = new GamesController(publisherInterface);

        serverInitializer.setRequestHandler(mainController);
        serverInitializer.startServers();

        System.out.println("Game manager started, listening for RMI and Socket " +
                "connections.");

    }

}
