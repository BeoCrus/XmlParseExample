package com.example.example;

public class XmlTag {
    private String name;
    private String value;

    public XmlTag(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public XmlTag() {
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
