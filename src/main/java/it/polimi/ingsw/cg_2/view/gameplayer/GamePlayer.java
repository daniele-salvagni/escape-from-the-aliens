package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.gameplayer.cli.CliInterpteter;
import it.polimi.ingsw.cg_2.view.gameplayer.cli.CliUpdater;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Scanner;

/**
 *
 */
public class GamePlayer {

    public static void main(String[] args) {

        ViewUpdater testview = new CliUpdater();
        Scanner in = new Scanner(System.in);

        try {


            System.out.println("RMI or Socket?");
            String choice = in.nextLine();

            PlayerConnectionFactory playerConnectionFactory;

            if (choice.equals("RMI")) {
                playerConnectionFactory = new RMIFactory("localhost", testview);
            } else {
                playerConnectionFactory = new SocketFactory
                        ("localhost", testview);
            }


            //PlayerConnectionFactory playerConnectionFactory = new RMIFactory
            // ("localhost", testview);
            RequestHandler requestHandler = playerConnectionFactory.getRequestHandler();

            //requestHandler.connect();


            while (true) {


                String cmd = in.nextLine();
                ResponseMsg response = null;

                RequestMsg request = CliInterpteter.parseString(playerConnectionFactory.getToken(), cmd);

                if (request==null){
                    System.out.println("ERROR: Invalid command");
                    continue;
                }

                response = requestHandler.processRequest(request);

                testview.update(response);

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }


    }


}
