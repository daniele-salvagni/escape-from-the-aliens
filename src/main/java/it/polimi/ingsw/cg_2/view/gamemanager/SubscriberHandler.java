package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.messages.broadcast.BroadcastMsg;
import it.polimi.ingsw.cg_2.view.commons.BrokerInterface;
import it.polimi.ingsw.cg_2.view.commons.SubscriberInterface;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

/**
 *
 */
public class SubscriberHandler extends SocketHandler implements SubscriberInterface {


    private static final Logger LOG = Logger.getLogger(SubscriberHandler.class.getName());
    private static final int TIMEOUT = 3000;

    private final ExecutorService executorService;

    private final BrokerInterface brokerInterface;

    private final ConcurrentLinkedQueue<BroadcastMsg> broadcastBuffer;
    private ObjectOutputStream outputStream;

    boolean isSubscribed;

    public SubscriberHandler(Socket socket, ExecutorService executorService,
                             BrokerInterface brokerInterface) {

        super(socket);
        this.executorService = executorService;
        this.brokerInterface = brokerInterface;

        broadcastBuffer = new ConcurrentLinkedQueue<>();

        isSubscribed = false;

    }





    @Override
    public void run() {

    }

    @Override
    public void dispatchMessage(BroadcastMsg message) throws RemoteException {

    }

}
