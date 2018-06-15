package mySelenium.tencent;

import data.TestData;
import mySelenium.PropertiesHandle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;


public class TC_Search_1 {
    private WebDriver driver;

    @Test(description = "1.进入首页\n" + "2.输入搜索的文件词\n" + "3.点击“查询”按钮", dataProvider = "searchDataProvider",dataProviderClass = TestData.class)
    public void testcase1(String searchkey) {
        PropertiesHandle propertiesHandle = PropertiesHandle.getInstance("/resources/element.properties");
        // 指定Chromedriver的位置
        driver = new ChromeDriver();
        driver.get(PropertiesHandle.get("url"));
        WebElement element = driver.findElement(By.xpath(PropertiesHandle.get("input_search_xpath")));
        element.sendKeys(searchkey);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath(PropertiesHandle.get("button_search_xpath"))).click();
    }

    @Test
    public void testcase2() {
        driver.close();
        Assert.assertTrue(true);
        System.out.println("testcase02");
    }


}
