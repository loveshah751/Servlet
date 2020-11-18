package us.singhlovepreet.socket.runtime;

import lombok.extern.java.Log;
import us.singhlovepreet.socket.ServerContainer;
import us.singhlovepreet.utils.WebConstants;
import us.singhlovepreet.webServlet.internal.Request;

import java.io.IOException;
import java.util.logging.Level;

/**
 * This Class demonstrate the Web Browser trying to access our custom server content.
 * As of now there is nothing our server is processing the request, it simple log the
 * incoming request to demonstrate the connectivity between our webServer class and web browser.
 */
@Log
public class WebServer {

    public static void main(String[] args) {
        var myWebServer = new ServerContainer();
        /** While true because we want to our web Server to keep running.
         * In short, our web server should be capable of accepting the multiple request.
         **/
        while (true) {
          var serverStarted =  myWebServer.startConnection(null, 8081);
          /** uncomment this method call to understand the incoming the request format**/
          //readWebBrowserRequest(myWebServer);
            if(serverStarted) {
                var validateRequest = parseWebBrowserRequest(myWebServer);
                responseToWebBrowser(myWebServer,validateRequest);
                myWebServer.stopConnection();
            }
            else {
                log.log(Level.SEVERE, WebConstants.FAILED_START_CLIENT_CONNECTION);
                break;
            }
        }
    }

    /**
     * This method will simply read the content send by the client, in our case is web browser
     * hitting the url:<link>localhost:8081</link>. And our method will simply read all the web content
     * sent by the browser and log it to console.
     * @param myWebServer
     *              server which holds all the required resources for processing the request
     */
    private static void readWebBrowserRequest(ServerContainer myWebServer) throws IOException {

        var reader = myWebServer.getBufferReader().get();
        StringBuilder body = new StringBuilder();
        while (reader.ready()){
            body.append((char)reader.read());
        }

        String data = body.toString();
        log.info("***********************************************");
        log.info(WebConstants.WEB_BROWSER_REQUEST);
        log.info(data);
        log.info(WebConstants.END_OF_WEB_BROWSER_REQUEST);
        log.info("***********************************************");
    }


    private static boolean parseWebBrowserRequest(ServerContainer myWebServer) {
        try {
            return new Request().parseRequest(myWebServer);
        } catch (Exception ex) {
            log.log(Level.SEVERE,WebConstants.REQUEST_PARSING_FAILURE);
            return false;
        }
    }

    /**
     * This method will simply send the http response back to our browser.
     * As our browser will only able to render the http response on the screen.
     *
     * @param myWebServer
     *              server which holds all the required resources for processing the request
     */
    private static void responseToWebBrowser(ServerContainer myWebServer, boolean validateRequest) {
        if (validateRequest) {
            WebConstants.successResponse(myWebServer);
        } else {
            WebConstants.errorResponse500(myWebServer);
        }
    }

}
