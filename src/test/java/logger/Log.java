package logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public class Log {

    private static Logger LOGGER;

    public static Logger getLogger(Class name) {
        LOGGER = Logger.getLogger(name);
        File dir = new File("/test-output/log");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        PropertyConfigurator.configure("log4j.properties");
        return LOGGER;
    }

    /**
     * 打印deug日志信息和报告信息
     *
     * @param message debug信息
     */
    public static void debug(String message) {
        if (LOGGER != null) {
            LOGGER.debug(message);
        }
    }

    /**
     * 打印 info日志信息和报告信息
     *
     * @param message
     */
    public static void info(String message) {
        if (LOGGER != null) {
            LOGGER.info(message);
        }
    }
}
