package data;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class TestData {

    @DataProvider(name = "searchDataProvider")
    private Object[][] dataProvider(Method method) {
        Object[][] data = null;
        if (method.getName().equals("testcase1")) {
            data = new Object[][]{
                    {"测试"}
            };
        }
        return data;
    }
}
