package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.controller.GamesController;

/**
 *
 */
public class GameManager {

    public static void main(String[] args) {

        ServerConnectionFactory serverInitializer = new ServerConnectionFactory();
        PublisherInterface publisherInterface = serverInitializer.getPublisherInterface();

        GamesController testcontroller = new GamesController(publisherInterface);

        serverInitializer.setRequestHandler(testcontroller);
        serverInitializer.startServers();

        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }

}
