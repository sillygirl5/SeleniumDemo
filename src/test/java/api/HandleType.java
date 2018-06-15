package api;

public enum HandleType {

    SELECTBYINDEX("selectByIndex"),
    SELECYBYVALUE("selectByValue"),
    SELECTBYVISIBLETEXT("selectByVisibleText"),
    DESELECTBYINDEX("deselectByIndex"),
    DESELECTBYVALUE("deselectByValue"),
    DESELECTBYVISIBLETEXT("deselectByVisibleText"),
    DESELECTALL("deselectAll"),
    GETSELECTEDOPTION("getSelectedOption"),
    GETALLSELECTEDOPTIONS("getAllSelectedOptions"),
    GETALLOPTIONS("getAllOptions");

    private String type;

    private HandleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
