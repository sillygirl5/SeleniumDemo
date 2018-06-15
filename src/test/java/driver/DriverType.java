package driver;

public enum DriverType {

    CHROME("chrome");

    private String type;

    DriverType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
