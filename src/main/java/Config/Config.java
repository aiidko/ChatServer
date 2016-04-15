package Config;

import MessageService.implementation.LoggerService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static int PORT ;
    public static int HISTORY_LENGTH;
    public static String HOST;
    public static String DEFAULT_USER_NAME;
    public static int MESSAGE_SIZE;

    public  Config(){
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("settings"));
            PORT = Integer.parseInt(properties.getProperty("PORT"));
            HISTORY_LENGTH = Integer.parseInt(properties.getProperty("HISTORY_LENGTH"));
            HOST = properties.getProperty("HOST");
            DEFAULT_USER_NAME=properties.getProperty("DEFAULT_USER_NAME");
            MESSAGE_SIZE = Integer.parseInt(properties.getProperty("MESSAGE_SIZE"));

        } catch (FileNotFoundException ex){
            LoggerService.writeErrorLog("Properties config file not found." + ex.toString());
        } catch (IOException ex){
            LoggerService.writeErrorLog("Error while reading file" + ex.toString());
        }
    }
}
