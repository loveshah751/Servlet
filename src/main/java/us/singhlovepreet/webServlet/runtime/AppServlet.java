package us.singhlovepreet.webServlet.runtime;

import us.singhlovepreet.webServlet.configuration.ServletConfigurationLoader;

import java.io.IOException;

public class AppServlet {

    public static void main(String[] args) throws IOException {
       var map = ServletConfigurationLoader.loadPropertiesFile("config.properties");
       map.forEach((k,v) -> System.out.println("Key: "+k +"\nvalue: "+v));
    }
}
