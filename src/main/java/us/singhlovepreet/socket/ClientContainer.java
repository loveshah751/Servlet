package us.singhlovepreet.socket;

import lombok.extern.java.Log;
import us.singhlovepreet.utils.WebConstants;

@Log
public class ClientContainer extends SocketContainer {

    @Override
    public boolean startConnection(String ipAddress, int port) {
        log.info(WebConstants.CLIENT_CONNECTION_CREATION);
        clientSocket = createClientSocket(ipAddress, port);
        if(clientSocket.isPresent()) {
            writer = createPrintWriter();
            reader = createBufferedReader();
            log.info(WebConstants.CONNECTION_SUCCESSFULLY_ESTABLISHED);
        } else{
            log.info(WebConstants.CONNECTION_FAILED_TO_ESTABLISHED);
        }

        return clientSocket.isPresent();
    }

    public boolean connectionAlive() {
        return this.clientSocket.isPresent() ? !this.clientSocket.get().isClosed() : false;
    }
}
