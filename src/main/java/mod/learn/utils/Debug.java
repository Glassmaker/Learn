package mod.learn.utils;

import org.apache.logging.log4j.Logger;

public class Debug {
    private static Logger log = null;

    public static void setLogger(Logger log) {
        Debug.log = log;
    }

    public static void message(String message) {
        Debug.log.info(message);
    }
}
