package us.singhlovepreet.socket;

import lombok.extern.java.Log;

import java.util.Scanner;

@Log
public class ServerRunTime {

    public static void main(String[] args) {

        GreeterServer server = new GreeterServer();
        var sc = server.getScanner();

        log.info("Please enter the Valid Port Number ");
        var portNumber = Integer.parseInt(sc.next());

        server.startConnection(null, portNumber);
        interactWithClient(server,sc);
    }

    private static void interactWithClient(GreeterServer server,Scanner sc) {
        welcomeMessage(server);
        for(Boolean keepConnectionAlive = true ; keepConnectionAlive != Boolean.FALSE; ){
            var messagePair = server.getReceivedMessage();
            var messageReceived = messagePair.getLeft();
            keepConnectionAlive = messagePair.getRight();
            log.info("Server > MessageReceived: " + messageReceived);
            if(keepConnectionAlive){
                sendMessage(server,sc);
            }
        }
    }

    private static void sendMessage(GreeterServer server,Scanner sc) {
        if (server.isServerAlive()) {
            log.info("Server > Enter your Message: ");
            var mesageToSend = sc.nextLine();
            server.sendMessage(mesageToSend);
        }
    }

    private static void welcomeMessage(GreeterServer server){
        if(server.isServerAlive()) {
            String automaticMessage = "Hello Client!";
            log.info("Server > Enter Automatic Welcome Message: "+automaticMessage);
            server.sendMessage(automaticMessage);
        }
    }
}
