package result;

/**
 * 测试方法实体类
 *
 * @author cdzhaojiajun
 */
public class TestMethodResult {

    private String model;
    private String method;
    private String result;
    private String param;
    private String steps;
    private String exception;
    private String jfsUrl;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result ? "PASS" : "FAIL";
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getJfsUrl() {
        return jfsUrl;
    }

    public void setJfsUrl(String jfsUrl) {
        this.jfsUrl = jfsUrl;
    }
}
