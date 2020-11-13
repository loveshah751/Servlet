package us.singhlovepreet.webServlet.internal;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;

/**
 * This is class custom implementation jakarta.servlet.http.HttpServlet class.
 *
 * As this class will demonstrate step by step implementation of the HttpServlet class from jakarta library.
 */
@Log
public abstract class HttpServlet {

    public abstract void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;

    public void init(){
        log.info("HttpServlet default implementation");
    }

    public void doGet(){
        log.info("HttpServlet default implementation for doGet Method");
    }

    public void doPost(){
        log.info("HttpServlet default implementation for doPost Method");
    }
}
