package mySelenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import api.SeleniumActions;
import org.testng.annotations.Test;
import excel.ExcelHandle;
import driver.Driver;
import driver.DriverType;

/**
 * 打开浏览器
 */
public class TC_Prepare {
    Logger LOGGER = Logger.getLogger(TC_Prepare.class);
    private static final String PROPERTIES = "/resources/element.properties";
    private WebDriver webDriver;
    private SeleniumActions actions;
    private PropertiesHandle propertiesHandle;

    @BeforeSuite
    public void beforeSuit() {
        LOGGER.info(String.format("Before suit \"%s\"", this.getClass().getName()));
        PropertiesHandle.clear();
        ExcelHandle.getInstance().workbook();
        propertiesHandle = PropertiesHandle.getInstance(PROPERTIES);
        webDriver = Driver.getInstance(DriverType.CHROME).getWebDriver();
        actions = new SeleniumActions(DriverType.CHROME);
    }

    @Test(description = "1、打开管理端地址\n" +
            "2、登录\n" +
            "3、最大化浏览器窗口\n" +
            "4、获取Cookie")
    public void loginRmp() {
        actions.navigate(PropertiesHandle.get("baiduURL"));
        actions.maximizeBrowser();
    }

    @AfterSuite
    public void afterSuit() {

        webDriver.quit();
    }
}
