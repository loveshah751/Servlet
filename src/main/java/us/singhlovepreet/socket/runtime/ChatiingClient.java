package us.singhlovepreet.socket.runtime;

import lombok.extern.java.Log;
import us.singhlovepreet.socket.ClientContainer;

import java.util.Scanner;

@Log
public class ChatiingClient {

    public static void main(String[] args) {
        ClientContainer client = new ClientContainer();
        var sc = client.getScanner();
        log.info("Please enter the Valid IP Address");
        var ipAddress = sc.next();

        log.info("Please enter the Valid Port Number ");
        var portNumber = Integer.parseInt(sc.next());

        client.startConnection(ipAddress, portNumber);
        interactWithServer(client,sc);
    }

    private static void interactWithServer(ClientContainer client, Scanner sc) {
        welcomeMessage(client);
        for(Boolean keepConnectionAlive = true ; keepConnectionAlive != Boolean.FALSE; ){
            var messagePair = client.getReceivedMessage();
            var messageReceived = messagePair.getLeft();
            keepConnectionAlive = messagePair.getRight();
            log.info("client > MessageReceived: " + messageReceived);
           if(keepConnectionAlive){
               sendMessage(client,sc);
           }
        }
    }

    private static void sendMessage(ClientContainer client, Scanner sc) {
        if (client.connectionAlive()) {
            log.info("Client > Enter your Message: ");
            var messageToSend = sc.nextLine();
            client.sendMessage(messageToSend);
        }

    }

    private static void welcomeMessage(ClientContainer client) {
        if (client.connectionAlive()) {
            String automaticMessage = "Hello Server! Thanks for accepting the Connection";
            log.info("Client > Enter Automatic Welcome Message: " + automaticMessage);
            client.sendMessage(automaticMessage);
        }
    }

}
