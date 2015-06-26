package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.messages.requests.RequestMsg;
import it.polimi.ingsw.cg_2.messages.responses.ResponseMsg;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.gameplayer.cli.CliInterpteter;
import it.polimi.ingsw.cg_2.view.gameplayer.cli.CliUpdater;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point for a game player.
 */
public class GamePlayer {

    private static final Logger LOG = Logger.getLogger(GamePlayer.class.getName());

    public static void main(String[] args) {

        ViewUpdater view = new CliUpdater();
        Scanner scanner = new Scanner(System.in);

        String connectionType;

        do {
            System.out.println("Enter RMI or SOCKET to choose the connection type:");
            connectionType = scanner.nextLine();
        } while (!connectionType.matches("^(RMI|SOCKET)$"));

        try {

            PlayerConnectionFactory playerConnectionFactory;

            if (connectionType.matches("^(RMI)$")) {
                playerConnectionFactory = new RMIFactory("localhost", view);
            } else {
                playerConnectionFactory = new SocketFactory("localhost", view);
            }

            RequestHandler requestHandler = playerConnectionFactory.getRequestHandler();

            while (true) {

                String cmd = scanner.nextLine();

                ResponseMsg response;
                RequestMsg request = CliInterpteter.parseString(playerConnectionFactory
                        .getToken(), cmd);

                if (request == null) {
                    System.out.println("ERROR: Invalid command");
                    continue;
                }

                response = requestHandler.processRequest(request);
                view.update(response);

            }


        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Can't establish an Socket connection.");
        } catch (NotBoundException e) {
            LOG.log(Level.SEVERE, "Can't establish an RMI connection.");
        }

    }

}
