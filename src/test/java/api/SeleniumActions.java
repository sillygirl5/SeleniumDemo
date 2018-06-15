package api;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import driver.Driver;
import logger.Log;
import driver.DriverType;

import java.util.function.Function;

public class SeleniumActions implements Actions {

    private Logger LOGGER = Log.getLogger(SeleniumActions.class);
    private static final String UPLOADEXE_PATH = System.getProperty("user.dir") + File.separator + "tools" + File.separator + "upload.exe";
    private WebDriver driver;
    private DriverType driverType;

    public SeleniumActions(DriverType type) {
        this.driverType = type;
        this.driver = Driver.getInstance(type).getWebDriver();
    }

    /**
     * 在windows的文件选择窗口中选择文件
     *
     * @param filePath
     * @return
     */
    @Override
    public boolean uploadFile(String filePath) {
        LOGGER.info("Select file " + filePath + " in windows file chooser");
        boolean result = false;
        String command = UPLOADEXE_PATH + " chrome " + filePath;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            result = true;
        } catch (IOException e) {
            LOGGER.error("Select file in windows file chooser fail.", e);
            result = false;
        } finally {
            if (process != null) {
                process.destroy();
            }
            return result;
        }
    }

    /**
     * 打开新标签定向到一个url中
     *
     * @param url
     */
    @Override
    public void openNewTab(String url) {
        LOGGER.info("Open url " + url + " in a new tab.");
        String script = String.format("window.open(\"%s\")", url);
        executeJavaScript(script);
    }

    /**
     * 切换页面
     *
     * @param handleName
     */
    @Override
    public void switchToWindow(String handleName) {
        LOGGER.info("Switch to window " + handleName);
        driver.switchTo().window(handleName);
    }

    /**
     * 切换页面
     *
     * @param index
     */
    @Override
    public void switchToWindow(int index) {
        LOGGER.info("Switch to window " + index);
        ArrayList<String> handleNames = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handleNames.get(index));
    }

    /**
     * 获取当前打开的所有窗口
     *
     * @return
     */
    @Override
    public Set<String> getAllWindows() {
        return driver.getWindowHandles();
    }

    /**
     * 最大化浏览器
     */
    public void maximizeBrowser() {
        LOGGER.info("Maximize browser.");
        driver.manage().window().maximize();
    }

    /**
     * 全屏浏览器
     */
    @Override
    public void fullScreenBrowser() {
        LOGGER.info("Full screen browser.");
        driver.manage().window().fullscreen();
    }

    /**
     * 跳转至具体页面
     *
     * @param url
     */
    public void navigate(String url) {
        LOGGER.info(String.format("Navigate to %s", url));
        driver.navigate().to(url);
    }

    /**
     * 输入指定内容
     *
     * @param type
     * @param value
     * @param content
     */
    public void input(LocatorType type, Object value, String content) {
        LOGGER.info(String.format("Input content %s in web element %s .", content, value));
        WebElement webElement = null;
        switch (type) {
            case ID:
                String id = (String) value;
                webElement = driver.findElement(By.id(id));
                break;
            case NAME:
                String name = (String) value;
                webElement = driver.findElement(By.name(name));
                break;
            case XPATH:
                String xpath = (String) value;
                webElement = driver.findElement(By.xpath(xpath));
                break;
            case CLASSNAME:
                String className = (String) value;
                webElement = driver.findElement(By.className(className));
                break;
            case CSS:
                String css = (String) value;
                webElement = driver.findElement(By.cssSelector(css));
                break;
            case LINKTEXT:
                String linkText = (String) value;
                webElement = driver.findElement(By.linkText(linkText));
                break;
            case PARTIALLINKTEXT:
                String partialLinkText = (String) value;
                webElement = driver.findElement(By.partialLinkText(partialLinkText));
                break;
            case TAGNAME:
                String tagName = (String) value;
                webElement = driver.findElement(By.tagName(tagName));
                break;
        }
        webElement.clear();
        webElement.sendKeys(content);
    }

    /**
     * 点击制定html元素
     *
     * @param type
     * @param value
     * @param timeout
     */
    public void click(LocatorType type, Object value, long timeout) {
        LOGGER.info(String.format("Click web element %s", (String) value));
        sleep(500);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement webElement = null;
        switch (type) {
            case ID:
                String id = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.elementToBeClickable(By.id(id)));
                break;
            case NAME:
                String name = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.elementToBeClickable(By.name(name)));
                break;
            case XPATH:
                String xpath = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                break;
            case CLASSNAME:
                String className = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.elementToBeClickable(By.className(className)));
                break;
            case CSS:
                String css = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
                break;
            case LINKTEXT:
                String linkText = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
                break;
            case PARTIALLINKTEXT:
                String partialLinkText = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.elementToBeClickable(By.partialLinkText(partialLinkText)));
                break;
            case TAGNAME:
                String tagName = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.elementToBeClickable(By.tagName(tagName)));
                break;
        }
        webElement.click();
    }

    /**
     * 等待Web元素
     *
     * @param type
     * @param value
     * @param timeout
     */
    public WebElement waitWebEelement(LocatorType type, Object value, long timeout) {
        LOGGER.info(String.format("Wait web element presence within %d seconds", timeout));
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement webElement = null;
        switch (type) {
            case ID:
                String id = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.id(id)));
                break;
            case XPATH:
                String xpath = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                break;
            case NAME:
                String name = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.name(name)));
                break;
            case CLASSNAME:
                String className = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.className(className)));
                break;
            case CSS:
                String css = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
                break;
            case LINKTEXT:
                String linkText = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
                break;
            case PARTIALLINKTEXT:
                String partialLinkText = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.partialLinkText
                        (partialLinkText)));
                break;
            case TAGNAME:
                String tagName = (String) value;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.tagName(tagName)));
                break;
        }
        return webElement;
    }

    /**
     * 查找页面上包含目标属性的所有元素
     *
     * @param type
     * @param value
     * @return
     */
    @Override
    public List<WebElement> findElements(LocatorType type, Object value) {
        List<WebElement> elements = null;
        switch (type) {
            case CLASSNAME:
                String className = (String) value;
                elements = driver.findElements(By.className(className));
                break;
            case TAGNAME:
                String tag = (String) value;
                elements = driver.findElements(By.tagName(tag));
                break;
        }
        return elements;
    }

    /**
     * 在元素中查找目标元素
     *
     * @param webElement
     * @param type
     * @param value
     * @return
     */
    @Override
    public List<WebElement> findElementsInElement(WebElement webElement, LocatorType type, Object value) {
        List<WebElement> elements = null;
        switch (type) {
            case TAGNAME:
                String tag = (String) value;
                elements = webElement.findElements(By.tagName(tag));
                break;
            case CLASSNAME:
                String className = (String) value;
                elements = webElement.findElements(By.className(className));
                break;
        }
        return elements;
    }

    /**
     * 查找页面上包含目标属性的元素
     *
     * @param type
     * @param value
     * @param index
     * @return
     */
    @Override
    public WebElement findElement(LocatorType type, Object value, int index) {
        WebElement webElement = null;
        switch (type) {
            case CLASSNAME:
                String className = (String) value;
                webElement = driver.findElements(By.className(className)).get(index);
                break;
            case XPATH:
                String xpath = (String) value;
                webElement = driver.findElement(By.xpath(xpath));
                break;
            case TAGNAME:
                String tag = (String) value;
                webElement = driver.findElements(By.tagName(tag)).get(index);
                break;
            case NAME:
                String name = (String) value;
                webElement = driver.findElements(By.name(name)).get(index);
                break;
            default:
                break;
        }
        return webElement;
    }

    /**
     * 操作下拉菜单
     *
     * @param locatorType
     * @param handleType
     * @param locatorValue
     * @param inputValue
     */
    @Override
    public Object handleDropdownMenu(LocatorType locatorType, HandleType handleType,
                                     Object locatorValue, Object inputValue,
                                     long timeout) {
        LOGGER.info(String.format("Handle dropdown menu %s", String.valueOf(locatorType)));
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement webElement = null;
        Select select;
        switch (locatorType) {
            case XPATH:
                String xpath = (String) locatorValue;
                webElement = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                break;
        }
        select = new Select(webElement);
        Object returnValue = null;
        switch (handleType) {
            case SELECTBYINDEX:
                int index = Integer.parseInt((String) inputValue);
                select.selectByIndex(index);
                break;
            case SELECYBYVALUE:
                String value = (String) inputValue;
                select.selectByValue(value);
                break;
            case SELECTBYVISIBLETEXT:
                String text = (String) inputValue;
                select.selectByVisibleText(text);
                break;
            case DESELECTBYINDEX:
                int dIndex = Integer.parseInt((String) inputValue);
                select.deselectByIndex(dIndex);
                break;
            case DESELECTBYVALUE:
                String dValue = (String) inputValue;
                select.deselectByValue(dValue);
                break;
            case DESELECTBYVISIBLETEXT:
                String dText = (String) inputValue;
                select.deselectByVisibleText(dText);
                break;
            case DESELECTALL:
                select.deselectAll();
                break;
            case GETSELECTEDOPTION:
                returnValue = select.getFirstSelectedOption().getText();
                break;
            case GETALLSELECTEDOPTIONS:
                List<String> optionsText = new ArrayList<String>();
                List<WebElement> options = select.getAllSelectedOptions();
                for (int i = 0; i < options.size(); i++) {
                    optionsText.add(options.get(i).getText());
                }
                returnValue = optionsText;
                break;
            case GETALLOPTIONS:
                List<String> allOptionsText = new ArrayList<String>();
                List<WebElement> allOptions = select.getOptions();
                for (int i = 0; i < allOptions.size(); i++) {
                    allOptionsText.add(allOptions.get(i).getText());
                }
                returnValue = allOptionsText;
                break;
        }
        return returnValue;
    }

    /**
     * 切换到另外的页面上
     *
     * @param locatorType
     * @param value
     * @param timeout
     * @return
     */
    @Override
    public WebDriver switchToFrame(LocatorType locatorType, Object value, long timeout) {
        LOGGER.info(String.format("Switch to frame %s.", (String) value));
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement frame = null;
        switch (locatorType) {
            case XPATH:
                String xpath = (String) value;
                frame = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                break;
            case ID:
                String id = (String) value;
                frame = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.id(id)));
                break;
            case NAME:
                String name = (String) value;
                frame = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.name(name)));
                break;
            case CLASSNAME:
                String className = (String) value;
                frame = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.className(className)));
                break;
            case CSS:
                String css = (String) value;
                frame = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
                break;
            case LINKTEXT:
                String linkText = (String) value;
                frame = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
                break;
            case PARTIALLINKTEXT:
                String partialLinkText = (String) value;
                frame = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.partialLinkText(partialLinkText)));
                break;
            case TAGNAME:
                String tag = (String) value;
                frame = wait.until((Function<? super WebDriver, WebElement>) ExpectedConditions.presenceOfElementLocated(By.tagName(tag)));
                break;
        }
        return driver.switchTo().frame(frame);
    }

    /**
     * 滚到窗口到目标元素
     *
     * @param element
     */
    @Override
    public void scrollToElement(WebElement element) {
        LOGGER.info(String.format("Scroll window to element x：%d y: %d", element.getLocation().getX(), element.getLocation().getY()));
        String js = "arguments[0].scrollIntoView(true);";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        javascriptExecutor.executeScript(js, element);
    }

    /**
     * 移动到元素element对象的“底端”与当前窗口的“底部”对齐
     *
     * @param element
     */
    @Override
    public void scrollElementToBottom(WebElement element) {
        LOGGER.info(String.format("Scroll window to element x：%d y: %d", element.getLocation().getX(), element.getLocation().getY()));
        String js = "arguments[0].scrollIntoView(false);";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        javascriptExecutor.executeScript(js, element);
    }


    /**
     * 执行Java Script
     *
     * @param javascript
     */
    public Object executeJavaScript(String javascript) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) this.driver;
        Object value = javascriptExecutor.executeScript(javascript);
        return value;
    }

    /**
     * 断言测试用例执行失败
     *
     * @param message
     */
    @Override
    public void assertFail(String message) {

    }

    /**
     * 断言测试用例执行成功
     *
     * @param message
     */
    @Override
    public void assertPass(String message) {

    }

    /**
     * 强制等待
     *
     * @param timeout
     */
    public void sleep(long timeout) {
        LOGGER.info(String.format("Force to wait %d seconds.", timeout));
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted Exception", e);
        }
    }
}

