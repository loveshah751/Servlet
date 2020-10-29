package us.singhlovepreet.socket;

import lombok.extern.java.Log;

@Log
public class GreeterClient extends SocketContainer {

    @Override
    public void startConnection(String ipAddress, int port) {
        log.info("Client Connection creation started");
        clientSocket = createClientSocket(ipAddress, port);
        writer = getPrintWriter();
        reader = getBufferReader();
        log.info("Successfully established a connection!");
    }

    public boolean connectionAlive() {
        return this.clientSocket.isPresent() ? !this.clientSocket.get().isClosed() : false;
    }
}
