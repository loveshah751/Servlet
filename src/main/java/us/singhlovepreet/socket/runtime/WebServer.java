package us.singhlovepreet.socket.runtime;

import lombok.extern.java.Log;
import org.apache.commons.lang3.ThreadUtils;
import us.singhlovepreet.socket.ServerContainer;

import java.time.LocalDateTime;

/**
 * This Class demonstrate the Web Browser trying to access our custom server content.
 * As of now there is nothing our server is processing the request, it simple log the
 * incoming request to demonstrate the connectivity between our webServer class and web browser.
 */
@Log
public class WebServer {

    public static void main(String[] args) throws InterruptedException {
        var myWebServer = new ServerContainer();
        /** While true because we want to our web Server to keep running.
         * In short, our web server should be capable of accepting the multiple request.
         **/
        while(true) {
            myWebServer.startConnection(null,8081);
            Thread.sleep(5000);
            readWebBrowserRequest(myWebServer);
            responseToWebBrowser(myWebServer);
            myWebServer.stopConnection();
        }
    }

    /**
     * This method will simply read the content send by the client, in our case is web browser
     * hitting the url:<link>localhost:8081</link>. And our method will simply read all the web content
     * sent by the browser and log it to console.
     * @param myWebServer
     */
    private static void readWebBrowserRequest(ServerContainer myWebServer){
        var webContent = myWebServer.getMessageFromBufferReader();
        log.info("***********************************************");
        log.info("Request from the Web Browser");
        while(!webContent.isEmpty()){
            log.info(webContent);
            webContent = myWebServer.getMessageFromBufferReader();
        }

        log.info("End of Web Browser Request");
        log.info("***********************************************");
    }

    /**
     * This method will simply send the http response back to our browser.
     * As our browser will only able to render the http response on the screen.
     * @param myWebServer
     */
    private static void responseToWebBrowser(ServerContainer myWebServer){
    var writer = myWebServer.writer;
    writer.ifPresent(wr ->{
        wr.println("HTTP/1.1 200 O.K.");
        wr.println("content-type: text/html");
        wr.println();
        wr.println("<html><body> ");
        wr.println("<p> My web server response : ");
        wr.println("<p> CurrentTime:"+LocalDateTime.now()+"</p>");
        wr.println("</body> </html>");

    });
    }
}
