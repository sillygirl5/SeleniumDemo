package mySelenium;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesHandle {
    private static volatile PropertiesHandle propertiesHandle = null;
    private static Properties properties = null;
    private PropertiesHandle() {
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static PropertiesHandle getInstance(String path) {
        if (propertiesHandle == null) {
            synchronized (PropertiesHandle.class) {
                if (propertiesHandle == null) {
                    propertiesHandle = new PropertiesHandle();
                    initProperties(path);
                }
            }
        }
        return propertiesHandle;
    }

    private static void initProperties(String path) {
        properties = new Properties();
        InputStream is = PropertiesHandle.class.getResourceAsStream(path);
        try {
            properties.load(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {

                }
            }
        }
    }

    public static void clear() {
        if (propertiesHandle != null) {
            propertiesHandle = null;
        }
    }
}
