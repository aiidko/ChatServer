package MessageService.implementation;

import org.apache.log4j.Logger;

public  class LoggerService {
    private final static Logger logger = Logger.getLogger("admin");

    public static void writeInfoLog(String logText){
        System.out.println(logText);
        logger.info(logText);
    }

    public static void writeErrorLog(String logText){
        logger.error(logText);
    }
}
