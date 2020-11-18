package us.singhlovepreet.webServlet.sample;

import lombok.extern.java.Log;
import us.singhlovepreet.utils.WebConstants;
import us.singhlovepreet.webServlet.internal.HttpServlet;
import us.singhlovepreet.webServlet.internal.Request;
import us.singhlovepreet.webServlet.internal.Response;

import java.io.PrintWriter;

@Log
public class SignUpServlet  extends HttpServlet {

    @Override
    public void doGet(Request httpRequest, Response httpResponse) {
        log.info("SignUpServlet doGet Method default implementation");


        PrintWriter wr = httpResponse.getPrintWriter();
        wr.println(WebConstants.OK);
        wr.println(WebConstants.CONTENT_TYPE);
        wr.println();
        wr.println(WebConstants.HTML_START_TAG);
        wr.println("<form method=\"post\">");
        wr.println("First Name: <input type=\"text\" id=\"fname\" name=\"fname\" value=\"Lovepreet\"><br>");
        wr.println("Last Name: <input type=\"text\" id=\"lname\" name=\"lname\" value=\"Singh\"><br><br>");
        wr.println("<input type=\"submit\" value=\"Submit\">");
        wr.println(WebConstants.HTML_END_TAG);
    }

    @Override
    public void doPost(Request httpRequest, Response httpResponse) {
        log.info("SignUpServlet doPost Method default implementation");

        PrintWriter wr = httpResponse.getPrintWriter();
        wr.println(WebConstants.OK);
        wr.println(WebConstants.CONTENT_TYPE);
        wr.println();
        wr.println(WebConstants.HTML_START_TAG);
        wr.println("First Name: "+ httpRequest.getBody().get("fname"));
        wr.println("<br>");
        wr.println("Last Name: "+ httpRequest.getBody().get("lname"));
        wr.println(WebConstants.HTML_END_TAG);

    }

    @Override
    public void destroy() {
        log.info("Destroying all the Open Connections for SignUp Servlet Class........");
    }
}
