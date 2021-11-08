import java.io.IOException;
import java.util.logging.*;

/**
 * User defined static logger class for logging purposes.
 */
public class MyLogger {
    private static Logger logger;
    private static FileHandler fileTxt;
    private static SimpleFormatter formatterTxt;

    private MyLogger() /*throws IOException*/ {

        // get the global logger to configure it
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        // logger = Logger.getLogger(MyLogger.class.getName());

        // suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        // set the LogLevel to Info, severe, warning and info will be written
        // finest is still not written
        logger.setLevel(Level.INFO);
        logger.severe("Severe Log");
        logger.warning("Warning Log");
        logger.info("Info Log");
        logger.finest("Really not important");

        //creates a log file
        try
        {
            fileTxt = new FileHandler("Log.txt", true);
        }
        catch (SecurityException | IOException e)
        {
            e.printStackTrace();
        }

        // creates a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

    }

    public static void main(String[] args) {
//        MyLogger myLogger = new MyLogger();
//        myLogger.log();
    }

    /**
     * Initializes a new user defined logger.
     * @return A new user defined logger (MyLogger class).
     */
    private static Logger getLogger(){
        if(logger == null){
            try {
                new MyLogger();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return logger;
    }

    /**
     * Static method that can be used for logging a user defined message (with it's log level) into a log file.
     * @param level Sets the log level for the message (SEVERE, WARNING, INFO, FINE, FINEST).
     * @param msg A log message that should be written into a log file.
     */
    public static void log(Level level, String msg){
        getLogger().log(level, msg + "\n");
    }
}
