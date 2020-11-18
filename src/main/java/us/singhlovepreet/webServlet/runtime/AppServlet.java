package us.singhlovepreet.webServlet.runtime;

import lombok.extern.java.Log;
import us.singhlovepreet.socket.ServerContainer;
import us.singhlovepreet.utils.WebConstants;
import us.singhlovepreet.webServlet.configuration.ServletConfigurationLoader;
import us.singhlovepreet.webServlet.internal.HttpServlet;
import us.singhlovepreet.webServlet.internal.Request;
import us.singhlovepreet.webServlet.internal.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

@Log
public class AppServlet {

    private static Map<String, HttpServlet>  urlContextMap;

    public static void main(String[] args) throws IOException {

        var webServer = new ServerContainer();
         urlContextMap = ServletConfigurationLoader.loadPropertiesFile("config.properties");
         shutDownHook();

        /** While true because we want to our web Server to keep running.
         * In short, our web server should be capable of accepting the multiple request.
         **/
        while (true) {
            var serverStarted =  webServer.startConnection(null, 8080);

            if(serverStarted) {
                var httpRequest = new Request();
                var validateRequest = httpRequest.parseRequest(webServer);

                if(validateRequest) {
                    var servlet = urlContextMap.get(httpRequest.getPath());
                    if(Objects.isNull(servlet)) {
                        WebConstants.errorResponse404(webServer);
                        continue;
                    }
                    var httpResponse = new Response(webServer.clientSocket.get().getOutputStream());
                    servlet.service(httpRequest,httpResponse);

                }else{
                    WebConstants.errorResponse500(webServer);
                }
               webServer.stopConnection();
            }
            else {
                log.log(Level.SEVERE, WebConstants.FAILED_START_CLIENT_CONNECTION);
                break;
            }
        }
    }


    private static void shutDownHook(){
       var shutDownThread = new Thread() {
            @Override
            public void run() {
                log.info("Gracefully Shutdown the hook.........");
                urlContextMap.values().forEach(httpServlet -> httpServlet.destroy());
                urlContextMap.clear();
                System.gc();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutDownThread);
    }

}
