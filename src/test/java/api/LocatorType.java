package api;

public enum LocatorType {
    ID("id"),
    XPATH("xpath"),
    NAME("name"),
    CLASSNAME("classname"),
    CSS("css"),
    LINKTEXT("linktext"),
    PARTIALLINKTEXT("partiallinktext"),
    TAGNAME("tagname"),
    WEBELEMENT("webelement");

    private String type;

    LocatorType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
