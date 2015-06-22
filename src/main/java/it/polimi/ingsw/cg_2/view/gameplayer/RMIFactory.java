package it.polimi.ingsw.cg_2.view.gameplayer;

import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.RequestHandler;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * PlayerConnectionFactory implementation using RMI, imports the RequestHandler
 * and the Broker from the GameManager and exports the Subscriber.
 */
public class RMIFactory extends PlayerConnectionFactory {

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
            /*
             * LocateRegistry class provides static methods for synthesizing
             * a remote interface to a registry at a particular network
             * address (host and port).
             */
            Registry registry = LocateRegistry.getRegistry(host, RMI_PORT);

            /*
             * lookup method searches for the remote interface binded to name
             * "RequestHandler" in the same host's registry.
             */
            requestHandler = (RequestHandler) registry.lookup("RequestHandler");

            /*
             * lookup method searches for the remote interface binded to name
             * "Broker" in the same host's registry.
             */
            broker = (BrokerInterface) registry.lookup("Broker");

            /*
             * Subscriber exports its own remote interface SubscriberInterface
			  * so that it can receive invocations from remote brokers.
			 */
            subscriberInterface = (SubscriberInterface) UnicastRemoteObject
                    .exportObject(getSubscriberInterface(), 0);

            setupConnection();

        } catch (RemoteException e) {
            throw e;
        } catch (NotBoundException | ClassCastException e) {
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
