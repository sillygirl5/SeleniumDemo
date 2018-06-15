package mySelenium.baidu;

import data.TestData;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import api.SeleniumActions;
import driver.Driver;
import driver.DriverType;
import api.LocatorType;
import mySelenium.PropertiesHandle;

public class TC_SearchBaidu_1 {
    Logger LOGGER = Logger.getLogger(TC_SearchBaidu_1.class);
    private WebDriver webDriver;
    private SeleniumActions actions;

    @BeforeTest
    public void beforeTest() {
        LOGGER.info(String.format("Before test \"%s\"", this.getClass().getName()));
        webDriver = Driver.getInstance(DriverType.CHROME).getWebDriver();
        actions = new SeleniumActions(DriverType.CHROME);
    }

    @AfterTest
    public void afterTest() {
        LOGGER.info(String.format("After test \"%s\"", this.getClass().getName()));
    }

    @Test(description = "1、打开百度搜索的页面\n" +
            "2、输入搜索的关键词\n" +
            "3、点击“搜索”\n", dataProvider = "baiduSearchDataProvider",dataProviderClass = TestData.class)
    public void baiduSearch(String key) {
        WebElement webElement = actions.findElement(LocatorType.XPATH,PropertiesHandle.get("input_searchBaidu_xpath"),10);
        webElement.sendKeys(key);
        actions.click(LocatorType.XPATH,PropertiesHandle.get("button_searchBaidu_xpath"),10);
        actions.sleep(2000);

    }

}
