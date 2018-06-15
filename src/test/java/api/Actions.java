package api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public interface Actions {

    /**
     * 在windows的文件选择窗口中选择文件
     *
     * @param filePath
     * @return
     */
    boolean uploadFile(String filePath);

    /**
     * 打开新标签定向到一个url中
     *
     * @param url
     */
    void openNewTab(String url);

    /**
     * 切换页面
     *
     * @param handleName
     */
    void switchToWindow(String handleName);

    /**
     * 切换页面
     *
     * @param index
     */
    void switchToWindow(int index);

    /**
     * 获取当前打开的所有窗口
     *
     * @return
     */
    Set<String> getAllWindows();

    /**
     * 最大化浏览器
     */
    void maximizeBrowser();

    /**
     * 全屏浏览器
     */
    void fullScreenBrowser();

    /**
     * 跳转至具体页面
     *
     * @param url
     */
    void navigate(String url);

    /**
     * 输入指定内容
     *
     * @param type
     * @param value
     * @param content
     */
    void input(LocatorType type, Object value, String content);

    /**
     * 点击制定html元素
     *
     * @param type
     * @param value
     * @param timeout
     */
    void click(LocatorType type, Object value, long timeout);

    /**
     * 等待Web元素
     *
     * @param type
     * @param value
     * @param timeout
     */
    WebElement waitWebEelement(LocatorType type, Object value, long timeout);

    /**
     * 查找页面上包含目标属性的所有元素
     *
     * @param type
     * @param value
     * @return
     */
    List<WebElement> findElements(LocatorType type, Object value);

    /**
     * 在元素中查找目标元素
     *
     * @param webElement
     * @param type
     * @param value
     * @return
     */
    List<WebElement> findElementsInElement(WebElement webElement, LocatorType type, Object value);

    /**
     * 查找页面上包含目标属性的元素
     *
     * @param type
     * @param object
     * @param index
     * @return
     */
    WebElement findElement(LocatorType type, Object object, int index);

    /**
     * 操作下拉菜单
     *
     * @param locatorType
     * @param handleType
     * @param locatorValue
     * @param inputValue
     * @param timeout
     */
    Object handleDropdownMenu(LocatorType locatorType, HandleType handleType,
                              Object locatorValue, Object inputValue, long timeout);

    /**
     * 切换到另外的页面上
     *
     * @param locatorType
     * @param value
     * @param timeout
     * @return
     */
    WebDriver switchToFrame(LocatorType locatorType, Object value, long timeout);

    /**
     * 滚到窗口到目标元素
     *
     * @param element
     */
    void scrollToElement(WebElement element);

    /**
     * 移动到元素element对象的“底端”与当前窗口的“底部”对齐
     *
     * @param element
     */
    void scrollElementToBottom(WebElement element);

    /**
     * 执行Java Script
     *
     * @param javascript
     */
    Object executeJavaScript(String javascript);


    /**
     * 断言测试用例执行失败
     *
     * @param message
     */
    void assertFail(String message);

    /**
     * 断言测试用例执行成功
     *
     * @param message
     */
    void assertPass(String message);

    /**
     * 强制等待
     *
     * @param timeout
     */
    void sleep(long timeout);
}

