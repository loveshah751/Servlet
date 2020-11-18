package us.singhlovepreet.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import us.singhlovepreet.socket.ServerContainer;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebConstants {

    public static final String TERMINATE_OPEN_CONNECTION = "Terminating All the open Connections";

    public static final String NO_MESSAGE_AVAILABLE = "Sorry No Message Available";

    public static final String ERROR_MESSAGE = "Error Message ";

    public static final String SOCKET_TERMINATION = "Socket request for the Termination";

    public static final String CONNECTION_TERMINATED = "Connection Terminated";

    public static final String CONNECTION_SUCCESSFULLY_ESTABLISHED = "Successfully established a connection!";

    public static final String CONNECTION_FAILED_TO_ESTABLISHED = "Failed to established a connection!";

    public static final String CLIENT_CONNECTION_CREATION = "Client Connection creation started";

    public static final String STOP = "stop";

    public static final String SERVER_CONNECTION_IN_PROGRESS = "Server Connection In progress";

    public static final String WAITING_FOR_CLIENT_TO_CONNECT = "Waiting for Client to connected on the port Number ";

    public static final String CLIENT_REGISTERED_SUCCESSFULLY = "Successfully registered the Client with Server!";

    public static final String SERVER_CONNECTION_STARTED = "Successfully started the Server a connection!";

    public static final String ERROR_REGISTERING_CLIENT = "Error registering the Client";

    public static final String ERROR_STARTING_SERVER = "Error Starting the Server";

    public static final String FAILED_START_CLIENT_CONNECTION = "Failed to start the connection with client.";

    public static final String WEB_BROWSER_REQUEST = "Request from the Web Browser";

    public static final String END_OF_WEB_BROWSER_REQUEST = "End of Web Browser Request";

    public static final String REQUEST_PARSING_FAILURE = "Failed to Parse the input Request";

    public static final String OK = "HTTP/1.1 200 O.K.";

    public static final String SERVER_ERROR = "HTTP/1.1 500";

    public static final String HTTP_RESOURCE_NOT_FOUND = "HTTP/1.1 404";

    public static final String CONTENT_TYPE = "content-type: text/html";

    public static final String HTML_START_TAG = "<html><body> ";

    public static final String PARAGRAPH_TEXT = "<p> My web server response : ";

    public static final String PARAGRAPH_DYNAMIC_TEXT = "<p> CurrentTime:" + LocalDateTime.now() + "</p>";

    public static final String PARAGRAPH_WITH_SERVER_ERROR_MESSAGE = "<p> Internal Server Error";

    public static final String PARAGRAPH_WITH_RESOURCE_NOT_FOUND= "<p> Resource Not Found 404!";

    public static final String QUERY_PARAM_LOG_MSG = "Request Query Param ";

    public static final String REQUEST_BODY_LOG_MSG = "Request Query Param ";

    public static final String HTML_END_TAG = "</body> </html>";

    public static final String JSON_START_TAG = "{";

    public static final String JSON_END_TAG = "}";

    public static final String COMA = ",";

    public static final String EMPTY_SPACE = " ";

    public static final String AND_CHAR = "&";

    public static final String EQUAL_CHAR = "=";

    public static final String COLON_CHAR = ":";

    public static final String QUESTION_MARK = "?";


    public static void successResponse(ServerContainer myWebServer) {
        var writer = myWebServer.writer;
        writer.ifPresent(wr -> {
            wr.println(WebConstants.OK);
            wr.println(WebConstants.CONTENT_TYPE);
            wr.println();
            wr.println(WebConstants.HTML_START_TAG);
            wr.println(WebConstants.PARAGRAPH_TEXT);
            wr.println(WebConstants.PARAGRAPH_DYNAMIC_TEXT);
            wr.println(WebConstants.HTML_END_TAG);

        });
    }

    public static void errorResponse500(ServerContainer myWebServer){
        var writer = myWebServer.writer;
        writer.ifPresent(wr -> {
            wr.println(WebConstants.SERVER_ERROR);
            wr.println(WebConstants.CONTENT_TYPE);
            wr.println();
            wr.println(WebConstants.HTML_START_TAG);
            wr.println(WebConstants.PARAGRAPH_WITH_SERVER_ERROR_MESSAGE);
            wr.println(WebConstants.HTML_END_TAG);

        });
    }


    public static void errorResponse404(ServerContainer myWebServer){
        var writer = myWebServer.writer;
        writer.ifPresent(wr -> {
            wr.println(WebConstants.HTTP_RESOURCE_NOT_FOUND);
            wr.println(WebConstants.CONTENT_TYPE);
            wr.println();
            wr.println(WebConstants.HTML_START_TAG);
            wr.println(WebConstants.PARAGRAPH_WITH_RESOURCE_NOT_FOUND);
            wr.println(WebConstants.HTML_END_TAG);

        });
    }
}
