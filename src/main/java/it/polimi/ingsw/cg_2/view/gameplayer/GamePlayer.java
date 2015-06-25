package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.io.IOException;
import java.rmi.NotBoundException;

/**
 *
 */
public class GamePlayer {



    public static void main(String[] args) {

        TESTVIEW testview = new TESTVIEW();

        try {

            //PlayerConnectionFactory playerConnectionFactory = new SocketFactory("localhost", testview);

            PlayerConnectionFactory playerConnectionFactory = new RMIFactory("localhost", testview);
            RequestHandler requestHandler = playerConnectionFactory.getRequestHandler();

            requestHandler.connect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }



}
