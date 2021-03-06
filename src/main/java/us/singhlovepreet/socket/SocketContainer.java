package us.singhlovepreet.socket;

import lombok.extern.java.Log;
import org.apache.commons.lang3.tuple.Pair;
import us.singhlovepreet.utils.WebConstants;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * @apiNote This is class provide introduction to java Socket programming concepts
 * <p>
 * Socket Programming @see {@link java.net.Socket}, {@link java.net.ServerSocket} is way of writing programs that
 * executes on multiple machines, where each machine is connected with other machine within the network.
 * <p>
 * In short, imagine socket as a endpoint for communication between two machines.
 * <p>
 * There are two protocols that helps each machine to communicates with each other using socket programming are:
 * 1.) TCP (Transmission control protocol)
 * 2.) UDP (User Datagram Protocol)
 * <p>
 * Main Difference between these two protocols are, UDP are connectionless which means there is  no session
 * between client and server (i.e. each machine)
 * <p>
 * In java, there is network package which provides all necessary functionality regarding network programming.
 * @see java.net;
 */
@Log
public abstract class SocketContainer {

    public Optional<Socket> clientSocket;
    public Optional<ServerSocket> serverSocket;
    public Optional<BufferedReader> reader;
    public Optional<PrintWriter> writer;
    public Scanner scanner;

    public abstract boolean startConnection(String ipAddress, int port);

    public  void sendMessage(String msg){
        this.writer.ifPresent(write -> write.println(msg) );
    };

    public Optional<BufferedReader> getBufferReader() {
        if(this.reader != null &&  this.reader.isPresent()) return this.reader;

        return createBufferedReader();
    }

    public Optional createBufferedReader() {
        if (clientSocket.isPresent()) {
            try {
                return Optional.of(new BufferedReader(new InputStreamReader(clientSocket.get().getInputStream())));
            } catch (IOException e) {
                return errorReturn(e);
            }
        }
        return Optional.empty();
    }

    public Optional<PrintWriter> getPrintWriter() {

        if(this.writer != null && this.writer.isPresent()) return this.writer;

        return createPrintWriter();
    }

    public Optional createPrintWriter() {
        if (this.clientSocket.isPresent()) {
            try {
                return Optional.of(new PrintWriter(this.clientSocket.get().getOutputStream(), true));
            } catch (IOException e) {
                return errorReturn(e);
            }
        }
        return Optional.empty();
    }

    public Optional<ServerSocket> createServerSocket(int port) {
        try {
            return Optional.of(new ServerSocket(port));
        } catch (IOException e) {
            return errorReturn(e);
        }
    }


    public Optional<Socket> registerClientWithServer(int port) {

        if (this.serverSocket.isPresent()) {
            try {
                return Optional.of(this.serverSocket.get().accept());
            } catch (IOException e) {
                return errorReturn(e);
            }
        }
        return Optional.empty();
    }

    public Optional<Socket> createClientSocket(String ipAddress, int port) {
        try {
            return Optional.of(new Socket(ipAddress,port));
        } catch (IOException e) {
            return errorReturn(e);
        }
    }

    public void stopConnection() {
        log.info(WebConstants.TERMINATE_OPEN_CONNECTION);
        this.reader.ifPresent(this::closeConnection);
        this.serverSocket.ifPresent(this::closeConnection);
        this.clientSocket.ifPresent(this::closeConnection);
        this.writer.ifPresent(this::closeConnection);
    }

    public String getMessageFromBufferReader() {
        if (this.reader != null && this.reader.isPresent()) {
            try {
                var readerObj = reader.get();
                var message = readerObj.ready() ? reader.get().readLine() : WebConstants.NO_MESSAGE_AVAILABLE;
                return message;
            } catch (IOException e) {
                return WebConstants.NO_MESSAGE_AVAILABLE;
            }
        }
        return WebConstants.NO_MESSAGE_AVAILABLE;
    }

    private void closeConnection(Closeable object) {
        try {
            object.close();
        } catch (IOException e) {
            log.log(Level.SEVERE, WebConstants.ERROR_MESSAGE + e.getLocalizedMessage());
        }
    }

    private Optional errorReturn(Exception e) {
        log.log(Level.SEVERE, WebConstants.ERROR_MESSAGE + e.getLocalizedMessage());
        return Optional.empty();
    }

    public  Pair<String,Boolean> getReceivedMessage() {
        var message = this.getMessageFromBufferReader();
        if (message.equalsIgnoreCase(WebConstants.STOP)) {
            log.info(WebConstants.SOCKET_TERMINATION);
           this.stopConnection();
            return Pair.of(WebConstants.CONNECTION_TERMINATED,Boolean.FALSE);
        }
        return Pair.of(message,Boolean.TRUE);
    }

    public Scanner getScanner(){
        return this.scanner == null ?  new Scanner(System.in): this.scanner;
    }
}
