package us.singhlovepreet.socket;

import lombok.extern.java.Log;

/**
 * This class will demonstrate from the Server Machine perspective using TCP protocol in socket Programming.
 * Like any server, server mainly have two functionalities
 * Server.start() and server.stop()
 * <p>
 * For more Info about the Socket Programming @see {@link SocketContainer}
 */
@Log
public class GreeterServer extends SocketContainer {

    @Override
    public void startConnection(String ipAddress, int port) {
        log.info("Server Connection In progress");
        serverSocket = createServerSocket(port);

        if (serverSocket.isPresent()) {
            log.info("Server with address " + serverSocket.get().getInetAddress().toString() + "started at port number " + serverSocket.get().getLocalPort());
            clientSocket = registerClientWithServer(port);
            writer = getPrintWriter();
            reader = getBufferReader();
            log.info("Successfully registered the Client with Server!");
        }
        log.info("Successfully started the Server a connection!");
    }

    public boolean isServerAlive() {
        return this.serverSocket.isPresent() ? !this.serverSocket.get().isClosed() : false;
    }
}
