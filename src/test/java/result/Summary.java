package result;

import java.text.NumberFormat;

public class Summary {

    private String model;
    private int methodExecuteCount = 0;
    private int passCount = 0;
    private int failCount = 0;
    private float passRate;

    public Summary() {

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMethodExecuteCount() {
        return methodExecuteCount;
    }

    public void setMethodExecuteCount(int methodExecuteCount) {
        this.methodExecuteCount = methodExecuteCount;
    }

    public int getPassCount() {
        return passCount;
    }

    public void setPassCount(int passCount) {
        this.passCount = passCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public String getPassRate() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) passCount / (float) methodExecuteCount * 100);
        return result + "%";
    }

    public void setPassRate(float passRate) {
        this.passRate = passRate;
    }

    public void addPassCount(int count) {
        passCount = passCount + count;
    }

    public void addFailCount(int count) {
        failCount = failCount + count;
    }

    public void addMethodExecuteCount(int count) {
        methodExecuteCount = methodExecuteCount + count;
    }
}
