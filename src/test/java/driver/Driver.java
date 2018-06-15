package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    private static volatile Driver driver = null;
    private static WebDriver webDriver = null;

    private Driver() {

    }

    /**
     * 初始化WebDriver
     *
     * @param type webdriver 类型
     */
    private static void initWebDriver(DriverType type) {
        switch (type) {
            case CHROME:
                webDriver = new ChromeDriver();
        }
    }

    /**
     * 获取Driver实例
     *
     * @param driverType webDriver类型
     * @return
     */
    public static Driver getInstance(DriverType driverType) {
        if (driver == null) {
            synchronized (Driver.class) {
                if (driver == null) {
                    driver = new Driver();
                    initWebDriver(driverType);
                }
            }
        }
        return driver;
    }

    /**
     * 获取当前Driver维护的WebDriver
     *
     * @return
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }
}
