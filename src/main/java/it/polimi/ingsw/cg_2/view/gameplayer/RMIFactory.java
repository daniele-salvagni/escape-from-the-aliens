package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PlayerConnectionFactory implementation using RMI, imports the RequestHandler
 * and the Broker from the GameManager and exports the Subscriber.
 */
public class RMIFactory extends PlayerConnectionFactory {

    private static final Logger LOG = Logger.getLogger(RMIFactory.class.getName());

    public static final int RMI_PORT = 7777;

    private final BrokerInterface broker;
    private final RequestHandler requestHandler;
    private SubscriberInterface subscriberInterface;

    /**
     * Create a new RMI player connection factory.
     *
     * @param host        the hostname of the server
     * @param viewUpdater the view updater, displays the server updates on the
     *                    view
     * @throws RemoteException   if an error occurred while starting RMI
     * @throws NotBoundException if the remote interface has not been found
     */
    public RMIFactory(String host, ViewUpdater viewUpdater) throws
            RemoteException, NotBoundException {

        super(viewUpdater);

        try {

            // Returns a reference to the remote object Registry on the specified host
            // and port. If host is null, the local host is used.
            Registry registry = LocateRegistry.getRegistry(host, RMI_PORT);

            // Returns the remote reference bound to the specified name in this registry.
            requestHandler = (RequestHandler) registry.lookup("RequestHandler");

            broker = (BrokerInterface) registry.lookup("Broker");

            // Exports the remote object to make it available to receive incoming
            // calls, using the particular supplied port. By using 0 RMI implementation
            // chooses a port.
            subscriberInterface = (SubscriberInterface) UnicastRemoteObject
                    .exportObject(getSubscriber(), 0);

            setupConnection();

        } catch (RemoteException e) {
            LOG.log(Level.SEVERE, "There was a problem establishing a RMI connection to" +
                    " the game manager.", e);
            throw e;
        } catch (NotBoundException | ClassCastException e) {
            LOG.log(Level.SEVERE, "RMI connection error (problem with the remote " +
                    "interface).", e);
            throw e;
        }

    }

    @Override
    public RequestHandler getRequestHandler() {

        return requestHandler;

    }

    @Override
    public BrokerInterface getBrokerInterface() {

        return broker;

    }

    @Override
    public SubscriberInterface getSubscriberInterface() {

        return subscriberInterface;

    }

}
