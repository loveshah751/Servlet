package us.singhlovepreet.socket;

import lombok.extern.java.Log;
import us.singhlovepreet.utils.WebConstants;

import java.util.logging.Level;

/**
 * This class will demonstrate from the Server Machine perspective using TCP protocol in socket Programming.
 * Like any server, server mainly have two functionalities
 * Server.start() and server.stop()
 * <p>
 * For more Info about the Socket Programming @see {@link SocketContainer}
 */
@Log
public class ServerContainer extends SocketContainer {

    @Override
    public boolean startConnection(String ipAddress, int port) {
        log.info(WebConstants.SERVER_CONNECTION_IN_PROGRESS);
        serverSocket = createServerSocket(port);

        if (serverSocket.isPresent()) {
            log.info(WebConstants.WAITING_FOR_CLIENT_TO_CONNECT + port);
            clientSocket = registerClientWithServer(port);

            if(clientSocket.isPresent()) {
                writer = createPrintWriter();
                reader = createBufferedReader();
                log.info(WebConstants.CLIENT_REGISTERED_SUCCESSFULLY);
                log.info(WebConstants.SERVER_CONNECTION_STARTED);
            }else{
                log.log(Level.SEVERE,WebConstants.ERROR_REGISTERING_CLIENT);
            }
        }else {
            log.log(Level.SEVERE, WebConstants.ERROR_STARTING_SERVER);
        }

        return serverSocket.isPresent();
    }

    public boolean isServerAlive() {
        return this.serverSocket.isPresent() ? !this.serverSocket.get().isClosed() : false;
    }
}
