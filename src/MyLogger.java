import java.io.IOException;
import java.util.logging.*;

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

        try
        {
            fileTxt = new FileHandler("Log.txt", true);
        }
        catch (SecurityException | IOException e)
        {
            e.printStackTrace();
        }

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

    }

    public static void main(String[] args) {
//        MyLogger myLogger = new MyLogger();
//        myLogger.log();
    }

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

    public static void log(Level level, String msg){
        getLogger().log(level, msg + "\n");
    }
}
