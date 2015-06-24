package it.polimi.ingsw.cg_2.view.gamemanager;

import it.polimi.ingsw.cg_2.view.commons.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This thread is the server for request-response connections, it listens on a port for
 * incoming connections and instantiate a new SocketHandler when that happens using an
 * {@link ExecutorService}.
 */
public class SocketServer extends Thread {

    private static final Logger LOG = Logger.getLogger(SocketServer.class.getName());

    private final int port;
    private final ExecutorService executorService;
    private boolean isStopped;

    // RequestHandler is used only in the server implementation (the pub-sub uses a
    // broker)
    private RequestHandler requestHandler;

    public SocketServer(int port, ExecutorService executorService, RequestHandler
            requestHandler) {

        this(port, executorService);
        this.requestHandler = requestHandler;

    }

    protected SocketServer(int port, ExecutorService executorService) {

        this.port = port;
        this.executorService = executorService;
        this.isStopped = false;

    }

    protected SocketHandler newHandler(Socket socket) {

        return new ClientHandler(socket, requestHandler);

    }

    public synchronized void stopServer() {

        // Attempts to stop all actively executing tasks, halts the processing of
        // waiting tasks, and returns a list of the tasks that were awaiting execution.
        executorService.shutdownNow();
        this.isStopped = true;

    }

    @Override
    public void run() {

        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error while creating a new socket server.", e);
            return;
        }

        while (!isStopped()) {

            Socket socket;

            try {

                socket = serverSocket.accept();
                SocketHandler socketHandler = newHandler(socket);
                getExecutorService().execute(socketHandler);

            } catch (IOException e) {

            }

        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Can't close socket server.", e);
        }

    }

    protected int getPort() {

        return port;

    }

    protected ExecutorService getExecutorService() {

        return executorService;

    }

    protected RequestHandler getRequestHandler() {

        return requestHandler;

    }

    protected boolean isStopped() {

        return isStopped;

    }

    protected void setStopped(boolean isStopped) {

        this.isStopped = isStopped;

    }

}
