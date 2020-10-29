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
        welcomeMessage(server);
        var messageReceived = server.getReceivedMessage();
        System.out.println("Server> Message Received: "+messageReceived);
        log.info("Server > MessageReceived: " + messageReceived);
        sendMessage(server,sc);
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
