package us.singhlovepreet.webServlet.internal;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.NonNull;
import lombok.extern.java.Log;
import us.singhlovepreet.utils.WebConstants;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is class custom implementation jakarta.servlet.http.HttpServlet class.
 *
 * As this class will demonstrate step by step implementation of the HttpServlet class from jakarta library.
 */
@Log
public abstract class HttpServlet {

    public void init(){
        log.info("HttpServlet default implementation");
    }

    public void doGet(Request httpRequest, Response httpResponse){
        log.info("HttpServlet default implementation for doGet Method");
    }

    public void doPost(Request httpRequest, Response httpResponse){
        log.info("HttpServlet default implementation for doPost Method");
    }

    /**
     * Refer to this for more details {@link jakarta.servlet.Servlet#service} method.
     * @param request
     * @param response
     */
    public void service(@NonNull Request request, @NonNull Response response) {
        var requestMethod = request.getRequestType();

        if (requestMethod.isGET()) {
            doGet(request,response);
        } else if (requestMethod.isPost()) {
            doPost(request,response);
        } else {
            new RuntimeException("Method type not supported");
        }
    }

    public void destroy() {
        log.info("Destroying all the Open Connections.");
    }
}
