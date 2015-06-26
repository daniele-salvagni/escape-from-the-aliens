package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.actions.MoveRequestMsg;
import it.polimi.ingsw.cg_2.messages.requests.actions.SendChatMsg;
import it.polimi.ingsw.cg_2.messages.responses.InvalidRequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.gameplayer.cli.CliInterpteter;
import it.polimi.ingsw.cg_2.view.gameplayer.cli.CliMessageVisitor;
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

        try {

            PlayerConnectionFactory playerConnectionFactory = new SocketFactory
                    ("localhost", testview);

            //PlayerConnectionFactory playerConnectionFactory = new RMIFactory
            // ("localhost", testview);
            RequestHandler requestHandler = playerConnectionFactory.getRequestHandler();

            //requestHandler.connect();

            Scanner in = new Scanner(System.in);
            while (true) {


                String cmd = in.nextLine();
                ResponseMsg response = null;

                RequestMsg request = CliInterpteter.parseString(playerConnectionFactory.getToken(), cmd);

                response = requestHandler.processRequest(request);

                testview.update(response);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
