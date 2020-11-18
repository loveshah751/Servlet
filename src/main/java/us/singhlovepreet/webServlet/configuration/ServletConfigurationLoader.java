package us.singhlovepreet.webServlet.configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import us.singhlovepreet.webServlet.internal.HttpServlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;
import java.util.logging.Level;

/**
 * This class is responsible for loading any configuration file required for the project.
 */
@Log
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServletConfigurationLoader {


    public static Map<String, HttpServlet> loadPropertiesFile(String fileName) {
        InputStream configFile = ServletConfigurationLoader.class.getClassLoader().getResourceAsStream(fileName);

        if (configFile == null) {
            log.log(Level.SEVERE, "Unable to load the config file");
        }

        return createURLContextMap(configFile);
    }

    private static Map<String, HttpServlet> createURLContextMap(InputStream configFile) {
        Properties properties = new Properties();
        Map<String, HttpServlet> urlContextMap = new WeakHashMap<>();
        try {
            properties.load(configFile);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getLocalizedMessage());
        }
        properties.forEach((k, v) -> {
            var key = (String) k;
            var value = getBean((String) v);
            urlContextMap.put(key, value);
        });
        return urlContextMap;
    }

    /**
     * This Method will create a bean whose super Class is  us.singhlovepreet.webServlet.internal.HttpServlet
     * for the givenName passed as a Argument.
     * @param beanName
     * @return us.singhlovepreet.webServlet.internal.HttpServlet
     */
    private static HttpServlet getBean(String beanName) {
        try {
            return (HttpServlet) Class.forName(beanName).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            log.log(Level.SEVERE, "Failed to create bean because of " + e.getLocalizedMessage());
        }
        return null;
    }
}
