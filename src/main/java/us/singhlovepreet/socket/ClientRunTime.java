package us.singhlovepreet.socket;

import lombok.extern.java.Log;

import java.util.Scanner;

@Log
public class ClientRunTime {

    public static void main(String[] args) {
        GreeterClient client = new GreeterClient();
        var sc = client.getScanner();
        log.info("Please enter the Valid IP Address");
        var ipAddress = sc.next();

        log.info("Please enter the Valid Port Number ");
        var portNumber = Integer.parseInt(sc.next());

        client.startConnection(ipAddress,portNumber);
        welcomeMessage(client);
        var messageReceived = client.getReceivedMessage();
        System.out.println("Client> Message Received: "+messageReceived);
        log.info("client > MessageReceived: "+messageReceived);
        sendMessage(client, sc);
    }

    private static void sendMessage(GreeterClient client,Scanner sc){
        if(client.connectionAlive()){
            log.info("Client > Enter your Message: ");
            var messageToSend = sc.nextLine();
            client.sendMessage(messageToSend);
        }

    }
    private static void welcomeMessage(GreeterClient client){
        if(client.connectionAlive()) {
            String automaticMessage = "Hello Server! Thanks for accepting the Connection";
            log.info("Client > Enter Automatic Welcome Message: "+automaticMessage);
            client.sendMessage(automaticMessage);
        }
    }
}
