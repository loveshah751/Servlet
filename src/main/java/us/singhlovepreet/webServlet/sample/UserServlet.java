package us.singhlovepreet.webServlet.sample;

import lombok.extern.java.Log;
import us.singhlovepreet.webServlet.internal.HttpServlet;
import us.singhlovepreet.webServlet.internal.Request;
import us.singhlovepreet.webServlet.internal.Response;

@Log
public class UserServlet extends HttpServlet {

    @Override
    public void doGet(Request httpRequest, Response httpResponse){
        log.info("UserServlet doGet Method default implementation");
    }

    @Override
    public void doPost(Request httpRequest, Response httpResponse){
        log.info("UserServlet doPost Method default implementation");
    }

    @Override
    public void destroy() {
        log.info("Destroying all the Open Connections for user Servlet Class........");
    }
}
