package us.singhlovepreet.webServlet.sample;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.java.Log;
import us.singhlovepreet.webServlet.internal.HttpServlet;

import java.io.IOException;

@Log
public class UserServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        //TODO : need to implement later
    }

    @Override
    public void doGet(){
        log.info("UserServlet doGet Method default implementation");
    }

    @Override
    public void doPost(){
        log.info("UserServlet doPost Method default implementation");
    }
}
